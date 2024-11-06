package com.university.courseTest.java;

import java.util.ArrayList;
import java.util.List;

public class Course<T> {
    private int classroom;
    private String subject;
    private String teacher;
    private List<T> students = new ArrayList<>();  // Lista de estudiantes genéricos

    public Course(int classroom, String subject, String teacher) {
        this.classroom = classroom;
        this.subject = subject;
        this.teacher = teacher;
    }

    public void inscription(T student) {
        students.add(student);
    }

    public List<T> getStudents() {
        return students;
    }
    public int getClassroom() {
        return classroom;
    }
    public String getSubject() {
        return subject;
    }
    public String getTeacher() {
        return teacher;

    }
}

