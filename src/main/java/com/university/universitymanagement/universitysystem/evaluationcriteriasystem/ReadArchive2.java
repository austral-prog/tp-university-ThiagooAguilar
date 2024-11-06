package com.university.universitymanagement.universitysystem.evaluationcriteriasystem;

import com.university.evaluation.evaluationtypes.evaluationcriteria.EvaluationCriteria;
import com.university.evaluation.evaluationtypes.evaluationcriteria.FinalResults;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ReadArchive2 {

    public static List<EvaluationCriteria> readArchive2(String filePath) {
        List<EvaluationCriteria> criteriaList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 4) {
                    System.err.println("Línea inválida o incompleta: " + line);
                    continue;
                }

                String subjectName = values[0];
                String criteriaType = values[1];
                double criteriaValue;

                try {
                    criteriaValue = Double.parseDouble(values[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Valor de criterio inválido en la línea: " + line);
                    continue;
                }


                List<String> evaluationNames = Arrays.asList(values).subList(3, values.length);

                EvaluationCriteria criteria = new EvaluationCriteria(subjectName, criteriaType, criteriaValue, evaluationNames);
                criteriaList.add(criteria);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return criteriaList;
    }

    public static List<FinalResults> readArchive3(String filePath) {
        List<FinalResults> evaluations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length < 4) {
                    continue;
                }

                String subjectName = data[0].trim();
                String evaluationName = data[1].trim();
                String studentName = data[2].trim();
                double grade = Double.parseDouble(data[3].trim());

                evaluations.add(new FinalResults(subjectName, evaluationName, studentName, grade));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error de formato en la columna de notas: " + e.getMessage());
        }

        return evaluations;
    }
}


