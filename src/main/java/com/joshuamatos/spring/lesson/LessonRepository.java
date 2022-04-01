package com.joshuamatos.spring.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lessons, Integer> {
   Optional<Lessons> findById(Integer id);
}
