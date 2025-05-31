package ru.dorofeev.application.ufs.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dorofeev.application.exception.model.ErrorResponse;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryRequest;
import ru.dorofeev.application.ufs.category.model.web.WebCategoryResponse;
import ru.dorofeev.application.ufs.category.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Tag(name = "CategoryController", description = "Точка доступа к категориям")
public class CategoryController {

    private final String POST_LIST = "/list";

    private final CategoryService service;

    @Operation(description = "Получение списка категорий",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = WebCategoryResponse.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping(POST_LIST)
    public WebCategoryResponse getCategories(@Valid @RequestBody WebCategoryRequest request) {
        return service.getCategories(request);
    }
}
