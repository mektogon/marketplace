package ru.dorofeev.application.ufs.product.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.application.ufs.tag.model.web.WebTag;

import java.util.List;
import java.util.UUID;

@Schema(description = "Модель с информацией по продукту")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WebProduct {

    @Schema(description = "Идентификатор продукта")
    private UUID id;

    @Schema(description = "Наименование продукта")
    private String displayName;

    @Schema(description = "Описание продукта")
    private String description;

    @Schema(description = "Список тегов пользователя")
    private List<WebTag> tags;
}
