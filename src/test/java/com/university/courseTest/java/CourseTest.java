package com.university.courseTest.java;

import com.university.studentTest.java.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private Student student;
    private Course<Student> course;

    @BeforeEach
    public void setUp() {
        student = new Student("John Doe", "john.doe@example.com");
        course = new Course<>(101, "Math", "Dr. Smith");
    }

    @Test
    public void testInscription() {
        course.inscription(student);
        assertTrue(course.getStudents().contains(student), "El estudiante debería estar inscrito en el curso.");
    }

    @Test
    public void testGetClassroom() {
        assertEquals(101, course.getClassroom(), "El aula del curso debería ser 101.");
    }

    @Test
    public void testGetSubject() {
        assertEquals("Math", course.getSubject(), "La materia del curso debería ser 'Math'.");
    }

    @Test
    public void testGetTeacher() {
        assertEquals("Dr. Smith", course.getTeacher(), "El profesor del curso debería ser 'Dr. Smith'.");
    }
}
