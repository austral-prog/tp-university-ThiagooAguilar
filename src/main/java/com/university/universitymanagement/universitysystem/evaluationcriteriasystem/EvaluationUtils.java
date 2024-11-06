package com.university.universitymanagement.universitysystem.evaluationcriteriasystem;
import com.university.evaluation.evaluationtypes.evaluationcriteria.EvaluationCriteria;
import com.university.evaluation.evaluationtypes.evaluationcriteria.FinalResults;
import com.university.evaluation.evaluationtypes.evaluationcriteria.StatusResults;

import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EvaluationUtils {

    public static List<StatusResults> evaluateResults(
            Map<String, Map<EvaluationCriteria, List<FinalResults>>> studentResultsMap) {

        List<StatusResults> resultsList = new ArrayList<>();

        for (Map.Entry<String, Map<EvaluationCriteria, List<FinalResults>>> studentEntry : studentResultsMap.entrySet()) {
            String studentName = studentEntry.getKey();
            Map<EvaluationCriteria, List<FinalResults>> criteriaMap = studentEntry.getValue();

            for (Map.Entry<EvaluationCriteria, List<FinalResults>> criteriaEntry : criteriaMap.entrySet()) {
                EvaluationCriteria criteria = criteriaEntry.getKey();
                List<FinalResults> finalResultsList = criteriaEntry.getValue();


                if ("MAX_ABOVE_VALUE".equals(criteria.getCriteriaType())) {
                    for (FinalResults finalResult : finalResultsList) {
                        double grade = finalResult.getGrade();
                        String status = (grade > criteria.getCriteriaValue()) ? "Desaprobado" : "Aprobado";
                        double finalGrade = grade;

                        StatusResults statusResult = new StatusResults(finalResult.getSubjectName(),
                                criteria.getEvaluationNames(),
                                studentName,
                                finalGrade,
                                status);  // Agregamos el status
                        resultsList.add(statusResult);
                    }

                } else if ("MIN_ABOVE_VALUE".equals(criteria.getCriteriaType()) || "AVERAGE_ABOVE_VALUE".equals(criteria.getCriteriaType())) {
                    double sum = 0;
                    for (FinalResults finalResult : finalResultsList) {
                        sum += finalResult.getGrade();
                    }
                    double averageGrade = sum / finalResultsList.size();

                    String status = (averageGrade > criteria.getCriteriaValue()) ? "Aprobado" : "Desaprobado";
                    double finalGrade = averageGrade;

                    StatusResults statusResult = new StatusResults(criteria.getSubjectName(),
                            criteria.getEvaluationNames(),
                            studentName,
                            finalGrade,
                            status);
                    resultsList.add(statusResult);
                }
            }
        }

        return resultsList;
    }
}


