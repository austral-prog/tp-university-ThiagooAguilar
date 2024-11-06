package com.university.studentTest.java;

import com.university.courseTest.java.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student student;
    private Course<Student> course;

    @BeforeEach
    void setUp() {
        student = new Student("John Doe", "john.doe@example.com");
        course = new Course<>(101, "Math", "Dr. Smith");
    }

    @Test
    void testAddCourse() {
        student.addCourse(course);
        assertEquals(1, student.Coursescount(), "El estudiante debería estar inscrito en 1 curso.");
    }

    @Test
    void testAddSameCourseTwice() {
        student.addCourse(course);
        student.addCourse(course);  // Intentamos añadir el mismo curso nuevamente
        assertEquals(1, student.Coursescount(), "El estudiante no debería estar inscrito en el mismo curso más de una vez.");
    }

    @Test
    void testGetCourses() {
        student.addCourse(course);
        assertTrue(student.getCourses().contains(course), "El curso debería estar en la lista de cursos del estudiante.");
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", student.getName(), "El nombre del estudiante debería ser 'John Doe'.");
    }

    @Test
    void testGetEmail() {
        assertEquals("john.doe@example.com", student.getEmail(), "El correo del estudiante debería ser 'john.doe@example.com'.");
    }
}
