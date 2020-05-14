package com.continual.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.continual.exercise.entity.CalculatedEventScoreEntity;
import com.continual.exercise.service.CalibrationService;
import com.continual.exercise.service.InitDataService;

@RestController
@RequestMapping("/roads")
public class RoadsController {
	
	private InitDataService initService;
	private CalibrationService calibrationService;
	
	public RoadsController(@Autowired InitDataService initService, @Autowired CalibrationService calibrationService) {
		this.initService = initService;
		this.calibrationService = calibrationService;
	}

	@GetMapping("/{id}/rankings")
	public ResponseEntity<List<CalculatedEventScoreEntity>> roadRanks(@PathVariable("id")Long id){
		List<CalculatedEventScoreEntity> rankings = calibrationService.getRoadRankings(id);
		return ResponseEntity.ok(rankings);
	}
	
	@PostMapping("/initData")
	public ResponseEntity<Void> initData(){
		initService.init();
		
		for (int i = 0; i < 14; i++) {
			calibrationService.calibrate(i, System.currentTimeMillis(), System.currentTimeMillis() + 7l * InitDataService.MILLIS_PER_DAY);
		}
		
		return ResponseEntity.ok().build();
	}
}
