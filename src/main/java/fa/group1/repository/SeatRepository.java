package fa.group1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.group1.entities.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	@Query(value="SELECT distinct * FROM  seat s  INNER JOIN schedule_movie sm ON s.cinima_room_id = sm.cinema_room_id "
			+ "WHERE sm.schedule_movie_id=?1 and s.seat_id NOT IN (select ss.seat_id from schedule_seat ss where ss.schedule_movie_id=?1)"
			,nativeQuery = true)
	List<Seat> listSeatIsEmpty(Integer scheduleMovieId);
	@Query(value = "select * from seat where cinima_room_id=?1",nativeQuery = true)
	List<Seat> findByCinemaRoomID(Integer cinemaID);
	
	@Query(value = "select * from seat s inner join schedule_movie sm on sm.cinema_room_id = s.cinima_room_id "
			+ "where sm.schedule_movie_id=?1",nativeQuery = true)
	List<Seat> findByScheduleMovieID(Integer scheduleMovieId);
}
