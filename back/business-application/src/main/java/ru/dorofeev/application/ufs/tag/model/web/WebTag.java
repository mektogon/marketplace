package ru.dorofeev.application.ufs.tag.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Schema(description = "Модель с информацией по тегу")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WebTag {

    @Schema(description = "Код тега")
    private String code;

    @Schema(description = "Наименование тега")
    private String displayName;
}
