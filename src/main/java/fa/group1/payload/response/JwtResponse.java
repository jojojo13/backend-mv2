package fa.group1.payload.response;

import java.util.HashMap;
import java.util.Map;

public class JwtResponse {
    private Map<String,Object> token;
    private Map<String,Object> userInfo;

    public JwtResponse(Map<String, Object> token, Map<String, Object> userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    public Map<String, Object> getToken() {
        return token;
    }

    public void setToken(Map<String, Object> token) {
        this.token = token;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
    }
}
