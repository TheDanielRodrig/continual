package com.continual.exercise;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { ExerciseApplication.class, TestMongoConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
class ExerciseApplicationTests {
	
	private static final String RANKINGS_REQUEST_ROUTE = "/roads/%d/rankings";
	private static final String INIT_REQUEST_ROUTE = "/roads/initData";

	@Autowired
	private TestRestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	@Test
	public void testRoads() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		restTemplate.postForEntity(INIT_REQUEST_ROUTE, null, ResponseEntity.class);
		
		ResponseEntity<List> response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 1), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 2), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 3), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 4), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 5), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 6), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 7), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 8), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
		
		response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 9), List.class);
		System.out.println(mapper.writeValueAsString(response.getBody()));
	}

}
