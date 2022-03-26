package com.mathfr.poketodo.infrastructure.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestInterceptorConfig {

    @Value("${spring.pokeapi.limit}")
    private String limit;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.query("limit", limit);
        };
    }

}
