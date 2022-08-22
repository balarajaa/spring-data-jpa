package com.learning.springdatajpa.repository;

import com.learning.springdatajpa.entity.Course;
import com.learning.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course course = Course.builder()
                .courseTitle(".NET")
                .credit(7)
                .build();

        Course course1 = Course.builder()
                .courseTitle("Java")
                .credit(8)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("pradeep")
                .lastName("pal")
                //.courses(Arrays.asList(course,course1))
                .build();
        teacherRepository.save(teacher);
    }

}