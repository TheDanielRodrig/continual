package com.continual.exercise.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.continual.exercise.entity.CalculatedEventScoreEntity;
import com.continual.exercise.entity.EventEntity;
import com.continual.exercise.entity.EventEntity.Rank;
import com.continual.exercise.repository.CalculatedEventScoreRepository;
import com.continual.exercise.repository.CallRepository;
import com.continual.exercise.repository.EventRepository;

@Component
public class CalibrationService {
	private EventRepository eventRepo;
	private CallRepository callRepo;
	private RoadEventService roadEventService;
	private CalculatedEventScoreRepository calculatedRepo;
	
	public CalibrationService(@Autowired EventRepository eventRepo, @Autowired CallRepository callRepo,
			@Autowired RoadEventService roadEventService, @Autowired CalculatedEventScoreRepository calculatedRepo) {
		this.eventRepo = eventRepo;
		this.callRepo = callRepo;
		this.roadEventService = roadEventService;
		this.calculatedRepo = calculatedRepo;
	}
	
	public void calibrate(long roadId, long startTime, long endTime) {
		long callsCount = callRepo.countByRoadAndTime(roadId, startTime, endTime);
		Map<String, Long> roadEvents = roadEventService.countByEventRoadAndTime(roadId, startTime, endTime);
		Map<String, List<Rank>> eventsById = eventRepo.findAll().stream().collect(Collectors.toMap(EventEntity::getId, event-> event.getRankings()));
		
		CalculatedEventScoreEntity calibratedData = new CalculatedEventScoreEntity(startTime, endTime, roadId);
		
		for (Entry<String, Long> eventEntry : roadEvents.entrySet()) {
			long eventCount = eventEntry.getValue();
			long percentile = eventCount / callsCount * 100;
			String rankId = eventsById.get(eventEntry.getKey()).stream()
					.filter(rank-> percentile >= rank.getMin() && percentile < rank.getMax())
					.findFirst().get().getId();
			
			calibratedData.getEventsScore().put(eventEntry.getKey(), rankId);
		}
		
		calculatedRepo.save(calibratedData);
	}
	
	public List<CalculatedEventScoreEntity> getRoadRankings(long roadId){
		return calculatedRepo.findByRoad(roadId);
	}
}
 