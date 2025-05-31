package ru.dorofeev.application.ufs.catalog.service;

import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogRequest;
import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogResponse;

public interface CatalogService {

    /**
     * Получение каталога товаров.
     *
     * @param request объект типа {@link WebCatalogRequest}.
     * @return объект типа {@link WebCatalogResponse}.
     */
    WebCatalogResponse getCatalog(WebCatalogRequest request);
}
