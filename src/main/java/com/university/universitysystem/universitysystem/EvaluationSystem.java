package com.university.universitysystem.universitysystem;

import com.university.course.Course;
import com.university.evaluation.EvaluationManagement;
import com.university.evaluation.evaluationtypes.*;
import com.university.evaluation.methods.IGradeable;
import com.university.student.Student;

import java.io.*;
import java.util.*;

public class EvaluationSystem {

    public static void readArchive(String inputCsv, EvaluationManagement evaluationManagement) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsv))) {
            String linea = br.readLine(); // Leer la primera línea (encabezado)

            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");

                // Validar columnas
                if (columnas.length < 6) {
                    System.err.println("Línea inválida en el CSV: " + linea);
                    continue; // Saltar línea inválida
                }

                String studentName = columnas[0];
                String subject = columnas[1];
                String evaluationName = columnas[3]; // Solo necesitas el nombre de la evaluación
                Integer grade = Integer.valueOf(columnas[5]);

                Evaluation existingEvaluation = null;

                // Verificar si ya existe una evaluación con el mismo estudiante, materia y nombre de evaluación
                for (Evaluation evaluation : evaluationManagement.getFinalExams()) {
                    if (evaluation.getStudentName().equals(studentName) &&
                            evaluation.getSubject().equals(subject) &&
                            evaluation.getEvaluationName().equals(evaluationName)) {
                        existingEvaluation = evaluation;
                        break;
                    }
                }

                // Si no se encontró en finalExams, verificar en otras listas
                if (existingEvaluation == null) {
                    for (Evaluation evaluation : evaluationManagement.getFinalWork()) {
                        if (evaluation.getStudentName().equals(studentName) &&
                                evaluation.getSubject().equals(subject) &&
                                evaluation.getEvaluationName().equals(evaluationName)) {
                            existingEvaluation = evaluation;
                            break;
                        }
                    }
                }

                if (existingEvaluation == null) {
                    for (Evaluation evaluation : evaluationManagement.getPartialExam()) {
                        if (evaluation.getStudentName().equals(studentName) &&
                                evaluation.getSubject().equals(subject) &&
                                evaluation.getEvaluationName().equals(evaluationName)) {
                            existingEvaluation = evaluation;
                            break;
                        }
                    }
                }

                if (existingEvaluation == null) {
                    for (Evaluation evaluation : evaluationManagement.getPracticalWork()) {
                        if (evaluation.getStudentName().equals(studentName) &&
                                evaluation.getSubject().equals(subject) &&
                                evaluation.getEvaluationName().equals(evaluationName)) {
                            existingEvaluation = evaluation;
                            break;
                        }
                    }
                }

                // Si no se encontró, crear una nueva evaluación
                if (existingEvaluation == null) {
                    if (evaluationName.equals("Examen Final")) {
                        existingEvaluation = new FinalExam(studentName, subject, evaluationName);
                    } else if (evaluationName.substring(0, 2).equals("TP")) {
                        if (evaluationName.equals("TP Final")) {
                            existingEvaluation = new FinalWork(studentName, subject, evaluationName);
                        } else {
                            existingEvaluation = new PracticalWork(studentName, subject, evaluationName);
                        }
                    } else if (evaluationName.equals("Segundo Parcial") || evaluationName.equals("Primer Parcial")) {
                        existingEvaluation = new PartialExam(studentName, subject, evaluationName);
                    }
                    evaluationManagement.addEvaluation(existingEvaluation);
                }

                // Agregar la calificación a la evaluación existente
                existingEvaluation.results(grade);
            }
        }
    }

    public static void writeArchive(String outputCsv, EvaluationManagement evaluationManagement) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new File(outputCsv))) {
            writer.println("Subject_Name,Evaluation_Name,Student_Name,Grade");

            List<Evaluation> allEvaluations = new ArrayList<>();
            allEvaluations.addAll(evaluationManagement.getFinalExams());
            allEvaluations.addAll(evaluationManagement.getPracticalWork());
            allEvaluations.addAll(evaluationManagement.getPartialExam());
            allEvaluations.addAll(evaluationManagement.getFinalWork());

            // Ordenar la lista de todas las evaluaciones por materia, tipo de evaluación y luego por nombre de estudiante
            Collections.sort(allEvaluations, Comparator.comparing(Evaluation::getSubject)
                    .thenComparing(Evaluation::getEvaluationName) // Añadido para ordenar por tipo de evaluación
                    .thenComparing(Evaluation::getStudentName));

            // Escribir las evaluaciones ordenadas en el archivo CSV
            for (Evaluation evaluation : allEvaluations) {
                if (evaluation instanceof PartialExam) {
                    List<Integer> grades = ((PartialExam) evaluation).getGrades();
                    writer.println(evaluation.getSubject() + "," + evaluation.getEvaluationName() + "," + evaluation.getStudentName()
                            + "," + evaluation.toAverage(grades));
                } else if (evaluation instanceof FinalExam) {
                    List<Integer> grades = ((FinalExam) evaluation).getGrades();
                    writer.println(evaluation.getSubject() + "," + evaluation.getEvaluationName() + "," + evaluation.getStudentName()
                            + "," + evaluation.toAverage(grades));
                } else if (evaluation instanceof FinalWork) {
                    List<Integer> grades = ((FinalWork) evaluation).getGrades();
                    writer.println(evaluation.getSubject() + "," + evaluation.getEvaluationName() + "," + evaluation.getStudentName()
                            + "," + evaluation.toAverage(grades));
                } else if (evaluation instanceof PracticalWork) {
                    List<Integer> grades = ((PracticalWork) evaluation).getGrades();
                    writer.println(evaluation.getSubject() + "," + evaluation.getEvaluationName() + "," + evaluation.getStudentName()
                            + "," + evaluation.toAverage(grades));
                }
            }
        }
    }
}

