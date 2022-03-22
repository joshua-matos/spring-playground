package com.joshuamatos.spring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class MathService {

	//count use MultiValueMap instead
	public String postSum(ArrayList<Integer> arrayList){

		//sum positive values
		Integer sum = arrayList
				.stream()
				.filter(i -> i > 0)
				.reduce(0, Integer::sum);

		//return intgers with + sign and = sum
		return arrayList.stream()
				 .map(Object::toString)
				 .collect(Collectors.joining(" + ", "", " = " + sum));


	}

}
