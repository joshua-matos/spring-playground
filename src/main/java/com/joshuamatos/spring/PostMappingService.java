package com.joshuamatos.spring;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Map;

import static java.lang.Math.PI;

@Service
public class PostMappingService {


    public String createComment(int postId, Map<String, String> params) {
        return String.format(
                "postId:%d notify:%s content:%s author:%s",
                postId,
                params.get("notify"),
                params.get("content"),
                params.get("author")
        );
    }

    public String mathArea(String type, int radius, int width, int height) {
        double area = 0.0;
        String returnString = "";
        DecimalFormat df = new DecimalFormat("0.00000");

        if(type.equals("rectangle") && radius > 0) {
            return "Invalid";
        }
            switch(type){
                case "circle":
                         area =  PI * (radius * radius);
                         returnString = "Area of a circle with a radius of "+ radius +" is " + df.format(area);
                    break;
                case "rectangle":
                        df = new DecimalFormat("00");
                        area = width  * height;
                        returnString = "Area of a " + height + "x" + width + " rectangle is " + df.format(area);
                    break;
                default:
                    return "invalid type.";

            }

            return returnString;

    }

}
