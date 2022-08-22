package com.learning.springdatajpa.repository;

import com.learning.springdatajpa.entity.Guardian;
import com.learning.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*#DataJpaTest*/
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("balarun@gmail.com")
                .firstName("bala")
                .lastName("chandar")
                //.guardianName("sam")
                //.guardianEmail("sam@gmail.com")
                //.guardianMobile("02323232323")
                .build();
        studentRepository.save(student);

    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .email("sam1@gmai.com")
                .name("sam1")
                .mobile("43435555")
                .build();
        Student student = Student.builder()
                .emailId("safin1@gmail.com")
                .firstName("safin1")
                .lastName("marat1")
                .guardian(guardian)
                .build();

        studentRepository.save(student);

    }


    @Test
    public void printAllStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println("student List :"+students);

    }

    @Test
    public void printStudentsByFirstName() {
        List<Student> students = studentRepository.findByFirstName("bala");
        System.out.println("student List :"+students);

    }

    @Test
    public void printStudentsByEmailIdNamedQuery() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("safin@gmail.com");
        System.out.println("student List :"+student);

    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId("Safin123","safin1@gmail.com");

    }


}