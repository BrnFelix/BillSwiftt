package com.uj.billswift.clients;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Anotação que define esta classe como uma configuração do Spring
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Sobrescreve o método para adicionar mapeamentos de CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Adiciona mapeamentos de CORS para todas as rotas (/**)
        registry.addMapping("/**")
                // Permite origens específicas (neste caso, http://localhost:8082)
                .allowedOrigins("http://localhost:8082")
                // Permite métodos HTTP específicos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}