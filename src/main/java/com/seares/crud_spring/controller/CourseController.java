package com.seares.crud_spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seares.crud_spring.model.Course;
import com.seares.crud_spring.repository.CourseRepository;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    // @ResponseStatus(code = HttpStatus.CREATED) Caso utilizar o Response status,
    // dá para remover o ResponseEntity, e retornar apenas o que foi criado.
    public ResponseEntity<Course> create(@RequestBody Course course) {
        // return courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable("id") Long id, @RequestBody Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
