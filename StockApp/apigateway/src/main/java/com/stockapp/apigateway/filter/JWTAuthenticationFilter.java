package com.stockapp.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JWTAuthenticationFilter implements GatewayFilter {

//    HTTP calls karega
    private final WebClient webClient;

//    Auth service ka base URL set karta hai
    public JWTAuthenticationFilter(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081").build(); // Auth service ka URL jaha token validate hoga
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return chain.filter(exchange);
    }
}
