package ru.dorofeev.application.ufs.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.dorofeev.application.pagination.model.PaginationDetail;
import ru.dorofeev.application.pagination.model.SortField;
import ru.dorofeev.application.pagination.utils.PaginationUtils;
import ru.dorofeev.application.ufs.category.mapper.CategoryMapper;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryRequest;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryResponse;
import ru.dorofeev.database.entity.Category;
import ru.dorofeev.database.repository.CategoryRepository;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final WebCategoryResponse EMPTY_RESPONSE = new WebCategoryResponse();

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public WebCategoryResponse getCategories(WebCategoryRequest request) {

        if (Objects.isNull(request)) {
            log.warn("Объект запроса не может быть равен null!");
            return EMPTY_RESPONSE;
        }

        Slice<Category> categorySlice = repository.findAllByIsVisibleTrue(
                PaginationUtils.getPageable(
                        request.getPagination(),
                        request.getSortField()
                )
        );

        return WebCategoryResponse.builder()
                .categories(mapper.map(categorySlice.getContent()))
                .pagination(PaginationDetail.build(categorySlice))
                .sortField(SortField.build(categorySlice))
                .build();
    }
}
