package com.university.universitysystem.universitysystem;

import com.university.evaluation.Evaluation.*;

import java.io.*;
import java.util.*;

public class Universitysystem2 {

    public static Map<String, Evaluation> readCSV(String inputCsv) throws IOException {
        Map<String, Evaluation> evaluationsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputCsv))) {
            String line;
            br.readLine(); // Leer encabezado

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                if (columns.length < 6) {
                    System.err.println("Línea inválida: " + line);
                    continue;
                }

                String studentName = columns[0];
                String subject = columns[1];
                String evaluationType = columns[2]; // No se usará en este ejemplo
                String evaluationName = columns[3];
                String exerciseName = columns[4]; // No se usará en este ejemplo
                int grade = Integer.parseInt(columns[5]);

                String key = studentName + "_" + subject + "_" + evaluationName; // Clave única para cada evaluación

                evaluationsMap.computeIfAbsent(key, k -> new Evaluation(studentName, subject, evaluationName))
                        .addGrade(grade);
            }
        }
        return evaluationsMap;
    }



    public static void writeCSV(String outputCsv, Map<String, Evaluation> evaluationsMap) throws IOException {
        try (FileWriter writer = new FileWriter(outputCsv)) {
            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");

            // Convertir el mapa a una lista y ordenar
            List<Evaluation> evaluationsList = new ArrayList<>(evaluationsMap.values());
            evaluationsList.sort(Comparator
                    .comparing(Evaluation::getSubject)
                    .thenComparing(Evaluation::getEvaluationName)
                    .thenComparing(Evaluation::getStudentName));

            for (Evaluation evaluation : evaluationsList) {
                double average = evaluation.calculateAverage();
                writer.write(String.format("%s,%s,%s,%.1f\n",
                        evaluation.getSubject(),
                        evaluation.getEvaluationName(),
                        evaluation.getStudentName(),
                        average));
            }
        }
    }
    }