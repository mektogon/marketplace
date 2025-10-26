package ru.dorofeev.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import ru.dorofeev.application.exception.model.ErrorResponse;
import ru.dorofeev.application.exception.model.enums.ErrorType;
import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogRequest;
import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogResponse;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryResponse;
import ru.dorofeev.application.ufs.product.model.web.WebProductResponse;

@Sql(value = "/sql/insert_basic_information.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/truncate_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CatalogControllerTest extends BaseApiTest {

    private static final String BASE_PATH = "/catalog";

    @DisplayName("[API-тест] Получение каталога товаров по указанной категории: Успешный ответ")
    @Test
    void getCatalog_shouldReturnSuccessAndNotEmptyResponse() {

        Response response = makeRequest(
                requestSpecification,
                BASE_PATH,
                HttpMethod.POST,
                null,
                getWebCatalogRequest(0, 10, 0, 10)
        );

        WebCatalogResponse webCatalogResponse = handleResponse(
                response,
                specOkAndJsonType,
                WebCatalogResponse.class
        );

        Assertions.assertNotNull(webCatalogResponse);

        WebProductResponse productResponse = webCatalogResponse.getProductResponse();
        Assertions.assertNotNull(productResponse);
        Assertions.assertFalse(productResponse.getProducts().isEmpty());
        Assertions.assertNotNull(productResponse.getPagination());
        Assertions.assertNotNull(productResponse.getSortField());

        WebCategoryResponse categoryResponse = webCatalogResponse.getCategoryResponse();
        Assertions.assertNotNull(categoryResponse);
        Assertions.assertFalse(categoryResponse.getCategories().isEmpty());
        Assertions.assertNotNull(categoryResponse.getPagination());
        Assertions.assertNotNull(categoryResponse.getSortField());
    }

    @DisplayName("[API-тест] Получение каталога товаров: Ошибка при валидации параметров пагинации")
    @Test
    void getCatalog_shouldReturnBadRequestAndErrorResponse() {
        Response response = makeRequest(
                requestSpecification,
                BASE_PATH,
                HttpMethod.POST,
                null,
                getWebCatalogRequest(null, null, null, null)
        );

        ErrorResponse errorResponse = handleResponse(
                response,
                specBadRequestAndJsonType,
                ErrorResponse.class
        );

        Assertions.assertNotNull(errorResponse);
        Assertions.assertNotNull(errorResponse.getCode());
        Assertions.assertEquals(ErrorType.VALIDATION_ERROR.getCode(), errorResponse.getCode());
        Assertions.assertNotNull(errorResponse.getType());
        Assertions.assertEquals(ErrorType.VALIDATION_ERROR.name(), errorResponse.getType());
        Assertions.assertNotNull(errorResponse.getSystem());
        Assertions.assertEquals(systemName, errorResponse.getSystem());
        Assertions.assertNotNull(errorResponse.getValidation());

        Assertions.assertFalse(errorResponse.getValidation().isEmpty());
        Assertions.assertEquals(4, errorResponse.getValidation().size());

        Assertions.assertTrue(errorResponse.getValidation().stream()
                .anyMatch(error -> "Количество элементов не может быть равно null!".equals(error.getErrorMessage())));

        Assertions.assertTrue(errorResponse.getValidation().stream()
                .anyMatch(error -> "Индекс не может быть равен null!".equals(error.getErrorMessage())));
    }

    /**
     * Метод формирования объекта типа {@link WebCatalogRequest}.
     *
     * @param pageIndexCategory индекс страницы при пагинации категорий.
     * @param pageSizeCategory  размер страницы при пагинации категорий.
     * @param pageIndexProduct  индекс страницы при пагинации продуктов.
     * @param pageSizeProduct   размер страницы при пагинации продуктов.
     * @return объект типа {@link WebCatalogRequest}.
     */
    public static WebCatalogRequest getWebCatalogRequest(
            Integer pageIndexCategory,
            Integer pageSizeCategory,
            Integer pageIndexProduct,
            Integer pageSizeProduct
    ) {
        return WebCatalogRequest.builder()
                .categoryRequest(CategoryControllerTest.getWebCategoryRequest(pageIndexCategory, pageSizeCategory))
                .productRequest(ProductControllerTest.getWebProductRequest(pageIndexProduct, pageSizeProduct))
                .build();
    }
}
