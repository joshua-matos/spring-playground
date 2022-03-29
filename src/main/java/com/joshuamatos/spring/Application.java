package com.joshuamatos.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//
//		ApplicationContext context = new AnnotationConfigApplicationContext(Automobile.class);
//		Automobile getContext = context.getBean(Automobile.class);
//
//		System.out.println(getContext.toString());
    }

}
