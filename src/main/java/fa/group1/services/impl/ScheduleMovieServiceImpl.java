package fa.group1.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fa.group1.dto.ScheduleShowDTO;
import fa.group1.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.group1.dto.ScheduleTimeDTO;
import fa.group1.entities.ScheduleMovie;
import fa.group1.repository.ScheduleMovieRepository;
import fa.group1.services.ScheduleMovieService;

@Service
public class ScheduleMovieServiceImpl implements ScheduleMovieService{

	@Autowired
	private ScheduleMovieRepository scheduleMovieRepository;
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleMovieServiceImpl.class);
	
	@Override
	public List<ScheduleShowDTO> getAllScheduleMovieByDate(LocalDate date) {
		List<ScheduleTimeDTO> list = scheduleMovieRepository.getAllScheduleMovieByDate(date);
		if(list.isEmpty()){
			LOGGER.error("No movie in this date");
			throw new ResourceNotFoundException("No movie in this date");
		}
		List<ScheduleShowDTO> scheduleShowList = new ArrayList<>();
		Map<String, List<ScheduleTimeDTO>> shows = list.stream()
				.collect(Collectors.groupingBy(ScheduleTimeDTO::getMovieName));

		shows.forEach((k, v) ->
				scheduleShowList.add(new ScheduleShowDTO(k, v))
		);
		return scheduleShowList;

	}

}
