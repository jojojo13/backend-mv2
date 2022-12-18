package fa.group1.controller;

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
public class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    @Test
    public void verifyAllUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verifyGetUserAccountByIDNotExsisted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/user-account?id=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void verifyGetUserAccountByIDExsisted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/user-account?id=2001").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void verifyGetUserAccountByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/user-account?id=2001").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(2001));
    }
//    @Test
//    public void verifyUpdateUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update").accept(MediaType.APPLICATION_JSON).content())
//                .andExpect(status().isOk());
//    }
    @Test
    public void verifyAllUserAsEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/user-as-employee?index=0&size=3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void verifyAllUserAsEmployeeExsist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/user-as-employee?index=0&size=3").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists());
    }
}
