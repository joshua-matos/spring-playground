package com.joshuamatos.spring.lesson;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public Optional<Lessons> getALesson(Integer id) {
        if(lessonRepository.existsById(id)) {
            return lessonRepository.findById(id);
        }
        else return Optional.empty();
    }

    public List<Lessons> getAllLessons() {
        return lessonRepository.findAll();
    }

    public String deleteALesson(Integer id) {
        String message;
        if(lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            message = String.format("The ID %d has been deleted from the database", id);
        } else {
            message = "ID does not exist";
        }
        return message;
    }

    public Lessons createALesson(Lessons lessons) {
        lessonRepository.save(lessons);
        return lessons;
    }
}
