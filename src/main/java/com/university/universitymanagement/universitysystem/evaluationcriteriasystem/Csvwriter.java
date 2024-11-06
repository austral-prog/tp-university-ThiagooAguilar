package com.university.universitymanagement.universitysystem.evaluationcriteriasystem;

import com.university.evaluation.evaluationtypes.Evaluation;
import com.university.evaluation.evaluationtypes.evaluationcriteria.StatusResults;
import jdk.jshell.Snippet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Csvwriter {

    public static void writeToCsv2(String outputCsv, List<StatusResults> results) throws IOException {
        try (FileWriter writer = new FileWriter(outputCsv)) {

            writer.append("Student_Name,Subject_Name,Evaluation_Name,Grade,Status\n");

            for (StatusResults result : results) {
                String evaluationNames = String.join(",", result.getEvaluationName());

                writer.append(result.getStudentName()).append(",")
                        .append(result.getSubjectName()).append(",")
                        .append(evaluationNames).append(",")  // Usar la cadena sin corchetes
                        .append(String.valueOf(result.getGrade())).append(",")
                        .append(result.getStatus()).append("\n");
            }
        }
        System.out.println("Archivo CSV creado exitosamente en " + outputCsv);
    }
}

