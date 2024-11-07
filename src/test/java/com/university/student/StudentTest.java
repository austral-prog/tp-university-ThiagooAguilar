package com.university.student;

import com.university.course.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test

    public void testget_setters(){
        Student student = new Student("Taylor Swift", "taylor@swift.com");

        assertEquals("Taylor Swift", student.getName(), "El nombre del estudiante no coincide");

        student.setName("Moria Casan");
        assertEquals("Moria Casan", student.getName(),"El nombre del estudiante no ha sido actualizado");
        assertEquals("taylor@swift.com", student.getEmail(),"El email del estudiante no coincide ");
        student.setEmail("moria@casan.com");
        assertEquals("moria@casan.com", student.getEmail(),"El email del estudiante no ha sido actualizado");
    }
    @Test
    public void test_method_of_courses() {
        System.out.println("Test method of courses");
        Student student = new Student("Taylor Swift", "taylor@swift.com");
        Course course1 = new Course(101, "Art", "Dolores Umbridge");
        Course course2 = new Course(202, "History", "Francisco Style");
        Course course3 = new Course(303, "Science", "Roberto Bernoulli");

        student.addCourse(course1);
        student.addCourse(course2);
        student.addCourse(course3);

        List<Course> students = student.getCourses();

        assertEquals(3, students.size(), "La cantidad de cursos no es correcta");
        Course course4 = new Course(202, "History", "Francisco Style");
        student.addCourse(course4);
        student.courserelimination();
        students = student.getCourses();
        assertEquals(3, students.size(),"La cantidad de cursos no es correcta");
        System.out.println("Test method of courses");
    }

    @Test
    public void test_method_of_course2() {
        Student student1= new Student("Taylor Swift", "taylor@swift.com");
        Course course1 = new Course(101, "Art", "Dolores Umbridge");
        Course course2 = new Course(202, "History", "Francisco Style");
        course1.inscription(student1);
        List<Course> curso= student1.getCourses();
        assertEquals(curso, student1.getCourses(), "El get de cursos no funciona correctamente");
        List<Course> cursito= new ArrayList<>();
        List<Object> coursito = new ArrayList<>();
        coursito.add(course2.getClassroom());
        coursito.add(course2.getSubject());
        coursito.add(course2.getTeacher());
        student1.setCourses(cursito);
        assertEquals(cursito, student1.getCourses(), "El Set de cursos no funciona correcta");
    }
}

