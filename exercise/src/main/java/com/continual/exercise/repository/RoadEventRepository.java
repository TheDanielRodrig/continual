package com.continual.exercise.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.continual.exercise.entity.RoadEventEntity;

public interface RoadEventRepository extends MongoRepository<RoadEventEntity, ObjectId> {
	@Query("{'roadId' : ?0, 'timestamp' : {$gte: ?1}, 'timestamp' : {$lt: ?2}}")
	List<RoadEventEntity> findByRoadAndTime(long roadId, long startTime, long endTime);
}
