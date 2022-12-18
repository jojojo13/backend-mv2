package fa.group1.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class BookingListDTO {

	private Integer ticketId;

	private Integer accountId;

	private String fullName;

	private String identityCard;

	private String phoneNumber;

	private String movieNameVN;
	private String date;
	private String seat;
	private Integer ticketStatus;
	private Integer movie_id;


	private String time;






}
