package fa.group1.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MOVIE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId;

	@Column(name = "ACTOR")
	private String actor;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "DIRECTOR")
	private String director;

	@Column(name = "DURATION")
	private Double duration;

	@Column(name = "FROM_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate from_date;

	@Column(name = "MOVIE_PRODUCTION_COMPANY")
	private String movie_production_company;

	@Column(name = "TO_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate to_date;

	@Column(name = "VERSION", length = 20)
	private String version;

	@Column(name = "MOVIE_NAME_ENGLISH")
	private String movie_name_english;

	@Column(name = "MOVIE_NAME_VN")
	private String movie_name_vn;

	@Column(name = "LARGE_IMAGE")
	private String large_image;

	@Column(name = "SMALL_IMAGE")
	private String small_image;

	@ManyToMany(cascade = { CascadeType.ALL })
	@ToString.Exclude
	@JoinTable(
			name = "movie_type",
			joinColumns = { @JoinColumn(name = "moive_id") },
			inverseJoinColumns = { @JoinColumn(name = "type_id") }
	)
	private List<Type> types;


//	@JsonIgnoreProperties("movies")
//	@ManyToMany(mappedBy = "movies",fetch = FetchType.LAZY)
//	@Fetch(FetchMode.SUBSELECT)
//	// LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới
//	private Collection<Schedule> schedules;

	@ToString.Exclude
	@OneToMany(mappedBy = "movie")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	private List<ScheduleMovie> scheduleMovies;
	
	@Column(name = "preview_url")
	private String previewUrl;
	
}
