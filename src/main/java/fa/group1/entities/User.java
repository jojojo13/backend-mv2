package fa.group1.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Entity
@Table(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "address")
	private String address;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "identity_card", unique = true)
	private String identityCard;

	@Column(name = "image")
	private String image;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "password")
	private String password;


	@Column(name = "register_date")
	private LocalDate registerDate;

	@Column(name = "score")
	public Double score;

	@ManyToOne
	@JoinColumn(name = "role_id") // thông qua khóa ngoại address_id
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Role role;

	@Column(name = "status", columnDefinition = "int default(1)")
	private Integer status;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<Ticket> tickets;
	
//	@OneToMany(mappedBy = "account")
//	private List<Invoice> invoices;

}
