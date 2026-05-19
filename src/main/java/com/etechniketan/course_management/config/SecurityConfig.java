package com.etechniketan.course_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF for standard REST APIs (if using stateless auth like JWT)
            .csrf(csrf -> csrf.disable())

            // 2. Configure endpoint permissions
            .authorizeHttpRequests(auth -> auth
                // Allow public access to all Swagger/OpenAPI resources
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()

                // All other API requests must be authenticated
                .anyRequest().authenticated()
            )// Enable an HTTP Basic authentication standard
                .httpBasic(Customizer.withDefaults());;

        return http.build();
    }
}