package fa.group1.services;

import fa.group1.dto.BookingSeatDTO;
import fa.group1.entities.*;
import fa.group1.repository.*;
import fa.group1.services.impl.ScheduleSeatServiceImpl;
import fa.group1.services.impl.TicketServiceImpl;
import fa.group1.utils.TokenAuthenticationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleSeatServiceTest {
    @Mock
    private ScheduleSeatRepository scheduleSeatRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private ScheduleMovieRepository scheduleMovieRepository;

    @InjectMocks
    private ScheduleSeatServiceImpl scheduleSeatService;
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void findAllScheduleSeat() {
        List<ScheduleSeat> scheduleSeatList = new ArrayList<ScheduleSeat>();

        scheduleSeatList.add(new ScheduleSeat(1, null, 1, 25000.00, null));
        when(scheduleSeatRepository.findAll()).thenReturn(scheduleSeatList);
        List<ScheduleSeat> result = scheduleSeatService.findAllScheduleSeat();
        assertEquals(1, result.size());
    }
    @Test
    public void addNewScheduleSeat() {
        List<ScheduleSeat> scheduleSeats = new ArrayList<>();
        List<Seat> seats=new ArrayList<Seat>();
        Seat seat= new Seat(1, null,"10",2,1,1,"vip");
        seats.add(seat);
        Ticket ticket=new Ticket(1, 25000.00,1,null, LocalDate.of(2022,9,9));
        BookingSeatDTO bookingSeat=new BookingSeatDTO(25000.00,seats);
        User user=new User(1, "Nong tai","Lao cai", LocalDate.of(2022,9,9),"nongtai1@gmail.com","Nong Duc Tai",1,"01234567","abcde","0123456789","0123456789",LocalDate.of(2022,8,9),245.00, null,1,null);
        ScheduleMovie scheduleMovie=new ScheduleMovie(1,null,null,null,LocalDate.of(2022,9,9),null);
        Authentication authentication = TokenAuthenticationUtils.getAuthentication("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA");
        when(userRepository.findByUsername(authentication.getPrincipal().toString())).thenReturn(Optional.of(user));
        ticket.setTicketType(0);
        ticket.setPrice(bookingSeat.getPrice());
        ticket.setUser(user);
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        ScheduleSeat scheduleSeat = new ScheduleSeat();
        scheduleSeat.setScheduleSeatId(1);
        scheduleSeat.setSeat(seat.getSeatId());
        scheduleSeat.setTicket(ticket);
        when(scheduleMovieRepository.findById(1)).thenReturn(Optional.of(scheduleMovie));
        scheduleSeat.setScheduleMovie(scheduleMovie);
        scheduleSeats.add(scheduleSeat);
        when(scheduleSeatRepository.saveAll(scheduleSeats)).thenReturn(scheduleSeats);
        when(scheduleSeatService.addNewScheduleSeat(scheduleMovie.getScheduleMovieId(),"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA",bookingSeat)).thenReturn(scheduleSeats);
        Assert.assertEquals(1, scheduleSeats.size());
    }

}
