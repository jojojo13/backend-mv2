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
import lombok.*;

@Entity
@Table(name = "schedule_seat", uniqueConstraints = {
		@UniqueConstraint(name = "Unique_scheduleMovie_seat", columnNames = { "schedule_movie_id", "seat_id" }) })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSeat implements Serializable {

	@Id
	@Column(name = "schedule_seat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleSeatId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "schedule_movie_id") // thông qua khóa ngoại ticket_id
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private ScheduleMovie scheduleMovie;
	
	@Column(name = "seat_id")
	private Integer seat;
	
	@Column(name = "price")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "ticket_id") // thông qua khóa ngoại ticket_id
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Ticket ticket;

}
