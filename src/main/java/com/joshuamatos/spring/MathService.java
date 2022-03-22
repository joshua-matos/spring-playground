package com.joshuamatos.spring;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MathService {

    //count use MultiValueMap instead
    public String postSum(ArrayList<Integer> arrayList) {

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

    //count use MultiValueMap instead
    public String postSum(MultiValueMap<String, Integer> multiValueMap, boolean useMultiMap) {
        List<Integer> listOne = List.of(1, 2, 3);
        multiValueMap.put("n", listOne);
        multiValueMap.put("n", List.of(1));
        multiValueMap.put("n", List.of(12));
        return "";

    }

    public String calculate(String operation, int x, int y) {
        int sum;
        String statement;

        switch (operation) {
            case "subtract":
                sum = x - y;
                statement = x + " - " + y + " = " + sum;
                break;
            case "multiply":
                sum = x * y;
                statement = x + " * " + y + " = " + sum;
                break;
            case "divide":
                sum = x / y;
                statement = x + " / " + y + " = " + sum;
                break;
            default:
                sum = x + y;
                statement = x + " + " + y + " = " + sum;
                break;
        }
        return statement;
    }


}
