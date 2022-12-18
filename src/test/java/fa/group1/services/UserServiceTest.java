package fa.group1.services;

import fa.group1.dto.UserDTO;
import fa.group1.entities.Role;
import fa.group1.entities.User;
import fa.group1.repository.RoleRepository;
import fa.group1.repository.UserRepository;
import fa.group1.services.impl.UserServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Mock
    private ModelMapper modelMapper;
    @Mock
    PasswordEncoder encoder;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1, "Nong tai", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, null, 1, null));
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.getAllUser();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllUserEmpty() {
        List<User> userList = new ArrayList<User>();
        try {
            when(userRepository.findAll()).thenReturn(userList);
            List<User> result = userService.getAllUser();
        } catch (Exception e) {
            final String msg = "Not found any user!!!";
            System.out.println(msg);
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void testGetUserById() {
        User user = new User(1, "Nong tai", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, null, 1, null);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User resultOpt = userService.findUserByID(1);
        assertNotNull(resultOpt);

    }

    @Test
    public void testGetUserByIdNotExsisted() {
        try {
            when(userRepository.findById(121212121)).thenReturn(null);
            User resultOpt = userService.findUserByID(1);
        } catch (Exception e) {
            final String msg = "No user match this id";
            System.out.println(msg);
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void testGetAllEmployeeList() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(0, "Nong tai", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null));
        userList.add(new User(1, "Nong tai", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null));
        when(userRepository.getAllEmployee()).thenReturn(userList);
        Pageable pageable = PageRequest.of(0, 3);

        List<User> result = userService.getAllEmployeeList(pageable);
        assertNotNull(result);
    }

    @Test
    public void testGetAllEmployeeListIsEmpty() {
        List<User> userList = new ArrayList<User>();
        try {
            when(userRepository.getAllEmployee()).thenReturn(userList);
            Pageable pageable = PageRequest.of(0, 3);

            List<User> result = userService.getAllEmployeeList(pageable);
        } catch (Exception e) {
            final String msg = "Not found any employee!!!";
            System.out.println(msg);
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void testAddUserAsEmployeeExsistedUsername() {
        try {
            User u = new User(0, "Nong tai", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
            when(userRepository.findByUsername(u.getUsername())).thenReturn(Optional.of(u));
            userService.addUserAsEmployee(u);
        } catch (Exception e) {
            final String msg = "Username is exsisted";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void testAddUserAsEmployeeExsistedEmail() {
        try {
            User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
            when(userRepository.findByEmail(u.getEmail())).thenReturn(Optional.of(u));
            userService.addUserAsEmployee(u);
        } catch (Exception e) {
            final String msg = "Email is exsisted";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void testAddUserAsEmployeeExsistedIdentityCard() {
        try {
            User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai12@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
            when(userRepository.findByIdentityCard(u.getIdentityCard())).thenReturn(Optional.of(u));
            userService.addUserAsEmployee(u);
        } catch (Exception e) {
            final String msg = "Identity card is exsisted";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void testAddUserAsEmployee() {
        User user = new User();
        user.setAccountId(1);
        user.setPassword(encoder.encode("abc"));
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Employee");
        when(roleRepository.findByRoleName("Employee")).thenReturn(Optional.of(role));
        user.setRole(role);
        user.setRegisterDate(LocalDate.now());
        user.setStatus(1);
        when(userService.addUserAsEmployee(user)).thenReturn(user);
        System.out.println("_------------" + userService.addUserAsEmployee(user));
    }

    @Test
    public void testUpdateUser() {
        UserDTO userDTO = new UserDTO(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai12@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null));
        User u = new User(2, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai12@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
        when(modelMapper.map(userDTO, User.class)).thenReturn(u);
        when(userRepository.save(u)).thenReturn(u);
        User user = userService.updateUser(userDTO);
        assertEquals(u.getAccountId(), user.getAccountId());
    }
}
