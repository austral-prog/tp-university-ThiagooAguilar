package com.university.universitymanagement.universitysystem;

import com.university.courseTest.java.Course;
import com.university.studentTest.java.Student;

import java.io.*;
import java.util.*;
public class Universitysystem {


    public static <T> void inscription(Course<T> course, T student) {
        course.inscription(student);
        if (student instanceof Student) {
            Student s = (Student) student;
            s.addCourse((Course<Student>) course);
        }
    }

    public static void leerArchivoCSV(String inputCsv, Map<String, Student> estudiantesMap, Map<String, Course<Student>> cursosMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsv))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");
                if (columnas.length < 5) {
                    System.err.println("Línea inválida en el CSV: " + linea);
                    continue;
                }

                String nombreEstudiante = columnas[2];
                String emailEstudiante = columnas[3];
                String materia = columnas[1];
                String profesor = columnas[4];
                int aula;

                try {
                    aula = Integer.parseInt(columnas[0]);
                } catch (NumberFormatException e) {
                    System.err.println("Aula inválida en la línea: " + linea);
                    continue;
                }

                Student estudiante = estudiantesMap.getOrDefault(nombreEstudiante, new Student(nombreEstudiante, emailEstudiante));
                estudiantesMap.put(nombreEstudiante, estudiante);

                Course<Student> curso = cursosMap.get(materia);
                if (curso == null) {
                    curso = new Course<>(aula, materia, profesor);
                    cursosMap.put(materia, curso);
                }

                if (!curso.getStudents().contains(estudiante)) {
                    inscription(curso, estudiante);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirArchivoCSV(String outputCsv, List<Student> estudiantes) {
        try (PrintWriter writer = new PrintWriter(new File(outputCsv))) {
            writer.println("Student_Name,Course_Count");
            for (Student estudiante : estudiantes) {
                writer.println(estudiante.getName() + "," + estudiante.Coursescount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarArchivoSiExiste(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void contarCursos(String inputCsv, String outputCsv) {
        Map<String, Student> estudiantesMap = new HashMap<>();
        Map<String, Course<Student>> cursosMap = new HashMap<>();

        eliminarArchivoSiExiste(outputCsv);

        leerArchivoCSV(inputCsv, estudiantesMap, cursosMap);

        List<Student> estudiantesOrdenados = new ArrayList<>(estudiantesMap.values());
        estudiantesOrdenados.sort(Comparator.comparing(Student::getName));

        escribirArchivoCSV(outputCsv, estudiantesOrdenados);
    }
}
