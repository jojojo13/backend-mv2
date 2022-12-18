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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Group1Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //    @Test
//    public void manageTicket() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/ticket").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
    @Test
    public void getSeats() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ticket/bookedTicket").accept(MediaType.APPLICATION_JSON)
                        .header("Authorization" , "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nZHoiLCJpYXQiOjE2NzEwMDI0NDIsImV4cCI6MTY3MjExMzU1M30.4ySvmSXlcqT4a3U7UQVTzvNLXhwHJAtKd8Skfuq42XHTA2ddN4frb4YzYqHOupsCmwSaekeMPw1mYNzZ9AaORA"))
                .andExpect(status().isOk());
    }

}
