package com.university.course;


import com.university.Entity;
import com.university.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course implements Entity {
    private int id;
    private int classroom;
    private String subject;
    private String teacher;
    List<Student> students = new ArrayList<>();

    public Course(int classroom, String subject, String teacher) {
        this.classroom = classroom;
        this.subject = subject;
        this.teacher = teacher;

    }
    public Course() {
        this.classroom = 0;
        this.subject = "";
        this.teacher = "";
        this.students = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClassroom() {

        return classroom;
    }
    public void setClassroom(int classroom) {

        this.classroom = classroom;
    }
    public String getSubject() {

        return subject;
    }
    public void setSubject(String subject) {

        this.subject = subject;
    }
    public String getTeacher() {

        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void removeStudent(Student student) {
        students.remove(student);
        List<Course> cursos =student.getCourses();
        cursos.remove(this);
    }
    public void inscription(Student student1){
        students.add(student1);
        student1.addCourse(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return
                subject.equals(course.subject) &&
                        teacher.equals(course.teacher);
    }

    public int hashCode() {
        return Objects.hash(classroom, subject, teacher);
    }
}