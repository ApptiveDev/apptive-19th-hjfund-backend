package com.example.apptive19thhjfundbackend.user.config;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링 빈으로 등록
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        Properties prop = new Properties();

        // 모든 경로에 대해
        registry.addMapping("/**")
                // GET, POST, PUT, PATCH, DELETE, OPTIONS 메서드를 허용한다.
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);

        String deployType = prop.getProperty("hjfund.deploy.type");

        // deploy.type에 따라 다른 도메인을 허용한다.
        if (deployType.equals("develop")) {
            registry.addMapping("/**")
                    .allowedOrigins(prop.getProperty("hjfund.deploy.develop_origin"));
        }

        if (deployType.equals("main")) {
            registry.addMapping("/**")
                    .allowedOrigins(prop.getProperty("hjfund.deploy.main_origin"));
        }
    }
}
