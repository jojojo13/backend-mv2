package fa.group1.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cinema_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRoom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cinema_room_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cinemaRoomId;

	@Column(name = "cinema_room_name", length = 50)
	private String cinemaRoomName;

	@JsonBackReference
	@OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
	private List<ScheduleMovie> scheduleMovies;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    private Collection<Seat> seats;
	
}
