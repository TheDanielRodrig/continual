package com.continual.exercise.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants(innerTypeName = "F")
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "roadEvent")
public class RoadEventEntity {
	@Id
	ObjectId id;
	
	Long roadId;
	long timestamp;
	String eventId;
}
