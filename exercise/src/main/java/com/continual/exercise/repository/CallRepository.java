package com.continual.exercise.repository;

import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.continual.exercise.entity.CallEntity;

public interface CallRepository extends MongoRepository<CallEntity, String> {
	
	@CountQuery("{'roadId' : ?0, 'timestamp' : {$gte: ?1}, 'timestamp' : {$lt: ?2}}")
	long countByRoadAndTime(long roadId, long startTime, long endTime);
}
