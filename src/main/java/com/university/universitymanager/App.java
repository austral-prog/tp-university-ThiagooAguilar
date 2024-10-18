package com.university.universitymanager;
import com.university.course.Course;
import com.university.evaluation.Evaluationtypes.Evaluation;
import com.university.student.Student;
import java.io.IOException;
import java.util.*;
import static com.university.universitymanager.universitysystem.Universitysystem.*;
import static com.university.universitymanager.universitysystem.Universitysystem2.ReadarchiveCSV2;
import  com.university.universitymanager.universitysystem.Universitysystem2.*;
import javax.security.auth.Subject;


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

    public static void definirnotas(String inputCsv, String outputCsv) throws IOException {
        Map<Evaluation,Map<String,Integer>> examenes = new HashMap<>();
        eliminarArchivoSiExiste(outputCsv);
        ReadarchiveCSV2(inputCsv,examenes);
        Map<Evaluation,Map<String,Integer>> examenesordenados = new HashMap<>(examenes);
    }

    public static void main(String[] args) {
        String inputCsv = "src/main/resources/input.csv";
        String outputCsv = "src/main/resources/solution.csv";

        contarCursos(inputCsv, outputCsv);

        System.out.println("Archivo generado correctamente.");
}}




