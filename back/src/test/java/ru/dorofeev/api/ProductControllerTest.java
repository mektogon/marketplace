package ru.dorofeev.api;

import io.restassured.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import ru.dorofeev.application.exception.model.ErrorResponse;
import ru.dorofeev.application.exception.model.enums.ErrorType;
import ru.dorofeev.application.pagination.model.Pagination;
import ru.dorofeev.application.ufs.product.model.web.WebProduct;
import ru.dorofeev.application.ufs.product.model.web.WebProductRequest;
import ru.dorofeev.application.ufs.product.model.web.WebProductResponse;

import java.util.List;

@Sql(value = "/sql/insert_basic_information.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/truncate_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ProductControllerTest extends BaseApiTest {

    private static final String BASE_PATH = "/product";
    private static final String POST_PRODUCT_LIST = BASE_PATH + "/list";

    @DisplayName("[API-тест] Получение списка продуктов: Успешный ответ, у одного из продуктов есть теги")
    @Test
    void getProducts_shouldReturnSuccessAndNotEmptyResponse() {
        Response response = makeRequest(
                requestSpecification,
                POST_PRODUCT_LIST,
                HttpMethod.POST,
                null,
                getWebProductRequest(0, 10)
        );

        WebProductResponse webProductResponse = handleResponse(
                response,
                specOkAndJsonType,
                WebProductResponse.class
        );

        Assertions.assertNotNull(webProductResponse);

        List<WebProduct> products = webProductResponse.getProducts();
        Assertions.assertNotNull(products);
        Assertions.assertEquals(3, products.size());

        List<WebProduct> productWithTags = products.stream()
                .filter(p -> CollectionUtils.isNotEmpty(p.getTags()))
                .toList();

        Assertions.assertEquals(1, productWithTags.size());

        List<WebProduct> productWithoutTags = products.stream()
                .filter(p -> CollectionUtils.isEmpty(p.getTags()))
                .toList();

        Assertions.assertEquals(2, productWithoutTags.size());
    }

    @DisplayName("[API-тест] Получение списка продуктов: Ошибка при валидации параметров пагинации")
    @Test
    void getProducts_shouldReturnBadRequestAndErrorResponse() {
        Response response = makeRequest(
                requestSpecification,
                POST_PRODUCT_LIST,
                HttpMethod.POST,
                null,
                getWebProductRequest(null, null)
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
        Assertions.assertEquals(2, errorResponse.getValidation().size());

        Assertions.assertTrue(errorResponse.getValidation().stream()
                .anyMatch(error -> "pagination.pageSize".equals(error.getErrorField())
                        && "Количество элементов не может быть равно null!".equals(error.getErrorMessage())));

        Assertions.assertTrue(errorResponse.getValidation().stream()
                .anyMatch(error -> "pagination.pageIndex".equals(error.getErrorField())
                        && "Индекс не может быть равен null!".equals(error.getErrorMessage())));
    }

    /**
     * Метод формирования объекта типа {@link WebProductRequest}.
     *
     * @param pageIndex индекс страницы при пагинации.
     * @param pageSize  размер страницы при пагинации.
     * @return объект типа {@link WebProductRequest}.
     */
    public static WebProductRequest getWebProductRequest(Integer pageIndex, Integer pageSize) {
        return WebProductRequest.builder()
                .pagination(Pagination.builder().pageIndex(pageIndex).pageSize(pageSize).build())
                .build();
    }
}