package com.university.studentTest.java;

import com.university.courseTest.java.Course;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String email;
    private List<Course<Student>> courses = new ArrayList<>();  // Lista de cursos donde está inscrito el estudiante

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addCourse(Course<Student> course) {
        if (!courses.contains(course)) {
            courses.add(course);  // Añadir solo si el estudiante no está ya inscrito en el curso
        }
    }

    // Método que devuelve la cantidad de cursos en los que el estudiante está inscrito
    public int Coursescount() {
        return courses.size();  // Retorna la cantidad de cursos en los que el estudiante está inscrito
    }

    public List<Course<Student>> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
