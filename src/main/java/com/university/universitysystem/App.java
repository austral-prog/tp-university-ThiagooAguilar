package com.university.universitysystem;
import com.university.course.Course;
import com.university.evaluation.Evaluation.Evaluation;
import com.university.student.Student;
import com.university.universitysystem.universitysystem.evaluationsystem.data.Data;
import com.university.universitysystem.universitysystem.evaluationsystem.data.SaveData;


import java.io.IOException;
import java.util.*;
import static com.university.universitysystem.universitysystem.Universitysystem.*;
import static com.university.universitysystem.universitysystem.evaluationsystem.data.OrderData.sortEvaluations;
import static com.university.universitysystem.universitysystem.evaluationsystem.data.ReadArchive.readArchive;
import static com.university.universitysystem.universitysystem.evaluationsystem.data.SaveData.saveData;
import static com.university.universitysystem.universitysystem.evaluationsystem.data.WriteData.writeToCsv;


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
        List<Data> data= readArchive(inputCsv2);
        List<Evaluation> evaluations =  saveData(data,evaluationManagement);
        sortEvaluations(evaluations);
        writeToCsv(outputCsv2, evaluations);
    }
}




