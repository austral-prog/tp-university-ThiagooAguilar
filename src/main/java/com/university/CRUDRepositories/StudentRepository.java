package com.university.CRUDRepositories;

import com.university.CRUDRepository;
import com.university.student.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentRepository implements CRUDRepository<Student> {
    private Map<Integer, Student> students= new HashMap<>();
    public int currentId = 1;

    @Override
    public void create(Student student) {
        student.setId(currentId);
        students.put(currentId, student);
        currentId++;
        System.out.println("El estudiante " + currentId + " created");
    }

    @Override
    public Student read(int id) {
        return students.get(id);
    }

    @Override
    public void update(int id, Student updatedStudent) {
        if (students.containsKey(id)) {
            students.put(id, updatedStudent);
            System.out.println("El estudiante " + id + " updated");
        }System.out.println("El estudiante " + id + " no existe");
    }

    @Override
    public void delete(int id) {
        if(students.remove(id) != null) {
            System.out.println("El estudiante " + id + " eliminado");
        }System.out.println("El estudiante " + id + " no existe");
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
