package ru.dorofeev.application.ufs.category.service;

import ru.dorofeev.application.ufs.category.model.web.WebCategoryRequest;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryResponse;

public interface CategoryService {

    /**
     * Получение списка категорий.
     *
     * @param request объект типа {@link WebCategoryRequest}.
     * @return объект типа {@link WebCategoryResponse}.
     */
    WebCategoryResponse getCategories(WebCategoryRequest request);
}
