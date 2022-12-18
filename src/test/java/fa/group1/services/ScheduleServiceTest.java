package fa.group1.services;

import fa.group1.entities.Schedule;
import fa.group1.entities.Type;
import fa.group1.repository.ScheduleRepository;
import fa.group1.services.impl.ScheduleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
public class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testGetAllToDo() {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        scheduleList.add(new Schedule(1, "2022-10-10", null));
        scheduleList.add(new Schedule(2, "2022-09-09", null));
        scheduleList.add(new Schedule(3, "2022-08-08", null));
        when(scheduleRepository.findAll()).thenReturn(scheduleList);
        List<Schedule> result = scheduleService.getAllSchedule();
        assertEquals(3, result.size());
    }
    @Test
    public void testGetAllTypeNull() {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        try{
            when(scheduleRepository.findAll()).thenReturn(scheduleList);
            List<Schedule> result = scheduleService.getAllSchedule();
        }catch (Exception e){
            final String msg = "Not found any schedule";
            Assertions.assertEquals(msg, e.getMessage());
        }
    }
}
