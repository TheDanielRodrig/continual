package com.continual.exercise.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.continual.exercise.entity.CallEntity;
import com.continual.exercise.entity.EventEntity;
import com.continual.exercise.entity.EventEntity.Rank;
import com.continual.exercise.entity.RoadEntity;
import com.continual.exercise.entity.RoadEventEntity;
import com.continual.exercise.repository.CallRepository;
import com.continual.exercise.repository.EventRepository;
import com.continual.exercise.repository.RoadEventRepository;
import com.continual.exercise.repository.RoadRepository;

@Component
public class InitDataService {
	private EventRepository eventRepo;
	private CallRepository callRepo;
	private RoadEventRepository roadEventRepo;
	private RoadRepository roadRepo;
	
	private static final RandomDataGenerator random = new RandomDataGenerator();
	public static final long MILLIS_PER_DAY = 1000l * 60l * 60l * 24l;
	
	public InitDataService(@Autowired EventRepository eventRepo,
			@Autowired CallRepository callRepo,
			@Autowired RoadEventRepository roadEventRepo,
			@Autowired RoadRepository roadRepo) {
		this.eventRepo = eventRepo;
		this.callRepo = callRepo;
		this.roadEventRepo = roadEventRepo;
		this.roadRepo = roadRepo;
	}
	
	public void init() {
		initRoads();
		initEvents();
		initCalls();
		initRoadEvents();
	}
	
	private void initCalls() {
		long now = System.currentTimeMillis();
		
		
		List<CallEntity> calls = new ArrayList<>(700);
		
		for (int dayIndex = 0; dayIndex < 8; dayIndex++) {
			for (long roadIndex = 0; roadIndex < 14; roadIndex++) {
				for (int callIndex = 0; callIndex < 100; callIndex++) {
					calls.add(new CallEntity(new ObjectId(), roadIndex, random.nextLong(now, now + (MILLIS_PER_DAY * dayIndex + 1))));
				}
			}
		}
		
		callRepo.saveAll(calls);
	}
	
	private void initRoadEvents() {
		long now = System.currentTimeMillis();
		String[] eventIds = new String[] {"EVENT_1", "EVENT_2", "EVENT_3", "EVENT_4"};
		
		List<RoadEventEntity> events = new ArrayList<>();
		
		for (int dayIndex = 0; dayIndex < 8; dayIndex++) {
			for (long roadIndex = 0; roadIndex < 14; roadIndex++) {
				for (int eventIndex = 0; eventIndex < 4; eventIndex++) {
					for (int eventOccuranceIndex = 0; eventOccuranceIndex < random.nextInt(0, 100); eventOccuranceIndex++) {
						events.add(new RoadEventEntity(new ObjectId(), roadIndex, random.nextLong(now, now + (MILLIS_PER_DAY * dayIndex + 1)),eventIds[eventIndex]));
					}
				}
			}
		}
		
		roadEventRepo.saveAll(events);
	}
	
	private void initRoads() {
		List<RoadEntity> roads = new ArrayList<>();
		
		for(long roadIndex = 0; roadIndex < 14; roadIndex++) {
			roads.add(new RoadEntity(roadIndex));
		}
		
		roadRepo.saveAll(roads);
	}
	
	private void initEvents() {
		eventRepo.save(new EventEntity("EVENT_1", Arrays.asList(new Rank("Rank 1", 90, 100), new Rank("Rank 2", 50, 90), new Rank("Rank 3", 15, 50), new Rank("Rank 4", 0, 15))));
		eventRepo.save(new EventEntity("EVENT_2", Arrays.asList(new Rank("Rank 1", 65, 100), new Rank("Rank 2", 25, 65), new Rank("Rank 3", 10, 25), new Rank("Rank 4", 0, 10))));
		eventRepo.save(new EventEntity("EVENT_3", Arrays.asList(new Rank("Rank 1", 0, 10), new Rank("Rank 2", 10, 50), new Rank("Rank 3", 50, 85), new Rank("Rank 4", 85, 100))));
		eventRepo.save(new EventEntity("EVENT_4", Arrays.asList(new Rank("Rank 1", 0, 65), new Rank("Rank 2", 65, 75), new Rank("Rank 3", 75, 90), new Rank("Rank 4", 90, 100))));
	}
}
