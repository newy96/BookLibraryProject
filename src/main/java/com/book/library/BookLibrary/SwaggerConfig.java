package com.book.library.BookLibrary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "BookLibraryAPI",
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "Dev",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {
}
