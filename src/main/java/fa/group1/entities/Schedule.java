package fa.group1.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "schedule_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleId;

	@Column(name = "schedule_time")
	private String scheduleTime;

//	@JsonBackReference
	@OneToMany(mappedBy = "schedule")
//	@JsonManagedReference
	@JsonIgnore
	private List<ScheduleMovie> scheduleMovies;

}
