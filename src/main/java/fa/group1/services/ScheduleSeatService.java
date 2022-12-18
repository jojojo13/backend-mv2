package fa.group1.services;

import java.util.List;

import fa.group1.dto.BookingSeatDTO;
import fa.group1.entities.ScheduleSeat;
import fa.group1.entities.Seat;

public interface ScheduleSeatService {

	List<ScheduleSeat> findAllScheduleSeat();

	List<ScheduleSeat> addNewScheduleSeat(Integer scheduleMovieId, String token, BookingSeatDTO seats);
}
