package com.sekulicd.citygroove;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CityGrooveApplication.class
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CityGrooveApplicationTests {


    @Autowired
    MockMvc mockMvc;

    //CityController test
    @Test
    public void checkLoadedCitiesInDb() throws Exception{
//        MvcResult result = mockMvc.perform(
//                MockMvcRequestBuilders
//                        .get("/public/city")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk()).andReturn();
//        String content = result.getResponse().getContentAsString();
        System.out.println();
    }

}
