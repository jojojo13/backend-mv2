package fa.group1.services;

import java.time.LocalDate;
import java.util.List;

import fa.group1.dto.ScheduleShowDTO;
import fa.group1.dto.ScheduleTimeDTO;

public interface ScheduleMovieService {
	List<ScheduleShowDTO> getAllScheduleMovieByDate(LocalDate date);
}
