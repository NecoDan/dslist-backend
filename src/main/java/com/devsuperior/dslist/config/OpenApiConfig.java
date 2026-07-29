package com.devsuperior.dslist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Projeto Multiplataforma")
                        .version("1.0.0")
                        .description("Documentação da API gerada automaticamente com Springdoc OpenAPI e Spring Boot.")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("Apache 2.0").
                                url("http://springdoc.org")
                        )
                );
    }
}
