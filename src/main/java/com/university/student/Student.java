package com.university.student;

import com.university.Entity;
import com.university.course.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student implements Entity {
    private int id;
    private String name;
    private String email;
    private List<Course> courses = new ArrayList<>();

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
    public int Coursescount(){
        return courses.size();
    }
    public void courserelimination() {
        Set<Course> Listasinrepetir = new HashSet<>(courses);
        courses = new ArrayList<>(Listasinrepetir);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
