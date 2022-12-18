package fa.group1.services;

import fa.group1.entities.Seat;
import fa.group1.repository.SeatRepository;
import fa.group1.services.impl.SeatServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(SpringJUnit4ClassRunner.class)
public class SeatServiceTest {
    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatServiceImpl seatService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void getAllSeatIsEmpty() {
        List<Seat> seatList = new ArrayList<Seat>();
        seatList.add(new Seat(1, null,"10",2,1,1,"vip"));
        when(seatRepository.listSeatIsEmpty(1)).thenReturn(seatList);
        List<Seat> result = seatService.getAllSeatIsEmpty(1);
        assertEquals(1, result.size());
    }
    @Test
    public void getAllSeatsByScheduleMovieID() {
        List<Seat> seatList = new ArrayList<Seat>();
        seatList.add(new Seat(1, null,"10",2,1,1,"vip"));
        when(seatRepository.findByScheduleMovieID(1)).thenReturn(seatList);
        List<Seat> result = seatService.getAllSeatsByScheduleMovieID(1);
        assertEquals(1, result.size());
    }
}
