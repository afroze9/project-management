package com.afroze.projectmanagement.api.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Logger logger;

    private final String issuer;

    @Autowired
    public AuthorizationHeaderFilter(Environment environment) {
        super(Config.class);
        this.logger = LoggerFactory.getLogger(AuthorizationHeaderFilter.class);
        this.issuer = environment.getProperty("spring.security.oauth2.resourceserver.jwt.issuer-uri");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
            }

            final List<String> authHeaders = request.getHeaders().get(HttpHeaders.AUTHORIZATION);

            if(authHeaders == null || authHeaders.isEmpty()) {
                return onError(exchange, "Undefined state", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            final String jwtToken = authHeaders.get(0).substring(7);

            if(!isJwtValid(jwtToken)) {
                return onError(exchange, "JWT Token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isJwtValid(String token) {
        JwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuer);
        try {
            Jwt output = decoder.decode(token);
            return output.getSubject() != null && !output.getSubject().isEmpty();
        } catch (BadJwtException e) {
            logger.info("Invalid JWT", e);
        }
        return false;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);

        logger.info(err);

        return response.setComplete();
    }

    public static class Config {}
}
