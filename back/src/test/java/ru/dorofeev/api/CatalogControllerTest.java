package ru.dorofeev.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import ru.dorofeev.application.exception.model.ErrorResponse;
import ru.dorofeev.application.exception.model.enums.ErrorType;
import ru.dorofeev.application.pagination.model.Pagination;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryRequest;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryResponse;

@Sql(value = "/sql/insert_basic_information.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/truncate_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CatalogControllerTest extends BaseApiTest {

    private static final String BASE_PATH = "/category";
    private static final String POST_CATEGORY_LIST = BASE_PATH + "/list";

    @DisplayName("[API-тест] Получение списка категорий: Успешный ответ")
    @Test
    void getCategories_shouldReturnSuccessAndNotEmptyResponse() {

        Response response = makeRequest(
                requestSpecification,
                POST_CATEGORY_LIST,
                HttpMethod.POST,
                null,
                getCategoryRequest(0, 100)
        );

        WebCategoryResponse webCategoryResponse = handleResponse(
                response,
                specOkAndJsonType,
                WebCategoryResponse.class
        );

        Assertions.assertNotNull(webCategoryResponse);
        Assertions.assertNotNull(webCategoryResponse.getCategories());
        Assertions.assertFalse(webCategoryResponse.getCategories().isEmpty());
    }

    @DisplayName("[API-тест] Получение списка категорий: Ошибка при валидации параметров пагинации")
    @Test
    void getCategories_shouldReturnBadRequestAndErrorResponse() {
        Response response = makeRequest(
                requestSpecification,
                POST_CATEGORY_LIST,
                HttpMethod.POST,
                null,
                getCategoryRequest(null, null)
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
     * Метод формирования объекта типа {@link WebCategoryRequest}.
     *
     * @param pageIndex индекс страницы при пагинации.
     * @param pageSize  размер страницы при пагинации.
     * @return объект типа {@link WebCategoryRequest}.
     */
    public static WebCategoryRequest getCategoryRequest(Integer pageIndex, Integer pageSize) {
        return WebCategoryRequest.builder()
                .pagination(Pagination.builder().pageIndex(pageIndex).pageSize(pageSize).build())
                .build();
    }
}
