package com.joshuamatos.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
@WebMvcTest(AppController.class)
class ApplicationTests {

	@Autowired
	MockMvc mvc;


	@TestConfiguration
	static class MathServiceImplTestContextConfiguration {

		@Bean
		public MathService MathService() {
			return new MathService();
		}

	}
	@Test
	void testPostMapping() throws Exception {
		MockHttpServletRequestBuilder request = post("/math/sum")
				.contentType(MediaType.ALL)
				.param("n", "4")
				.param("n", "5")
				.param("n", "6");
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("4 + 5 + 6 = 15"));
	}
//
	@Test
	void initTest() throws Exception {
		RequestBuilder request = get("/");
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Hello Spring!"));
	}
//
//	@Test
//	void mathPITest() throws Exception {
//		RequestBuilder request = get("/math/pi");
//		this.mvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().string("3.141592653589793"));
//	}
//
//	@Test
//	void urlRequestVars() throws Exception {
//
//				String testString = String.format("/%d/%s", 1, "Josh");
//				this.mvc.perform(get(testString))
//						.andExpect(status().isOk())
//						.andExpect(content().string(("1 and Josh")));
//	}
//
//
//	@Test
//	void queryMapExample() throws Exception {
//		String testString = ("/map-example?map=1&string=2");
//		this.mvc.perform(get(testString))
//				.andExpect(status().isOk())
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect(content().contentType("text/plain;charset=UTF-8"))
//				.andExpect(content().string("{map=1, string=2}"));
//	}

}
