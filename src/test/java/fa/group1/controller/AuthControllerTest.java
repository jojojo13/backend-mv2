package fa.group1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fa.group1.Group1Application;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Group1Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext wac;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    @Test
    public void verifySignIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                        .accept(MediaType.APPLICATION_JSON).content("{\n" +
                                "\"username\":\"hungdz\",\n" +
                                "\"password\":\"123456\"\n" +
                                "}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void verifySignUp() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .accept(MediaType.APPLICATION_JSON).content("{\n" +
                                "\"username\":\"usernameValid\",\n" +
                                "\"password\":\"123456\",\n" +
                                "\"address\":\"236 Lochigan\",\n" +
                                "\"dateOfBirth\":\"2000-09-06\",\n" +
                                "\"email\":\"validEmail@gmail.com\",\n" +
                                "\"fullName\":\"Nguyen Hehe Hung\",\n" +
                                "\"gender\":1,\n" +
                                "\"identityCard\":2009232322,\n" +
                                "\"image\":\"path/to/hehe.jpg\",\n" +
                                "\"phoneNumber\":\"0997232213\"\n" +
                                "}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void verifyUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/userInfo")
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization" , "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA"))
                .andExpect(status().isOk());

    }

}
