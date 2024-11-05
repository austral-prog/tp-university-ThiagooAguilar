package com.university.universitymanagement.universitysystem;

import com.university.course.Course;
import com.university.student.Student;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Universitysystem {
    public static void eliminarArchivoSiExiste(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void leerArchivoCSV(String inputCsv, Map<String, Student> estudiantesMap, Map<String, Course> cursosMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsv))) {
            String linea = br.readLine(); // Leer la primera línea (encabezado)

            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");

                // Validar columnas
                if (columnas.length < 5) {
                    System.err.println("Línea inválida en el CSV: " + linea);
                    continue; // Saltar línea inválida
                }

                String nombreEstudiante = columnas[2];
                String emailEstudiante = columnas[3];
                String materia = columnas[1];
                String profesor = columnas[4];
                int aula;

                // Manejo de excepciones al convertir aula a entero
                try {
                    aula = Integer.parseInt(columnas[0]);
                } catch (NumberFormatException e) {
                    System.err.println("Aula inválida en la línea: " + linea);
                    continue; // Saltar línea inválida
                }

                Student estudiante = estudiantesMap.getOrDefault(nombreEstudiante, new Student(nombreEstudiante, emailEstudiante));
                estudiantesMap.put(nombreEstudiante, estudiante);

                // Usar solo la materia como clave para agrupar cursos
                Course curso = cursosMap.get(materia);
                if (curso == null) {
                    curso = new Course(aula, materia, profesor);
                    cursosMap.put(materia, curso);
                }

                if (!curso.getStudents().contains(estudiante)) {
                    curso.inscription(estudiante);
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

}

