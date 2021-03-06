package com.joshuamatos.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import javax.servlet.http.Cookie;

import java.io.InputStream;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@AutoConfigureMockMvc
@SpringBootTest
class ApplicationTests {

	@Autowired
	MockMvc mvc;



	@Test
	void testPostMappingCalculate() throws Exception {



		//Map JSON information
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> data = new HashMap<>();
		data.put("jsonKey", "jsonValue");
		String json = mapper.writeValueAsString(data);


		MockHttpServletRequestBuilder request = post("/math/calculate")
				.contentType(MediaType.ALL_VALUE)
				.param("operation", "multiply")
				.param("x", "5")
				.param("y", "5");

		//test multiply
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("5 * 5 = 25"));

		request = post("/math/calculate")
				.contentType(MediaType.ALL_VALUE)
				.param("operation", "add")
				.param("x", "5")
				.param("y", "5");

		//test add
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("5 + 5 = 10"));


		request = post("/math/calculate")
				.contentType(MediaType.ALL_VALUE)
				.param("x", "5")
				.param("y", "5");

		//test add
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("5 + 5 = 10"));

		request = post("/math/calculate")
				.contentType(MediaType.ALL_VALUE)
				.param("operation", "divide")
				.param("x", "5")
				.param("y", "5");

		//test divide
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("5 / 5 = 1"));

		request = post("/math/calculate")
				.contentType(MediaType.ALL_VALUE)
				.param("operation", "subtract")
				.param("x", "5")
				.param("y", "5");

		//test subtract
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("5 - 5 = 0"));

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
		//test area
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));

		request = post("/math/area")
				.contentType(MediaType.ALL_VALUE)
				.param("type", "rectangle")
				.param("height", "4")
				.param("width", "7");
		//test rectangle
		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Area of a 4x7 rectangle is 28"));

		//test invalid input rectangle and radius
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
