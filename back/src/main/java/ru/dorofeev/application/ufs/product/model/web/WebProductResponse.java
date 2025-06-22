package ru.dorofeev.application.ufs.product.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.application.pagination.model.BasicPaginationResponseFields;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Модель со списком продуктов")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WebProductResponse extends BasicPaginationResponseFields {

    @Schema(description = "Список продуктов")
    @Builder.Default
    private List<WebProduct> products = new ArrayList<>();
}
