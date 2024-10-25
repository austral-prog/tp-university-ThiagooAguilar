package com.university.universitysystem;
import com.university.course.Course;
import com.university.evaluation.EvaluationManagement;
import com.university.student.Student;


import java.io.IOException;
import java.util.*;

import static com.university.universitysystem.universitysystem.EvaluationSystem.readArchive;
import static com.university.universitysystem.universitysystem.EvaluationSystem.writeArchive;
import static com.university.universitysystem.universitysystem.Universitysystem.*;



public class App {
    public static void contarCursos(String inputCsv, String outputCsv) {
        Map<String, Student> estudiantesMap = new HashMap<>();
        Map<String, Course> cursosMap = new HashMap<>();

        // Eliminar el archivo solution.csv si ya existe
        eliminarArchivoSiExiste(outputCsv);

        // Leer el archivo CSV y procesar datos
        leerArchivoCSV(inputCsv, estudiantesMap, cursosMap);

        // Ordenar estudiantes
        List<Student> estudiantesOrdenados = new ArrayList<>(estudiantesMap.values());
        estudiantesOrdenados.sort(Comparator.comparing(Student::getName));

        // Escribir resultados en el archivo de salida
        escribirArchivoCSV(outputCsv, estudiantesOrdenados);

    }





    public static void main(String[] args) throws IOException {
        String inputCsv = "src/main/resources/input.csv";
        String outputCsv = "src/main/resources/solution.csv";
        String inputCsv2 = "src/main/resources/input_2.csv";
        String outputCsv2 = "src/main/resources/solution2.csv";

        contarCursos(inputCsv, outputCsv);
        EvaluationManagement evaluationManagement = new EvaluationManagement();
        readArchive(inputCsv2, evaluationManagement);
        eliminarArchivoSiExiste(outputCsv2);
        writeArchive(outputCsv2, evaluationManagement);
    }
}




