package com.joshuamatos.spring.lesson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LessonsConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerLessons(LessonRepository lessonRepository) {
        return args -> {
            Lessons lessons = new Lessons("JPA");
            Lessons lessons1 = new Lessons("JPA");
            Lessons lessons2 = new Lessons("JPA");
            Lessons lessons3 = new Lessons("JPA");
            Lessons lessons4 = new Lessons("JPA");
            Lessons lessons5 = new Lessons("JPA");
            lessonRepository.saveAll(List.of(
                    lessons,
                    lessons1,
                    lessons2,
                    lessons3,
                    lessons4,
                    lessons5));
        };
    }
}
