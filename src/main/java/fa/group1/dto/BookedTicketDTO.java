package fa.group1.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedTicketDTO {

	private String movieName;
	private LocalDate bookingDate;
	private Double price;
	private Integer status;
}
