package com.university.UniversityManager;

import com.university.Course.Course;
import com.university.Student.Student;

import java.io.*;
import java.util.*;

public class UniversityManager {

    public static void contarCursos(String inputCsv, String outputCsv) {
        // Mapa para almacenar los estudiantes por su nombre
        Map<String, Student> estudiantesMap = new HashMap<>();
        // Mapa para almacenar los cursos por su combinación de aula y materia
        Map<String, Course> cursosMap = new HashMap<>();

        // Leer el archivo CSV de entrada
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsv))) {
            String linea = br.readLine(); // Leer la primera línea (encabezado)

            while ((linea = br.readLine()) != null) {
                // Dividir la línea en columnas (separadas por coma)
                String[] columnas = linea.split(",");

                // Nombre y correo del estudiante están en la columna 2 y 3
                String nombreEstudiante = columnas[2];
                String emailEstudiante = columnas[3];

                // Nombre de la materia y profesor están en las columnas 4 y 5
                String materia = columnas[1];
                String profesor = columnas[4];
                int aula = Integer.parseInt(columnas[0]);

                // Crear o obtener el estudiante
                Student estudiante = estudiantesMap.getOrDefault(nombreEstudiante, new Student(nombreEstudiante, emailEstudiante));
                estudiantesMap.put(nombreEstudiante, estudiante); // Guardar el estudiante en el mapa

                // Crear una clave única para el curso
                String cursoKey = materia;

                // Crear o obtener el curso
                Course curso = cursosMap.get(cursoKey);
                if (curso == null) {
                    curso = new Course(aula, materia, profesor);
                    cursosMap.put(cursoKey, curso); // Guardar el curso en el mapa
                }

                // Inscribir al estudiante en el curso si no está ya inscrito
                if (!curso.getStudents().contains(estudiante)) {
                    curso.inscription(estudiante); // Inscribir al estudiante
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ordenar los estudiantes alfabéticamente
        List<Student> estudiantesOrdenados = new ArrayList<>(estudiantesMap.values());
        estudiantesOrdenados.sort(Comparator.comparing(Student::getName));

        // Escribir el archivo CSV de salida
        try (PrintWriter writer = new PrintWriter(new File(outputCsv))) {
            writer.println("Student_Name,Course_Count"); // Escribir encabezado

            // Escribir los estudiantes con la cantidad de cursos
            for (Student estudiante : estudiantesOrdenados) {
                writer.println(estudiante.getName() + "," + estudiante.Coursescount());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputCsv = "src/main/resources/input.csv";
        String outputCsv = "src/main/resources/solution.csv";

        contarCursos(inputCsv, outputCsv);

        System.out.println("Archivo generado correctamente.");
    }
}
