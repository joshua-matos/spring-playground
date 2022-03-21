package com.joshuamatos.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AppController {
	private String appName;


	private final MathService mathService;

	@Autowired
	public AppController(MathService mathService) {
		this.mathService = mathService;
	}

	@PostMapping("/math/sum")
	public String returnPostMappingService(@RequestParam("n") ArrayList<Integer> arrayList){
		return mathService.postSum(arrayList);
	}

	public String getAppContext() {
		return "Controller";
	}


	@PostMapping("/math/calculate")
	public String calculate(
			@RequestParam(required = false) String operation,
			@RequestParam int x,
			@RequestParam int y
	) {
		int sum;
		operation = "";
		String statement;

		switch(operation){
			case "subtract":
				sum = x - y;
				statement = x + " - " + y + "= " + sum;
				break;
			case "multiply":
				sum = x * y;
				statement = x + " * " + y + "= " + sum;
				break;
			case "divide":
				sum = x / y;
				statement = x + " / " + y + "= " + sum;
				break;
			default:
				sum = x + y;
				statement = x + " + " + y + "= " + sum;
				break;
		}
		return statement;
	}



	@GetMapping("/")
	public String helloWorld(){
		return "Hello Spring!";
	}
//
//	@GetMapping("/math/pi")
//	public String returnPI(){
//		return "3.141592653589793";
//	}
//
//	@RequestMapping("/r")
//	public String RequestMappingURL(){
//
//		return "r";
//	}
//
//	@GetMapping("/{id}/{name}")
//	public String urlGetMapping(
//			@PathVariable String name,
//			@PathVariable String id) {
//		return "" + id + " and " + name;
//	}
//
//	@GetMapping("/people")
//	public String getPeople(
//			@RequestParam("sort") String sort,
//			@RequestParam(required = false) String direction
//	){
//		return sort;
//	}
//
//	@GetMapping("/map-example")
//	public String getMapParams(@RequestParam Map<String, String> querystring) {
//		return querystring.toString();
//	}

}
