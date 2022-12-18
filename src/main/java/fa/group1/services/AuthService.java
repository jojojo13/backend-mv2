package fa.group1.services;

import fa.group1.entities.User;
import fa.group1.payload.request.LoginRequest;

import java.util.Map;

public interface AuthService {

    Map<String,Object> signIn(LoginRequest loginRequest);
    Map<String,Object> signUp(User user);
    Map<String,Object> getUserInfoByToken(String token);
}
