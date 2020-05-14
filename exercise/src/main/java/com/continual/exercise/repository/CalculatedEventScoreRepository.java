package com.continual.exercise.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.continual.exercise.entity.CalculatedEventScoreEntity;

public interface CalculatedEventScoreRepository extends MongoRepository<CalculatedEventScoreEntity, ObjectId> {
	@Query("{roadId : ?0}")
	public List<CalculatedEventScoreEntity> findByRoad(long roadId);
}
