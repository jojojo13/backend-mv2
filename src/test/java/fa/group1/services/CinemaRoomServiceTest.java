package fa.group1.services;

import fa.group1.entities.CinemaRoom;
import fa.group1.entities.Role;
import fa.group1.entities.Seat;
import fa.group1.entities.User;
import fa.group1.repository.CinemaRoomRepository;
import fa.group1.repository.SeatRepository;
import fa.group1.services.impl.CinemaRoomServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CinemaRoomServiceTest {
    @Mock
    private CinemaRoomRepository cinemaRoomRepository;
    @Mock
    private SeatRepository seatRepository;
    @InjectMocks
    private CinemaRoomServiceImpl cinemaRoomService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testGetAllCinemaRoom() {
        Pageable pageable = PageRequest.of(0, 3);
        CinemaRoom cinemaRoom= new CinemaRoom(1,"Cinema room 1",null,null);
        when(cinemaRoomRepository.findAll(pageable)).thenReturn(new PageImpl<CinemaRoom>(Arrays.asList(cinemaRoom)));
        Page<CinemaRoom> result=cinemaRoomService.getAllCinemaRoom(0,3);
        assertEquals(1,result.getTotalElements());
    }

    @Test
    public void getAllSeatByCinemaID() {
        List<Seat> list = new ArrayList<>() ;
        list.add(new Seat());
       CinemaRoom cinemaRoom = new CinemaRoom();
       cinemaRoom.setCinemaRoomId(1);
       cinemaRoom.setCinemaRoomName("room1");
       cinemaRoom.setSeats(list);
       when(seatRepository.findByCinemaRoomID(1)).thenReturn(list);
       when(cinemaRoomService.getAllSeatByCinemaID(1)).thenReturn(list);
       assertEquals(1L,list.size());
    }
    @Test
    public void getAllSeatByCinemaIDIsNull() {
        List<Seat> list = new ArrayList<>() ;
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setCinemaRoomId(1);
        cinemaRoom.setCinemaRoomName("room1");
        try {
            when(seatRepository.findByCinemaRoomID(1)).thenReturn(list);
            when(cinemaRoomService.getAllSeatByCinemaID(1)).thenReturn(list);
        } catch (Exception e) {
            final String msg = "No seat in this cinema room";
            Assertions.assertEquals(msg, e.getMessage());
        }

    }

    @Test
    public void updateSeatInCinemaRoom() {
        List<Seat> list = new ArrayList<>();
        list.add(new Seat());
        when(cinemaRoomService.updateSeatInCinemaRoom(list)).thenReturn(list);
    }
}
