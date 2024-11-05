package com.university.evaluation.evaluationtypes;

import java.util.HashMap;
import java.util.Map;

public class EvaluationManager {
    private Map<String, EvaluationCriteria> evaluationMap;

    public EvaluationManager() {
        evaluationMap = new HashMap<>();
    }

    public void addEvaluation(String evaluationName, EvaluationCriteria criteria) {
        evaluationMap.put(evaluationName, criteria);
    }

    public EvaluationCriteria getCriteria(String evaluationName) {
        return evaluationMap.get(evaluationName);
    }

    public void displayEvaluations() {
        for (Map.Entry<String, EvaluationCriteria> entry : evaluationMap.entrySet()) {
            System.out.println("Evaluación: " + entry.getKey() + ", Criterios: " + entry.getValue());
        }
    }
}

