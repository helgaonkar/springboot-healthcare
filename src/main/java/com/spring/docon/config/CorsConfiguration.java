package com.spring.docon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer1() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/enrollments").allowedOrigins("http://localhost:4200");
                registry.addMapping("/enrollments/*").allowedOrigins("http://localhost:4200");
            }
        };
    }
}
