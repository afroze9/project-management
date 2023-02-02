//package com.afroze.projectmanagement.api.gateway.security;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
//import org.springframework.mock.web.server.MockServerWebExchange;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static reactor.core.publisher.Mono.when;
//
//class AuthorizationHeaderFilterTest {
//
//    private final AuthorizationHeaderFilter factory;
//    private ServerWebExchange exchange;
//    private GatewayFilterChain filterChain = mock(GatewayFilterChain.class);
//    private ArgumentCaptor<ServerWebExchange> captor = ArgumentCaptor.forClass(ServerWebExchange.class);
//
//    @Autowired
//    public AuthorizationHeaderFilterTest(AuthorizationHeaderFilter factory) {
//
//        this.factory = factory;
//    }
//
//    @BeforeEach
//    void setUp() {
//        when(filterChain.filter(captor.capture()))
//                .thenReturn(Mono.empty());
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void apply() {
//        MockServerHttpRequest request = MockServerHttpRequest.get("http://localhost:8080").build();
//        exchange = MockServerWebExchange.from(request);
//        GatewayFilter filter = factory.apply(new AuthorizationHeaderFilter.Config());
//
//        ServerHttpRequest actualRequest = captor.getValue().getRequest();
//        assertEquals(request, actualRequest);
//    }
//}