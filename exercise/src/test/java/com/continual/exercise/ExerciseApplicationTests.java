package com.continual.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = { ExerciseApplication.class, TestMongoConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
class ExerciseApplicationTests {
	
	private static final String RANKINGS_REQUEST_ROUTE = "/roads/%d/rankings";
	private static final String INIT_REQUEST_ROUTE = "/roads/initData";

	@Autowired
	private TestRestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@Test
	void contextLoads() {
		restTemplate.postForEntity(INIT_REQUEST_ROUTE, null, ResponseEntity.class);
		ResponseEntity<?> response =  restTemplate.getForEntity(String.format(RANKINGS_REQUEST_ROUTE, 1), Object.class);
		System.out.println(response);
	}

}
