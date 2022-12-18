package fa.group1.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.group1.entities.Seat;
import fa.group1.repository.SeatRepository;
import fa.group1.services.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public List<Seat> getAllSeatIsEmpty(Integer scheduleMovieId) {
		return seatRepository.listSeatIsEmpty(scheduleMovieId);
	}

	@Override
	public List<Seat> getAllSeatsByScheduleMovieID(Integer scheduleMovieId) {
		
		return seatRepository.findByScheduleMovieID(scheduleMovieId);
	}

}
