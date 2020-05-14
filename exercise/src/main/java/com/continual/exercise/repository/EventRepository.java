package com.continual.exercise.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.continual.exercise.entity.EventEntity;

public interface EventRepository extends MongoRepository<EventEntity, String> {

}
