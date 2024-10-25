package com.university.universitysystem.universitysystem.evaluationsystem.data;

import com.university.evaluation.evaluationtypes.Evaluation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WriteData {


    public static void writeToCsv(String outputCsv, List<Evaluation> evaluations) throws IOException {
        try (FileWriter writer = new FileWriter(outputCsv)) {

            writer.append("Subject_Name,Evaluation_Name,Student_Name,Grade\n");

                for (Evaluation evaluation : evaluations) {
                    writer.append(evaluation.getSubject()).append(",")
                        .append(evaluation.getEvaluationName()).append(",")
                        .append(evaluation.getStudentName()).append(",")
                        .append(String.valueOf(evaluation.calculateGradesResult(evaluation.getGrades()))).append("\n");
            }
        }
        System.out.println("Archivo CSV creado exitosamente en " + outputCsv);
    }}






