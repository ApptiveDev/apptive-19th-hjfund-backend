package com.example.apptive19thhjfundbackend.user.config;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration // 스프링 빈으로 등록
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    @Value("${hjfund.deploy.type}")
    private String deployType;

    @Value("${hjfund.deploy.develop_origin}")
    private String developOrigin;

    @Value("${hjfund.deploy.main_origin}")
    private String mainOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 모든 경로에 대해
        registry.addMapping("/**")
                // GET, POST, PUT, PATCH, DELETE, OPTIONS 메서드를 허용한다.
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);

        // deploy.type에 따라 다른 도메인을 허용한다.
        if (deployType.equals("develop")) {
            registry.addMapping("/**")
                    .allowedOrigins(developOrigin);
        }

        if (deployType.equals("main")) {
            registry.addMapping("/**")
                    .allowedOrigins(mainOrigin);
        }
    }
}