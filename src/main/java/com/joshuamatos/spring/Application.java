package com.joshuamatos.spring;

import com.joshuamatos.spring.system.WaterSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {


         SpringApplication.run(Application.class, args);

//		ApplicationContext context = new AnnotationConfigApplicationContext(MathService.class);
//		MathService getContext = context.getBean(MathService.class);
//		System.out.println(getContext.toString());
    }

}
//