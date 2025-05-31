package ru.dorofeev.application.ufs.catalog.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryRequest;
import ru.dorofeev.application.ufs.product.model.web.WebProductRequest;

@Schema(description = "Модель запроса каталога")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WebCatalogRequest {

    @Size(message = "Код категории не может быть пустым!", min = 1)
    @Schema(description = "Код категории, по которой необходимо получить начальный список товаров")
    private String initialCategoryCode;

    @Schema(description = "Параметры запрос списка категорий")
    private WebCategoryRequest categoryRequest;

    @Schema(description = "Параметры запрос списка продуктов")
    private WebProductRequest productRequest;
}
