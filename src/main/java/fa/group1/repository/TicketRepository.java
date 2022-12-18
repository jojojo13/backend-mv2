package fa.group1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.group1.dto.BookedTicketDTO;
import fa.group1.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query(value = "exec BookingTicketForAdmin", nativeQuery = true)
	List<Object> findAllTicket();

	@Query(" select new fa.group1.dto.BookedTicketDTO(m.movie_name_vn, t.bookingDate,t.price,t.ticketType) "
			+ "FROM Ticket t " + "join ScheduleSeat ss on ss.ticket.ticketId = t.ticketId "
			+ "join ScheduleMovie sm on sm.scheduleMovieId = ss.scheduleMovie.scheduleMovieId "
			+ "join Movie m on sm.movie.movieId = m.movieId " + "where t.user.accountId = :accountId " +
			" group by m.movie_name_vn, t.bookingDate,t.price,t.ticketType")
	List<BookedTicketDTO> getAllBookedTicket(Integer accountId);
}
