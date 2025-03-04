package com.seares.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.seares.crud_spring.enums.Category;
import com.seares.crud_spring.model.Course;
import com.seares.crud_spring.model.Lesson;
import com.seares.crud_spring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for (int i = 0; i < 20; i++) {

				Course c = new Course();
				c.setName("Angular com Spring" + i);
				c.setCategory(Category.FRONT_END);

				Lesson l = new Lesson();
				l.setName("Introdução ao Angular");
				l.setYoutubeUrl("https://www.youtube.com/watch?v=1-w1RfWI0W8");
				l.setCourse(c);

				c.getLessons().add(l);

				Lesson l1 = new Lesson();
				l1.setName("Listando aulas no Angular");
				l1.setYoutubeUrl("https://www.youtube.com/watch?v=ZLpyS4qqEZE&list=PLGxZ4Rq3BOBpwaVgAPxTxhdX_TfSVlTcY");
				l1.setCourse(c);

				c.getLessons().add(l1);

				courseRepository.save(c);
			}
		};
	}
}
