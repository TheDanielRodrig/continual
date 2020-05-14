package com.continual.exercise.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event")
public class EventEntity {
	@Id
	String id;
	
	List<Rank> rankings;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Rank{
		String id;
		int min;
		int max;
	}
}
