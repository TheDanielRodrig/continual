package com.continual.exercise.entity;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "eventScore")
public class CalculatedEventScoreEntity {
	@Id
	@JsonIgnore
	ObjectId id;
	
	long startTime;
	long endTime;
	
	@JsonIgnore
	Long roadId;
	
	Map<String, String> eventsScore = new HashMap<>();
	
	public CalculatedEventScoreEntity(long startTime, long endTime, Long roadId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.roadId = roadId;
	}
}
