package com.learning.springdatajpa.repository;

import com.learning.springdatajpa.entity.Course;
import com.learning.springdatajpa.entity.Student;
import com.learning.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        System.out.println(courseList);
    }

   @Test
    public void courseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("red")
                .lastName("der")
                .build();

        Course course = Course.builder()
                .courseTitle("python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);


    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);
        List<Course> courses =
                courseRepository.findAll(secondPageWithTwoRecords)
                        .getContent();

        Long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords)
                                .getTotalElements();
        System.out.println("totalElements: "+totalElements);

        System.out.println("courses: "+courses);

    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle =
                PageRequest.of(
                        0,4,Sort.by("courseTitle")
                );
        Pageable sortByCreditDesc =
                PageRequest.of(0,2,Sort.by("credit").descending());

        Pageable sortByTitleCreditDesc =
                PageRequest.of(0,2,Sort.by("courseTitle").descending()
                        .and(Sort.by("credit")));

        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();
        System.out.println("Courses : "+courses);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);

        List<Course> courses =
                courseRepository.findByCourseTitleContaining("S",firstPageTenRecords).getContent();

        System.out.println("Courses : "+courses);

    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        Course course = Course
                .builder()
                .courseTitle("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);

    }

}