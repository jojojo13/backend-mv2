package fa.group1.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seat", uniqueConstraints = {
		@UniqueConstraint(name = "Unique_seat", columnNames = { "seat_colume", "seat_row","cinima_room_id" }) })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat implements Serializable {

	@Id
	@Column(name = "seat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seatId;


	@ManyToOne
	@JoinColumn(name = "cinima_room_id")
	private CinemaRoom cinemaRoom;

	@Column(name = "seat_colume", length = 5)
	private String seatColume;

	@Column(name = "seat_row")
	private Integer seatRow;

	@Column(name = "seat_status", columnDefinition = "int default(1)")
	private Integer seatStatus;

	@Column(name = "seat_type", columnDefinition = "int default(0)")
	private Integer seatType;
	@Column(name = "position")
	private String position;

}
