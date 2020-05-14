package com.continual.exercise.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.continual.exercise.entity.RoadEntity;

public interface RoadRepository extends MongoRepository<RoadEntity, Long> {

}
