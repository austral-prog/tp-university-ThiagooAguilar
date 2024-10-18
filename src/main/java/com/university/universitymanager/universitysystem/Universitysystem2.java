package com.university.universitymanager.universitysystem;

import com.university.evaluation.Evaluationtypes.*;
import com.university.student.Student;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Universitysystem2 {

    public static void ReadarchiveCSV2(String inputCsv, Map<Evaluation, Map<String, Integer>> exercise_scores) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(inputCsv))) {
            String linea = br.readLine(); // Leer la primera línea (encabezado)

            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");
                if (columnas.length < 6) {
                    System.err.println("Línea inválida en el CSV: " + linea);
                    continue; // Saltar línea inválida
                }
                String name = columnas[0];
                String subject = columnas[1];
                String evaluation_type = columnas[2];
                String evaluation_name = columnas[3];
                String exercise_name = columnas[4];
                Integer grade = Integer.parseInt(columnas[5]);
                Map<String, Integer> results = new HashMap<>();
                results.put(exercise_name, grade);


                if (evaluation_type.equals("WRITTEN_EXAM")) {
                    Evaluation evaluation = new WrittenExam(name, evaluation_name, subject, results);
                    if (exercise_scores.containsKey(evaluation)) {
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    } else {
                        exercise_scores.put(evaluation, new HashMap<>());
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    }
                } else if (evaluation_type.equals("ORAL_EXAM")) {
                    Evaluation evaluation = new Oral_Exam(name, evaluation_name, subject, results);
                    if (exercise_scores.containsKey(evaluation)) {
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    } else {
                        exercise_scores.put(evaluation, new HashMap<>());
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    }
                } else if (evaluation_type.equals("PRACTICAL_WORK")) {
                    Evaluation evaluation = new PracticalWork(name, evaluation_name, subject, results);
                    if (exercise_scores.containsKey(evaluation)) {
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    } else {
                        exercise_scores.put(evaluation, new HashMap<>());
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    }
                } else if (evaluation_type.equals("FINAL_PRACTICAL_WORK")) {
                    Evaluation evaluation = new FinalPracticalWork(name, evaluation_name, subject, results);
                    if (exercise_scores.containsKey(evaluation)) {
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    } else {
                        exercise_scores.put(evaluation, new HashMap<>());
                        exercise_scores.get(evaluation).put(exercise_name, grade);
                    }
                }
            }

        }
    }
    private double calcularPromedioDeMap(Map<String, Integer> examScores) {
        int sum = 0;
        for (Integer score : examScores.values()){
            sum += score;
        }
        return !examScores.isEmpty() ? (double) sum / examScores.size() : 0.0;    }

    public static void WriteArchiveCSV(String outputCsv, Map<Evaluation, Map<String, Integer>> exams) {
        try (PrintWriter writer = new PrintWriter(new File(outputCsv))) {
            writer.println("Subject_Name,Evaluation_Name,Student_Name,Exercise_Name,Grade");

            for (Map.Entry<Evaluation, Map<String, Integer>> entry : exams.entrySet()) {
                Evaluation evaluation = entry.getKey();
                Map<String, Integer> exercises = entry.getValue();

                for (Map.Entry<String, Integer> exerciseEntry : exercises.entrySet()) {
                    String exerciseName = exerciseEntry.getKey();
                    Integer grade = exerciseEntry.getValue();

                    // Escribir una línea para cada ejercicio
                    writer.println(evaluation.getSubject() + "," +
                            evaluation.getEvaluationName() + "," +
                            evaluation.getName() + "," +
                            exerciseName + "," +
                            grade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


