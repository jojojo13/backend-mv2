package fa.group1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.group1.entities.Seat;
import fa.group1.services.SeatService;

@RestController
@RequestMapping(path = "api/seats")
@CrossOrigin
public class SeatController {

	@Autowired
	SeatService seatService;

	@GetMapping("")
	public ResponseEntity<?> getSeats(@RequestParam("scheduleMovieId")Integer scheduleMovieId) {

		List<Seat> list = seatService.getAllSeatsByScheduleMovieID(scheduleMovieId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("empty")
	public ResponseEntity<?> getSeatsEmpty(@RequestParam("scheduleMovieId")Integer scheduleMovieId) {

		List<Seat> list = seatService.getAllSeatIsEmpty(scheduleMovieId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
