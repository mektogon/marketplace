package ru.dorofeev.application.ufs.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.dorofeev.application.pagination.model.PaginationDetail;
import ru.dorofeev.application.pagination.model.SortField;
import ru.dorofeev.application.pagination.utils.PaginationUtils;
import ru.dorofeev.application.ufs.product.mapper.ProductMapper;
import ru.dorofeev.application.ufs.product.model.web.WebProductRequest;
import ru.dorofeev.application.ufs.product.model.web.WebProductResponse;
import ru.dorofeev.database.entity.Product;
import ru.dorofeev.database.repository.ProductRepository;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final WebProductResponse EMPTY_RESPONSE = new WebProductResponse();

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public WebProductResponse getProducts(WebProductRequest request) {

        if (Objects.isNull(request)) {
            log.warn("Объект запроса не может быть равен null!");
            return EMPTY_RESPONSE;
        }

        Slice<Product> productSlice = repository.findAllByIsVisibleTrue(
                PaginationUtils.getPageable(
                        request.getPagination(),
                        request.getSortField()
                )
        );

        return WebProductResponse.builder()
                .products(mapper.map(productSlice.getContent()))
                .pagination(PaginationDetail.build(productSlice))
                .sortField(SortField.build(productSlice))
                .build();
    }
}
