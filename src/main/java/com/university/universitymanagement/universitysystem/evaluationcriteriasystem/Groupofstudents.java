package com.university.universitymanagement.universitysystem.evaluationcriteriasystem;

import com.university.evaluation.evaluationtypes.evaluationcriteria.EvaluationCriteria;
import com.university.evaluation.evaluationtypes.evaluationcriteria.FinalResults;

import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

public class Groupofstudents {

    public static Map<String, List<FinalResults>> groupResultsByStudent(List<FinalResults> finalResultsList) {
        Map<String, List<FinalResults>> studentResultsMap = new HashMap<>();

        for (FinalResults result : finalResultsList) {
            studentResultsMap
                    .computeIfAbsent(result.getStudentName(), k -> new ArrayList<>())
                    .add(result);
        }

        return studentResultsMap;
    }

    public static Map<String, Map<EvaluationCriteria, List<FinalResults>>> groupResultsByEvaluationCriteria(
            Map<String, List<FinalResults>> studentResultsMap,
            List<EvaluationCriteria> evaluationCriteriaList) {

        Map<String, Map<EvaluationCriteria, List<FinalResults>>> groupedResults = new HashMap<>();

        for (Map.Entry<String, List<FinalResults>> entry : studentResultsMap.entrySet()) {
            String studentName = entry.getKey();
            List<FinalResults> studentResults = entry.getValue();


            Map<EvaluationCriteria, List<FinalResults>> studentGroupedResults = new HashMap<>();

            for (FinalResults finalResult : studentResults) {
                for (EvaluationCriteria criteria : evaluationCriteriaList) {
                    if (criteria.getEvaluationNames().contains(finalResult.getEvaluationName()) &&
                            finalResult.getSubjectName().equals(criteria.getSubjectName())) {
                        studentGroupedResults
                                .computeIfAbsent(criteria, k -> new ArrayList<>())
                                .add(finalResult);
                    }
                }
            }

            groupedResults.put(studentName, studentGroupedResults);
        }
        return groupedResults;
    }

}
