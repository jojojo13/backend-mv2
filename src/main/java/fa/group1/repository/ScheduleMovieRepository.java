package fa.group1.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.group1.dto.ScheduleTimeDTO;
import fa.group1.entities.ScheduleMovie;


public interface ScheduleMovieRepository extends JpaRepository<ScheduleMovie, Integer> {
	@Query(" select new fa.group1.dto.ScheduleTimeDTO(m.movie_name_vn,s.scheduleTime,m.movieId,sm.scheduleMovieId) " 
			+ "FROM ScheduleMovie sm "
			+ "join Movie m on sm.movie.movieId = m.movieId " 
			+ "join Schedule s on sm.schedule.scheduleId = s.scheduleId "
			+ "where sm.date = :date ")
	List<ScheduleTimeDTO> getAllScheduleMovieByDate(LocalDate date);
}
