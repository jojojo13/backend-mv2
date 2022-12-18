package fa.group1.services;

import java.util.List;
import java.util.Map;


import fa.group1.dto.UserDTO;
import fa.group1.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserDetailsService {
//    Map<String, Object> getBookingHistory(int page, int size);

    List<User> getAllEmployeeList(Pageable pageable);

    List<User> getAllUser();

    User findUserByID(Integer id);

    User updateUser(UserDTO u);

    User addUserAsEmployee(User user);
}
