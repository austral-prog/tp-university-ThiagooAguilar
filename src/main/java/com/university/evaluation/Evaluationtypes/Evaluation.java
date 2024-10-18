package com.university.evaluation.Evaluationtypes;

import java.util.HashMap;
import java.util.Map;

public abstract class Evaluation {
    private static String name;
    private static String evaluationName;
    private static String subject;
    private Map<String, Integer> exercises;

    public Evaluation(String name,String evaluationName,String subject, Map<String, Integer> exercises) {
        this.name = name;
        this.evaluationName = evaluationName;
        this.subject = subject;
        this.exercises = exercises;
    }
    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }
    public static String getSubject(){
        return subject;
    }
    public void setsubject(String subject){
        this.subject = subject;
    }

    public static String getEvaluationName() {
        return evaluationName;
    }

    public double calcularPromedioDeMap(Map<String, Integer> examScores) {
        int sum = 0;
        for (Integer score : examScores.values()) {
            sum += score;}
            return !examScores.isEmpty() ? (double) sum / examScores.size() : 0.0;
    }

    public Map<String, Integer> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return "Evaluation: " + evaluationName + ", Exercise: ";
    }
}
