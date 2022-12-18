package fa.group1.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fa.group1.dto.ScheduleShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fa.group1.dto.ScheduleShowDTO;
import fa.group1.dto.ScheduleTimeDTO;
import fa.group1.services.ScheduleMovieService;

@RestController
@RequestMapping(path="api/scheduleMovie")
@CrossOrigin
public class ScheduleMovieController {
	
	@Autowired
	private ScheduleMovieService scheduleMovieService;
	
	@GetMapping("")
	public ResponseEntity<?> getSchedule(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		List<ScheduleShowDTO> scheduleShowList = scheduleMovieService.getAllScheduleMovieByDate(date);

		return new ResponseEntity<>(scheduleShowList, HttpStatus.OK);
	}
	
}
