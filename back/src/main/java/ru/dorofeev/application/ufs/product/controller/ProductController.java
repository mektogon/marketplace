package ru.dorofeev.application.ufs.product.controller;

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
import ru.dorofeev.application.ufs.product.model.web.WebProductRequest;
import ru.dorofeev.application.ufs.product.model.web.WebProductResponse;
import ru.dorofeev.application.ufs.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "ProductController", description = "Точка доступа к продуктам")
public class ProductController {

    private final String POST_LIST = "/list";

    private final ProductService service;

    @Operation(description = "Получение списка продуктов",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = WebProductResponse.class))
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
    public WebProductResponse getProducts(@Valid @RequestBody WebProductRequest request) {
        return service.getProducts(request);
    }
}
