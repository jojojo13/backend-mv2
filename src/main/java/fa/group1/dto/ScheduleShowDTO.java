package fa.group1.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class 	ScheduleShowDTO {

	private String movieName;
	private List<ScheduleTimeDTO> scheduleTimeDTO;
}
