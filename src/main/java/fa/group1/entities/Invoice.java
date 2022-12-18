package fa.group1.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Invoice")
@Data
public class Invoice implements Serializable {
	@Id
	@Column(name = "invoice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer invoiceId;

	@Column(name = "add_core")
	private Integer addCore;

	@Column(name = "booking_date")
	private LocalDate bookingDate;

	@Column(name = "movie_name")
	private Integer movieName;

	@Column(name = "status", columnDefinition = "int default(1)")
	private Integer status;

	@Column(name = "total_money")
	private Integer totalMoney;

	@Column(name = "use_score")
	private Integer useScore;

	@OneToOne // Đánh dấu có mỗi quan hệ 1-1 với Ticket ở phía dưới
	@JoinColumn(name = "ticket_id") // Liên kết với nhau qua khóa ngoại ticket_id
	private Ticket ticket;
}
