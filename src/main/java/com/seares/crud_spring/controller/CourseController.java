package com.seares.crud_spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seares.crud_spring.model.Course;
import com.seares.crud_spring.repository.CourseRepository;

@RestController
@RequestMapping("/api/courses")

public class CourseController {

    private final CourseRepository courseRepository;

    
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseRepository.findAll();
    }

    @PostMapping
    //@ResponseStatus(code = HttpStatus.CREATED) Caso utilizar o Response status, dá para remover o ResponseEntity, e retornar apenas o que foi criado.
    public ResponseEntity<Course> create(@RequestBody Course course) {
        //return courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(courseRepository.save(course));
    }
}
