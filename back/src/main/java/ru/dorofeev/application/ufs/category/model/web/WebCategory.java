package ru.dorofeev.application.ufs.category.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Schema(description = "Модель с информацией по категории")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WebCategory {

    @Schema(description = "Код категории")
    private String code;

    @Schema(description = "Наименование категории")
    private String displayName;

    @Schema(description = "Приоритет категории")
    private short priority;

    @Schema(description = "Отображение категории")
    private Boolean isVisible;
}
