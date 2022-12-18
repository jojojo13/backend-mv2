package fa.group1.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeId;

	@Column(name = "type_name")
	private String typeName;

	@JsonBackReference
	@ToString.Exclude
	@ManyToMany(mappedBy = "types", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới
	private List<Movie> movies;
}
