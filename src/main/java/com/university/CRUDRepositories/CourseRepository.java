package com.university.CRUDRepositories;

import com.university.CRUDRepository;
import com.university.course.Course;
import com.university.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRepository implements CRUDRepository<Course> {
    private Map<Integer, Course> courses = new HashMap<>();
    private int currentId = 1;


    @Override
    public void create(Course course) {
        course.setId(currentId);
        courses.put(currentId, course);
        currentId++;
        System.out.println("Curso creado con ID: " + course.getId());
    }

    @Override
    public Course read(int id) {
        return courses.get(id);
    }

    @Override
    public void update(int id, Course updatedCourse) {
        if (courses.containsKey(id)) {
            updatedCourse.setId(id);
            courses.put(id, updatedCourse);
            System.out.println("Curso actualizado con ID: " + id);
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    @Override
    public void delete(int id) {
        if (courses.remove(id) != null) {
            System.out.println("Curso eliminado con ID: " + id);
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    @Override
    public String getIdentifier() {
        return "Course";
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }
}

