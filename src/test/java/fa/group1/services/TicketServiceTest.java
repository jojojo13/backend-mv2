package fa.group1.services;

import fa.group1.dto.BookedTicketDTO;
import fa.group1.entities.Ticket;
import fa.group1.entities.User;
import fa.group1.repository.TicketRepository;
import fa.group1.repository.UserRepository;
import fa.group1.services.impl.TicketServiceImpl;
import fa.group1.utils.TokenAuthenticationUtils;
import org.junit.Before;
import org.junit.Test;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testFindAllTicket() {
        List<Object> ticketList = new ArrayList<Object>();
        ticketList.add(new Ticket(1, 25000.00,1,null, LocalDate.of(2022,9,9)));
        ticketList.add(new Ticket(2, 25000.00,1,null, LocalDate.of(2022,9,9)));
        ticketList.add(new Ticket(3, 25000.00,1,null, LocalDate.of(2022,9,9)));
        when(ticketRepository.findAllTicket()).thenReturn(ticketList);
        List<Object> result = ticketService.findAllTicket();
        assertEquals(3, result.size());
    }
    @Test
    public void getAllBookedTicketDTOs() {
        List<BookedTicketDTO> ticketDTOList = new ArrayList<BookedTicketDTO>();
        ticketDTOList.add(new BookedTicketDTO("hanh dong", LocalDate.of(2022,9,9),25000.00, 1));
        ticketDTOList.add(new BookedTicketDTO("hanh dong", LocalDate.of(2022,9,9),25000.00, 1));
        ticketDTOList.add(new BookedTicketDTO("hanh dong", LocalDate.of(2022,9,9),25000.00, 1));
        Optional<User> user= Optional.of(new User(1, "Nong tai", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, null, 1, null));
        Authentication authentication = TokenAuthenticationUtils.getAuthentication("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA");
        when(userRepository.findByUsername(authentication.getPrincipal().toString())).thenReturn(user);
        when(ticketRepository.getAllBookedTicket(user.get().getAccountId())).thenReturn(ticketDTOList);
        List<BookedTicketDTO> result = ticketService.getAllBookedTicketDTOs("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA");
        assertNotNull(result);
    }
}
