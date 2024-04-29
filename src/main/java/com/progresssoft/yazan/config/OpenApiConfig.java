package com.progresssoft.yazan.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Yazan Ata"
                ),
                description = "OpenApi Documentation for ClusteredData Warehouse",
                title = "OpenApi specification - Yazan"
        )
)
public class OpenApiConfig {
}
