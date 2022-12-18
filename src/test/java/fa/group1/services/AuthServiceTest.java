package fa.group1.services;


import fa.group1.entities.Role;
import fa.group1.entities.User;
import fa.group1.payload.request.LoginRequest;
import fa.group1.repository.RoleRepository;
import fa.group1.repository.UserRepository;
import fa.group1.security.services.UserDetailsImpl;
import fa.group1.services.impl.AuthServiceImpl;

import fa.group1.utils.TokenAuthenticationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthServiceTest {
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PasswordEncoder encoder;
    @InjectMocks
    private AuthServiceImpl authService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testSignIn() {
        GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority("Customer");
        LoginRequest loginRequest = new LoginRequest("hungdz", "123456");
        UserDetailsImpl user = new UserDetailsImpl(1, loginRequest.getUsername(), loginRequest.getPassword(), grantedAuthorities);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ))).thenReturn(auth);
        Map<String, Object> userInfo = authService.signIn(loginRequest);
        Map<String, Object> data = (Map<String, Object>) userInfo.get("data");
        Assert.assertEquals(loginRequest.getUsername(), data.get("username"));

    }

    @Test
    public void testSignUpExsistedEmail() {
        try {
            User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
            when(userRepository.findByEmail(u.getEmail())).thenReturn(Optional.of(u));
            authService.signUp(u);
        } catch (Exception e) {
            final String msg = "Email is exsisted";
            assertEquals(msg, e.getMessage());
        }

    }

    @Test
    public void testSignUpExsistedUsername() {
        try {
            User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
            when(userRepository.findByUsername(u.getUsername())).thenReturn(Optional.of(u));
            authService.signUp(u);
        } catch (Exception e) {
            final String msg = "Username is exsisted";
            assertEquals(msg, e.getMessage());
        }

    }

    @Test
    public void testSignUpExsistedIdentityCard() {
        try {
            User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
            when(userRepository.findByIdentityCard(u.getIdentityCard())).thenReturn(Optional.of(u));
            authService.signUp(u);
        } catch (Exception e) {
            final String msg = "Identity card is exsisted";
            assertEquals(msg, e.getMessage());
        }

    }

    @Test
    public void testSignUpSuccess() {
        User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
        Role role = new Role(2, "Employee", null);
        when(roleRepository.findByRoleName("Customer")).thenReturn(Optional.of(role));
        String encodePwd = "123456";
        when(encoder.encode(u.getPassword())).thenReturn(encodePwd);
        when(userRepository.save(u)).thenReturn(u);
        Map<String, Object> response = authService.signUp(u);
        assertEquals("Signup successfully!!!", response.get("message"));

    }
    @Test
    public void testGetUserByValidToken(){
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA";
        User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
        Authentication authentication = TokenAuthenticationUtils.getAuthentication(token);
        when(userRepository.findByUsername(authentication.getPrincipal().toString())).thenReturn(Optional.of(u));
        Map<String, Object> response =authService.getUserInfoByToken(token);
        assertTrue(response.get("username")!="");
    }
    @Test
    public void testGetUserByInValidToken(){
        try{
            String InvalidToken="HEHEHEHEeyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA";
            User u = new User(0, "hehe", "Lao cai", LocalDate.of(2022, 9, 9), "nongtai1@gmail.com", "Nong Duc Tai", 1, "01234567", "abcde", "0123456789", "0123456789", LocalDate.of(2022, 8, 9), 245.00, new Role(2, "Employee", null), 1, null);
            Authentication authentication = TokenAuthenticationUtils.getAuthentication(InvalidToken);
            when(userRepository.findByUsername(authentication.getPrincipal().toString())).thenReturn(null);
            authService.getUserInfoByToken(InvalidToken);

        }catch (Exception e){
                assertEquals("User not found",e.getMessage());
        }

    }
}
