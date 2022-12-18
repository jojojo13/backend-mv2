package fa.group1.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import fa.group1.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.group1.entities.Role;
import fa.group1.entities.User;
import fa.group1.payload.request.LoginRequest;
import fa.group1.repository.RoleRepository;
import fa.group1.repository.UserRepository;
import fa.group1.utils.JwtUtils;
import fa.group1.security.services.UserDetailsImpl;
import fa.group1.utils.TokenAuthenticationUtils;

@RestController
@RequestMapping(path = "api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> responseJwt = authService.signIn(loginRequest);
        return ResponseEntity.ok(responseJwt);
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        Map<String, Object> response = authService.signUp(user);
        return ResponseEntity.ok(response);

    }

    @GetMapping("userInfo")
    ResponseEntity<?> getUserInfo(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> response = authService.getUserInfoByToken(token);
        return ResponseEntity.ok(response);

    }
}
