package fa.group1.services.impl;

import fa.group1.dto.UserDTO;
import fa.group1.entities.Role;
import fa.group1.entities.User;
import fa.group1.exceptions.ResourceNotFoundException;
import fa.group1.exceptions.UniqueConstraintException;
import fa.group1.repository.RoleRepository;
import fa.group1.repository.UserRepository;
import fa.group1.services.UserDetailsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

//    @Override
//    public Map<String, Object> getBookingHistory(int page, int size) {
//        return null;
//    }

    @Override
    public List<User> getAllEmployeeList(Pageable pageable) {
        List<User> listEmployee = userRepository.getAllEmployee();

        if (listEmployee.isEmpty()) {
        	LOGGER.error("Not found any employee");
            throw new ResourceNotFoundException("Not found any employee");
        }
        return listEmployee;
    }

    @Override
    public List<User> getAllUser() {
        List<User> list = userRepository.findAll();
        if (list.isEmpty()) {
        	LOGGER.error("Not found any user");
            throw new ResourceNotFoundException("Not found any user");
        }
        return list;
    }

    @Override
    public User findUserByID(Integer id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id).orElseThrow(() ->
        {
        	LOGGER.error("No user match this id");
        	throw new ResourceNotFoundException("No user match this id");});
    }

    @Override
    public User updateUser(UserDTO userDTO) {

        User user = modelMapper.map(userDTO, User.class);
        User u = userRepository.save(user);
        return u;

    }

    @Override
    public User addUserAsEmployee(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
        	LOGGER.error("Username is exsisted");
            throw new UniqueConstraintException("Username is exsisted");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        	LOGGER.error("Email is exsisted");
            throw new UniqueConstraintException("Email is exsisted");
        }
        if (userRepository.findByIdentityCard(user.getIdentityCard()).isPresent()) {
        	LOGGER.error("Identity card is exsisted");
            throw new UniqueConstraintException("Identity card is exsisted");
        }
        Optional<Role> role = roleRepository.findByRoleName("Employee");
        String encodePwd = encoder.encode(user.getPassword());
        user.setRole(role.get());
        user.setRegisterDate(LocalDate.now());
        user.setStatus(1);
        user.setPassword(encodePwd);
        return userRepository.save(user);
    }
}
