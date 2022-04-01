package com.joshuamatos.spring;
import com.joshuamatos.spring.lesson.LessonRepository;
import com.joshuamatos.spring.lesson.LessonService;
import com.joshuamatos.spring.lesson.Lessons;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
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

import javax.swing.*;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
@AutoConfigureMockMvc
@SpringBootTest

public class LessonsTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository lessonRepository; //database for direct testing
    LessonService lessonService; //test the service responsible for executing database actions

    @Test
    @Transactional
    @Rollback
    void testDatabaseMethods() throws Exception {
        //check post
        this.mvc.perform(post("/lessons/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"JAVA\"}"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("JAVA"));

        //create objects
        Lessons lessons1 = new Lessons("Spring");
        Lessons lessons2 = new Lessons("Dark Souls");

        //Save data to database
        lessonRepository.saveAll(List.of(lessons1, lessons2));

        //to access in JSON
        int id1 = lessons1.getId();
        int id2 = lessons2.getId();

        this.mvc.perform(get("/lessons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].title", Matchers.containsString("Spring")));

//        this.mvc.perform(get("/lessons")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$["+id2+"].title").value("Dark Souls"));
    }

    @Test
    void testPostMappingCalculate() throws Exception {

        //Get the lesson 5 value
        MockHttpServletRequestBuilder requestBuilder = get("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("JPA") );


        //Delete the lesson 5
        MockHttpServletRequestBuilder requestBuilder2 = delete("/lessons/5");

        this.mvc.perform(requestBuilder2)
                .andExpect(status().isOk())
                .andExpect(content().string("The ID 5 has been deleted from the database"));
    }
}



