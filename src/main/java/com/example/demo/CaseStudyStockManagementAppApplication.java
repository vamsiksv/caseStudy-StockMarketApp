package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.*;

@SpringBootApplication
@EnableDiscoveryClient
public class CaseStudyStockManagementAppApplication {
	
	
	
	  @Bean public FilterRegistrationBean jwtFilter() { FilterRegistrationBean fb =
	  new FilterRegistrationBean(); fb.setFilter(new JWTFilter());
	  fb.addUrlPatterns("/api/v1.0/*"); return fb; }
	 

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyStockManagementAppApplication.class, args);
	}

}
