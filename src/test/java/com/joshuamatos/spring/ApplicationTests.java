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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



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

		@Bean
		public PostMappingService PostMappingService() { return new PostMappingService();}

	}

	@Test
	void testPostMapping() throws Exception {
		MockHttpServletRequestBuilder request = post("/math/sum")
				.contentType(MediaType.ALL_VALUE)
				.param("n", "4")
				.param("n", "5")
				.param("n", "6");
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("4 + 5 + 6 = 15"));
	}

	@Test
	void testMathAreaCalc() throws Exception {
		MockHttpServletRequestBuilder request = post("/math/area")
				.contentType(MediaType.ALL_VALUE)
				.param("type", "circle")
				.param("radius", "4");

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));

		request = post("/math/area")
				.contentType(MediaType.ALL_VALUE)
				.param("type", "rectangle")
				.param("height", "4")
				.param("width", "7");

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Area of a 4x7 rectangle is 28"));


		request = post("/math/area")
				.contentType(MediaType.ALL_VALUE)
				.param("type", "rectangle")
				.param("radius", "4");

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Invalid"));



	}

	@Test
	void initTest() throws Exception {
		RequestBuilder request = get("/");
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Hello Spring!"));
	}

	@Test
	void mathPITest() throws Exception {
		RequestBuilder request = get("/math/pi");
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("3.141592653589793"));
	}

	@Test
	void urlRequestVars() throws Exception {

				String testString = String.format("/%d/%s", 1, "Josh");
				this.mvc.perform(get(testString))
						.andExpect(status().isOk())
						.andExpect(content().string(("1 and Josh")));
	}


	@Test
	void queryMapExample() throws Exception {
		String testString = "/map-example?map=1&string=2";
		this.mvc.perform(get(testString))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("{map=1, string=2}"));
	}

	@Test
	void testPostMappingPathVar() throws Exception {
		//@PostMapping("/posts/{postId}/comments")
		String testString = String.format(
				"postId:%d notify:%s content:%s author:%s", 1, "notify", "content", "author");

		MockHttpServletRequestBuilder request = post("/posts/1/comments")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("notify", "notify")
				.param("content", "content")
				.param("author", "author");

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(testString));
	}

	@Test
	public void testCookies() throws Exception {
		Cookie cookie = new Cookie("foo", "bar");

		this.mvc.perform(get("/cookie").cookie(cookie))
				.andExpect(status().isOk())
				.andExpect(content().string("bar"));
	}
}
