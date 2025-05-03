package ru.dorofeev.application;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Base Project",
                description = "Интернет-площадка для размещения товаров",
                version = "01.000.00"
        ),
        servers = @Server(
                url = "protocol://host:port/marketplace/",
                description = "Адрес приложения"
        ),
        externalDocs = @ExternalDocumentation(
                url = "protocol://host:port/docs/app",
                description = "Карточка приложения"
        )
)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
