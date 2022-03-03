package com.example.demo.companyController;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;




@RestController
public class consumerController {
	
	//@Autowired
	//private DiscoveryClient discoveryClient;
	
	/*
	 * public void getToken() throws RestClientException,Exception {
	 * List<ServiceInstance> instances =
	 * discoveryClient.getInstances("JWT-producer");
	 * 
	 * ServiceInstance serviceInstance = instances.get(0); String baseUrl =
	 * serviceInstance.getUri().toString();
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); baseUrl =
	 * baseUrl+"auth/v1/login"; ResponseEntity<String> responseEntity = null;
	 * 
	 * try { responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST,
	 * getHeaders(),String.class); } catch (Exception e) { e.printStackTrace(); }
	 * System.out.println(responseEntity.getBody()); }
	 * 
	 * private static HttpEntity<?> getHeaders() throws IOException , Exception{
	 * 
	 * HttpHeaders headers = new HttpHeaders(); headers.set("Accept",
	 * MediaType.APPLICATION_JSON_VALUE); return new HttpEntity<>(headers);
	 * 
	 * }
	 */

}
