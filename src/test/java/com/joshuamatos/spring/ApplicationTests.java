package com.joshuamatos.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
@WebMvcTest(AppController.class)
class ApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	void initTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/");
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Hello Spring!"));
	}

	@Test
	void mathPITest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("3.141592653589793"));
	}
//	@Test
//	void contextLoads() {
//
//	}

}
