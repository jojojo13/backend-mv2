package fa.group1.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "schedule_movie", uniqueConstraints = {
		@UniqueConstraint(name = "Unique_schedule_show", columnNames = { "scheduleId", "date", "cinema_room_id" }),
		@UniqueConstraint(name = "Unique_schedule_show_movie", columnNames = { "scheduleId", "date", "movieId" }) })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "schedule", scope = Schedule.class)
public class ScheduleMovie implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "schedule_movie_id")
//	private Integer scheduleMovieId;
//
//	@JsonManagedReference
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "scheduleId")
//	private Schedule schedule;
//
//	@JsonBackReference
//	@ToString.Exclude
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Movie.class)
//	@JoinColumn(name = "movieId")
//	private Movie movie;
//
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	@ToString.Exclude
//	@JoinColumn(name = "cinema_room_id") // thông qua khóa ngoại address_id
//	private CinemaRoom cinemaRoom;
//
//	@Column(name = "date", nullable = false)
//	private LocalDate date;
//
////	@JsonBackReference
//	@ToString.Exclude
//	@OneToMany(mappedBy = "scheduleMovie")
//	private List<ScheduleSeat> scheduleSeats;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_movie_id")
	private Integer scheduleMovieId;

//	@JsonBackReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scheduleId")
	private Schedule schedule;

	@ToString.Exclude
	@ManyToOne()
	@JsonBackReference
	@JoinColumn(name = "movieId")
	private Movie movie;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@ToString.Exclude
	@JoinColumn(name = "cinema_room_id") // thông qua khóa ngoại address_id
	private CinemaRoom cinemaRoom;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	// @JsonBackReference
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "scheduleMovie")
	private List<ScheduleSeat> scheduleSeats;

}
