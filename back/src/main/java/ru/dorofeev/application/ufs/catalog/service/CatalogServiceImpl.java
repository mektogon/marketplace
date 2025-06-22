package ru.dorofeev.application.ufs.catalog.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.dorofeev.application.pagination.model.PaginationDetail;
import ru.dorofeev.application.pagination.model.SortField;
import ru.dorofeev.application.pagination.utils.PaginationUtils;
import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogRequest;
import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogResponse;
import ru.dorofeev.application.ufs.category.mapper.CategoryMapper;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryResponse;
import ru.dorofeev.application.ufs.product.mapper.ProductMapper;
import ru.dorofeev.application.ufs.product.model.web.WebProductResponse;
import ru.dorofeev.database.entity.Category;
import ru.dorofeev.database.entity.ProductCategory;
import ru.dorofeev.database.repository.CategoryRepository;
import ru.dorofeev.database.repository.ProductCategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private static final WebCatalogResponse EMPTY_RESPONSE = new WebCatalogResponse();
    private static final String DEFAULT_CATEGORY_FIELD_SORT = "priority";

    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public WebCatalogResponse getCatalog(WebCatalogRequest request) {

        if (Objects.isNull(request)) {
            log.warn("Объект запроса не может быть равен null!");
            return EMPTY_RESPONSE;
        }

        SortField sortFieldCategoryRequest = request.getCategoryRequest().getSortField();
        if (Objects.isNull(sortFieldCategoryRequest)) {
            sortFieldCategoryRequest = SortField.builder()
                    .field(DEFAULT_CATEGORY_FIELD_SORT)
                    .direction(Sort.Direction.ASC)
                    .build();
        }

        Slice<Category> categoriesSlice = categoryRepository.findAllByIsVisibleTrue(
                PaginationUtils.getPageable(
                        request.getCategoryRequest().getPagination(),
                        sortFieldCategoryRequest
                )
        );
        List<Category> categoriesContent = categoriesSlice.getContent();

        AtomicReference<String> initialCategoryCode = new AtomicReference<>(request.getInitialCategoryCode());
        if (StringUtils.isBlank(initialCategoryCode.get())) {
            categoriesContent.stream().findFirst().ifPresent(o -> initialCategoryCode.set(o.getCode()));
        }

        Slice<ProductCategory> productsSlice =
                productCategoryRepository.getProductCategoriesByCategoryCode(
                        initialCategoryCode.get(),
                        PaginationUtils.getPageable(
                                request.getProductRequest().getPagination(),
                                request.getProductRequest().getSortField()
                        )
                );

        return WebCatalogResponse.builder()
                .initialCategoryCode(initialCategoryCode.get())
                .categoryResponse(
                        WebCategoryResponse.builder()
                                .categories(categoryMapper.map(categoriesSlice.getContent()))
                                .pagination(PaginationDetail.build(categoriesSlice))
                                .sortField(SortField.build(categoriesSlice))
                                .build()
                )
                .productResponse(
                        WebProductResponse.builder()
                                .products(
                                        productMapper.map(
                                                productsSlice.getContent()
                                                        .stream()
                                                        .map(ProductCategory::getProduct)
                                                        .toList()
                                        )
                                )
                                .pagination(PaginationDetail.build(categoriesSlice))
                                .sortField(SortField.build(categoriesSlice))
                                .build()
                )
                .build();
    }
}
