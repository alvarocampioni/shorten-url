package com.practice.urlshorten.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("URL Shortener").version("1.0.0")
                .description("Shorten a URL or get the original URL from the shortened one")
                .summary("Place the Short URL from the response body on your browser"));
    }
}
