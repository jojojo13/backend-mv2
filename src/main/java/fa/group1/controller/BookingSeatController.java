package fa.group1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fa.group1.dto.BookingSeatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fa.group1.entities.Seat;
import fa.group1.services.ScheduleSeatService;
import fa.group1.services.SeatService;

@RestController
@RequestMapping(value = "api/booking")
@CrossOrigin
public class BookingSeatController {

	@Autowired
	private SeatService seatService;

	@Autowired
	private ScheduleSeatService scheduleSeatService;

	@GetMapping("{id}")
	public ResponseEntity<List<Seat>> getAllSeatIsEmpty(@PathVariable("id") Integer id) {
		List<Seat> list = seatService.getAllSeatIsEmpty(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addNewSeat(@RequestParam("scheduleMovieId") Integer scheduleMovieId,
			@RequestHeader(name = "Authorization") String token,  @RequestBody BookingSeatDTO bookingSeat) {
		Map<String, Object> response = new HashMap<>();
		scheduleSeatService.addNewScheduleSeat(scheduleMovieId, token, bookingSeat);
		response.put("message", "Create successfully!!!");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
