package com.joshuamatos.spring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest

public class LessonsTest {
    @Autowired
    MockMvc mvc;


    @Test
    void testPostMappingCalculate() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("JPA") );


        MockHttpServletRequestBuilder requestBuilder2 = delete("/lesson/5");


        this.mvc.perform(requestBuilder2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The ID 5 has beeen deleted from the database"));
    }
}



