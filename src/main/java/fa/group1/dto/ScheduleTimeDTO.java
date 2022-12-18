package fa.group1.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import fa.group1.entities.ScheduleMovie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTimeDTO {

//	@JsonFormat(pattern = "yyyy-MM-dd")
//	private LocalDate date;

	private String movieName;

	private String scheduleTime;

	private Integer movieId;
	
	private Integer scheduleMovieId;
}
