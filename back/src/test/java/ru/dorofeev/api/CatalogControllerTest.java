package ru.dorofeev.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "/sql/insert_basic_information.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/truncate_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CatalogControllerTest extends BaseApiTest {

    @DisplayName("[API-тест] Получение списка категорий: Успешный ответ")
    @Test
    void getCategories_shouldReturnSuccessAndNotEmptyResponse() {

    }

    @DisplayName("[API-тест] Получение списка категорий: Ошибка при валидации")
    @Test
    void getCategories_shouldReturnBadRequestAndErrorResponse() {

    }
}
