package com.joshuamatos.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class AppController {
    private final MathService mathService;
    private final PostMappingService postMappingService;
    private String appName;

    @Autowired
    public AppController(MathService mathService, PostMappingService postMappingService) {
        this.postMappingService = postMappingService;
        this.mathService = mathService;
    }

    @PostMapping("/math/area")
    public String mathArea(
            @RequestParam Optional<String> type,
            @RequestParam Optional<Integer> radius,
            @RequestParam Optional<Integer> width,
            @RequestParam Optional<Integer> height) {

		String stringType = type.orElseGet(() -> "no type");
		int intRadius = radius.orElseGet(() -> 0);
		int intWidth = width.orElseGet(() -> 0);
		int intHeight = height.orElseGet(() -> 0);;

        return postMappingService.mathArea(stringType, intRadius, intWidth, intHeight);


    }

	@PostMapping("/volume/{length}/{width}/{height}")
	public String calculateVolume(
			@PathVariable Integer length,
			@PathVariable Integer width,
			@PathVariable Integer height
	) {
		if(length == null || width == null || height == null) {
			length = 0;
			height = 0;
			width = 0;
		}
		int area = width * height * height;
		return String.format("The volume of a rectangle is %d with the height: %d and width: %d and length: %d", area, width, height, length);
	}

    @PostMapping("/posts/{postId}/comments")
    public String createComment(
			@PathVariable int postId,
			@RequestParam Map<String, String> params) {
        return postMappingService.createComment(postId, params);
    }

    @PostMapping("/math/sum")
    public String returnPostMappingService(@RequestParam("n") ArrayList<Integer> arrayList) {
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
        if (operation == null) operation = "";

        return mathService.calculate(operation, x, y);
    }


    @GetMapping("/")
    public String helloWorld() {
        return "Hello Spring!";
    }

    @GetMapping("/math/pi")
    public String returnPI() {
        return "3.141592653589793";
    }

    @RequestMapping("/r")
    public String RequestMappingURL() {

        return "r";
    }

    @GetMapping("/{id}/{name}")
    public String urlGetMapping(
            @PathVariable String name,
            @PathVariable String id) {
        return "" + id + " and " + name;
    }

    @GetMapping("/people")
    public String getPeople(
            @RequestParam("sort") String sort,
            @RequestParam(required = false) String direction
    ) {
        return sort;
    }

    @GetMapping("/map-example")
    public String getMapParams(@RequestParam Map<String, String> querystring) {
        return querystring.toString();
    }

    @GetMapping("/cookie")
    public String getCookie(@CookieValue(name = "foo") String cookie) {
        return cookie;
    }
}
