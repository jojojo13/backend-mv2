package fa.group1.services;

import java.util.List;

import fa.group1.entities.Seat;

public interface SeatService {

	List<Seat> getAllSeatIsEmpty(Integer scheduleMovieId);
	List<Seat> getAllSeatsByScheduleMovieID(Integer scheduleMovieId);
}
