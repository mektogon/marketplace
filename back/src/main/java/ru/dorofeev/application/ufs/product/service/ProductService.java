package ru.dorofeev.application.ufs.product.service;

import ru.dorofeev.application.ufs.product.model.web.WebProductRequest;
import ru.dorofeev.application.ufs.product.model.web.WebProductResponse;

public interface ProductService {

    /**
     * Получение списка продуктов.
     *
     * @param request объект типа {@link WebProductRequest}.
     * @return объект типа {@link WebProductResponse}.
     */
    WebProductResponse getProducts(WebProductRequest request);
}
