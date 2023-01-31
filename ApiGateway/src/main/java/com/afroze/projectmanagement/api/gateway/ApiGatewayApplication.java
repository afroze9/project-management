package com.afroze.projectmanagement.api.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	private final TokenRelayGatewayFilterFactory filterFactory;

	@Autowired
	public ApiGatewayApplication(TokenRelayGatewayFilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("company", r -> r.path("/company")
						.filters(f -> f.filter(filterFactory.apply()))
				.uri("lb://company"))
				.build();
	}
}
