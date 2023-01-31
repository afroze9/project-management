package com.afroze.projectmanagement.company.api.config;

import com.afroze.projectmanagement.company.api.security.CustomConverter;
import com.afroze.projectmanagement.company.api.security.GrantedAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebSecurity
public class SecurityConfig {

    private final Environment environment;

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
//                .pathMatchers("/company/**").permitAll()//hasAuthority("read:company")
//                .pathMatchers("/company").permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                    .jwtDecoder(jwtDecoder())
                    .jwtAuthenticationConverter(customConverter());
        return http.build();
    }

    private ReactiveJwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(environment.getProperty("spring.security.oauth2.resourceserver.jwt.issuer-uri"));
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesConverter() {
        GrantedAuthoritiesConverter converter = new GrantedAuthoritiesConverter();
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> customConverter() {
        var converter = new CustomConverter();
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}