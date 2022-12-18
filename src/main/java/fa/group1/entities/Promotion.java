package fa.group1.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Promotion")
@Data
public class Promotion implements Serializable {
	
	@Id
	@Column(name = "promotion_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer promotionId;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "disscount_level")
	private Integer disscountLevel;
	
	@Column(name = "end_time")
	private LocalDate endTime;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "title")
	private String title;
}
