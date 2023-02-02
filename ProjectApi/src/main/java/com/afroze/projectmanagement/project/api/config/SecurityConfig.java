package com.afroze.projectmanagement.project.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(a -> a.requestMatchers("/actuator").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/actuator/**").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/project").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/project/**").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/**").authenticated())
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}