package com.powerup.house_microservice.infrastructure.configuration.security;

import com.powerup.house_microservice.infrastructure.configuration.security.jwt.JWTAuthorizationFilter;
import com.powerup.house_microservice.infrastructure.configuration.security.jwt.JwtEntryPoint;
import com.powerup.house_microservice.infrastructure.configuration.security.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtEntryPoint jwtEntryPoint;
    private final JwtToken jwtToken;

    @Bean
    public JWTAuthorizationFilter jwtTokenFilter() {
        return new JWTAuthorizationFilter(jwtToken);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**",
                                "/v3/api-docs/**").permitAll()
                        //RealEstateCategory
                        .requestMatchers(HttpMethod.POST, "/real-estate-categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/real-estate-categories").hasAnyRole("ADMIN", "SELLER", "BUYER")
                        //Locations
                        .requestMatchers(HttpMethod.POST, "/locations").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/locations").hasAnyRole("ADMIN", "SELLER", "BUYER")
                        //Cities
                        .requestMatchers(HttpMethod.POST, "/cities").hasRole("ADMIN")
                        //RealEstates
                        .requestMatchers(HttpMethod.POST, "/real-estates").hasAnyRole( "SELLER")
                        .requestMatchers(HttpMethod.GET, "/real-estates").hasAnyRole("ADMIN", "SELLER", "BUYER")
                        .requestMatchers(HttpMethod.GET, "/real-estates/exists").permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint))
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
