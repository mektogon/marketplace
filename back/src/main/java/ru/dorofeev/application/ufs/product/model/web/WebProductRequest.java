package ru.dorofeev.application.ufs.product.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.application.pagination.model.BasicPaginationFilterFields;

@Schema(description = "Модель запроса списка продуктов")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class WebProductRequest extends BasicPaginationFilterFields {

}
