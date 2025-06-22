package ru.dorofeev.application.ufs.catalog.controller;

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
import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogRequest;
import ru.dorofeev.application.ufs.catalog.model.web.WebCatalogResponse;
import ru.dorofeev.application.ufs.catalog.service.CatalogService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog")
@Tag(name = "CatalogController", description = "Точка доступа к каталогу")
public class CatalogController {

    private final CatalogService service;

    @Operation(description = "Получение списка категорий",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = WebCatalogResponse.class))
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
    @PostMapping()
    public WebCatalogResponse getCatalog(@Valid @RequestBody WebCatalogRequest request) {
        return service.getCatalog(request);
    }
}
