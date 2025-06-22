package ru.dorofeev.application.ufs.category.model.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.application.pagination.model.BasicPaginationResponseFields;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Модель со списком категорий")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class WebCategoryResponse extends BasicPaginationResponseFields {

    @Schema(description = "Список категорий")
    @Builder.Default
    private List<WebCategory> categories = new ArrayList<>();
}
