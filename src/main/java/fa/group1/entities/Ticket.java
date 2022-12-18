package fa.group1.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Entity
@Table(name = "Ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {

	@Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketId;

	@Column(name = "price")
	private Double price;

	@Column(name = "ticket_type", columnDefinition = "int default(0)")
	private Integer ticketType;

	@ManyToOne
	@JoinColumn(name = "account_id") // thông qua khóa ngoại
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonBackReference
//	private User account;

	private User user;
	@Column(name = "booking_date")
	private LocalDate bookingDate;

}
