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
        // Origin 불러오기
        String[] allowedOrigins;
        if (deployType.equals("develop")) {
            allowedOrigins = new String[]{developOrigin};
        } else {
            allowedOrigins = new String[]{mainOrigin};
        }

        registry.addMapping("/**")
            .allowedOrigins(allowedOrigins)
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(MAX_AGE_SECS);
    }
}