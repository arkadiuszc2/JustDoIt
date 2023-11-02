package com.justDoIt.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Backend API",
        contact = @Contact(name = "Arkadiusz Ciepliński", email = "arkadiusz.cieplinski2@gmail.com"),
        version = "1.0"
    )
)
public class OpenApiConfig {

}
