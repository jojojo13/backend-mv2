package fa.group1.dto;

import fa.group1.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountId;

    private String username;

    private String address;

    private LocalDate dateOfBirth;

    private String email;

    private String fullName;

    private Integer gender;

    private String identityCard;

    private String image;

    private String phoneNumber;

    private String password;

    private LocalDate registerDate;

    public Double score;
    private Role role;
}
