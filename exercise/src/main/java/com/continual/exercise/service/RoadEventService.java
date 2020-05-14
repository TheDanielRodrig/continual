package com.continual.exercise.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.continual.exercise.entity.RoadEventEntity.F;

import lombok.Data;

@Component
public class RoadEventService {
	private MongoTemplate mongoTemplate;
	
	public RoadEventService(@Autowired MongoDbFactory mongoFactory) {
		this.mongoTemplate = new MongoTemplate(mongoFactory);
	}
	
	public Map<String, Long> countByEventRoadAndTime(long roadId, long startTime, long endTime){
		MatchOperation match = Aggregation.match(Criteria.where(F.roadId).is(roadId).andOperator(Criteria.where(F.timestamp).gte(startTime), Criteria.where(F.timestamp).lt(endTime)));
		GroupOperation group = Aggregation.group(F.eventId).count().as("count");
		
		Map<String, Long> countByEvent = new HashMap<>();
		mongoTemplate.aggregate(Aggregation.newAggregation(match, group), "roadEvent", EventCount.class).forEach(count-> countByEvent.put(count.id, count.count));
		
		return countByEvent;
	}
	
	@Data
	public static class EventCount{
		String id;
		long count;
	}
}
