package ru.dorofeev.application.ufs.catalog.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryResponse;
import ru.dorofeev.application.ufs.product.model.web.WebProductResponse;

@Schema(description = "Модель с каталогом товаров")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WebCatalogResponse {

    @Schema(description = "Код категории, по которой необходимо получить начальный список товаров")
    private String initialCategoryCode;

    @Schema(description = "Информация по категориям")
    private WebCategoryResponse categoryResponse;

    @Schema(description = "Информация по продуктам")
    private WebProductResponse productResponse;
}
