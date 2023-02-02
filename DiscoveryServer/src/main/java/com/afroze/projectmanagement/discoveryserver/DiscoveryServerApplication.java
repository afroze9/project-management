package com.afroze.projectmanagement.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server for Project Management App
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

	/**
	 * Entry point for Eureka Server
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}
}