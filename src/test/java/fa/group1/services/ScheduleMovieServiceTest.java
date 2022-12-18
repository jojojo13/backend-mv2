package fa.group1.services;

import fa.group1.dto.ScheduleShowDTO;
import fa.group1.dto.ScheduleTimeDTO;
import fa.group1.entities.Schedule;
import fa.group1.repository.ScheduleMovieRepository;
import fa.group1.services.impl.ScheduleMovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleMovieServiceTest {
    @Mock
    ScheduleMovieRepository scheduleMovieRepository;
    @InjectMocks
    ScheduleMovieServiceImpl scheduleMovieService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void getAllScheduleMovieByDate() {
        List<ScheduleTimeDTO> scheduleTimeDTOList=new ArrayList<>();
        scheduleTimeDTOList.add(new ScheduleTimeDTO("hanh dong", "2h", 1,1));
        scheduleTimeDTOList.add(new ScheduleTimeDTO("hanh dong", "2h", 1,1));
        scheduleTimeDTOList.add(new ScheduleTimeDTO("hanh dong", "2h", 1,1));
        when(scheduleMovieRepository.getAllScheduleMovieByDate(LocalDate.of(2022,10,10))).thenReturn(scheduleTimeDTOList);
        List<ScheduleShowDTO> scheduleShowList = new ArrayList<>();
        Map<String, List<ScheduleTimeDTO>> shows = scheduleTimeDTOList.stream()
                .collect(Collectors.groupingBy(ScheduleTimeDTO::getMovieName));
        shows.forEach((k, v) ->
                scheduleShowList.add(new ScheduleShowDTO(k, v))
        );
        List<ScheduleShowDTO> scheduleShowList1=scheduleMovieService.getAllScheduleMovieByDate(LocalDate.of(2022,10,10));
        System.out.println(scheduleShowList1);
        assertNotNull(scheduleShowList1);
    }
    @Test
    public void getAllScheduleMovieByDateNull() {
        List<ScheduleTimeDTO> scheduleTimeDTOList=new ArrayList<>();
        try{
            when(scheduleMovieRepository.getAllScheduleMovieByDate(LocalDate.of(2022,10,10))).thenReturn(scheduleTimeDTOList);

        }catch (Exception e){
            final String msg = "User not found";
            Assertions.assertEquals(msg, e.getMessage());
        }

    }
}
