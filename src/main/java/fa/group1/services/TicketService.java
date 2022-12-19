package fa.group1.services;

import java.util.List;

import fa.group1.dto.BookedTicketDTO;
import fa.group1.dto.TicketByMonth;
import fa.group1.entities.Ticket;

public interface TicketService {

	List<Object> findAllTicket();
//	Ticket addTicket(Ticket ticket);
	List<BookedTicketDTO> getAllBookedTicketDTOs(String token);
	Ticket getTicketByID(Integer id);
	Ticket updateConfirmTicket(Integer ticketID);
	List<TicketByMonth> getTotalTicketByMonth();
}
