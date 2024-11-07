package com.university.course;

import com.university.student.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    @Test
    public void testcoursecreationandinscription(){
        Course course = new Course(405, "English", "Taylor Swift");
        Student student = new Student("John", "jhoon_thebest@gmail.com");
        course.inscription(student);
        List<Student> students = course.getStudents();
        List<Course>  cursos = student.getCourses();
        assertEquals(1,students.size() ,"El estudiante no ha sido inscripto en la lista del curso ");
        assertEquals(1,cursos.size(),"La lista de cursos del estudiante no ha sido actualizada");
        course.removeStudent(student);
        cursos = student.getCourses();
        students = course.getStudents();
    }
    @Test
    public void testremoveandtechers(){
        Course course = new Course(405, "Maths", "L'hopital");
        Student student = new Student("John", "jhoon_thebest@gmail.com");
        course.inscription(student);
        course.removeStudent(student);
        List<Student> students = course.getStudents();
        List<Course>  cursos = student.getCourses();
        assertEquals(0,students.size(),"El estudiante no ha sido eliminado" );
        assertEquals(0,cursos.size(), "La lista de cursos del estudiante no ha sido actualizada");
        String name= course.getTeacher();
        assertEquals(name,"L'hopital","El profesor no es el mismo que dirige el curso");
        course.setTeacher("Taylor swift");
        String other_name= course.getTeacher();
        assertEquals(other_name, "Taylor swift", "El profesor no  ha sido cambiado");
    }
    @Test
    public void testsettstudents(){
        Course course = new Course(405, "Maths", "L'hopital");
        Student student = new Student("Harry", "Harry@gmail.com");
        Student student2 = new Student("Jane", "Jane@gmail.com");
        Student student3 = new Student("Louis", "louis@gmail.com");
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        course.setStudents(students);
        List<Student> students4 = course.getStudents();
        assertEquals(3, students4.size(), "La lista de estudiantes no ha sido actualizada");
    }
}