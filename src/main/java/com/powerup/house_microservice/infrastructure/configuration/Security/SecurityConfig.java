package com.powerup.house_microservice.infrastructure.configuration.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // para desabilitar vulnerabilidade en los formularios web podria quitarse
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**",
                                "/v3/api-docs/**", "/real-estate-category/save").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

}
