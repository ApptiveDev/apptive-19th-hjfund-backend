package com.example.apptive19thhjfundbackend.user.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${hjfund.deploy.type}")
    private String deployType;

    @Value("${hjfund.deploy.develop_origin}")
    private String developOrigin;

    @Value("${hjfund.deploy.main_origin}")
    private String mainOrigin;

    @Autowired
    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // CORS 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Origin 불러오기
        String[] allowedOrigins;
        if (deployType.equals("develop")) {
            allowedOrigins = new String[]{developOrigin};
        } else {
            allowedOrigins = new String[]{mainOrigin};
        }
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .authorizeRequests()
                // 1. 기본적으로 GET 메소드는 허용. /api/user/* 에 대한 JWT 인증 적용 (단, /api/user/auth 제외)
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/user/auth/**").permitAll()
                // 2. /api/report/*에 대해 POST, PUT, DELETE는 ADMIN 권한 요구 (단, /api/report/[id]/like 제외)
                .antMatchers(HttpMethod.POST, "/api/report/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/report/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/report/**").hasRole("ADMIN")
                .antMatchers("/api/report/**/like").authenticated()
                // 3. 특정 POST 요청에 대한 JWT 인증 제외
                .antMatchers(HttpMethod.POST, "/api/user/auth/login", "/api/user/auth/register").permitAll()
                // 기타 등등
                .anyRequest().authenticated()
            .and()
            .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
            .and()
            // JWT 인증 필터
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**", "/sign-api/exception");
    }

}
