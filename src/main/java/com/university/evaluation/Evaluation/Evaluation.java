package com.university.evaluation.Evaluation;

import java.util.*;

public class Evaluation {
    private String studentName;
    private String subject;
    private String evaluationName;
    private List<Integer> grades;

    public Evaluation(String studentName, String subject, String evaluationName) {
        this.studentName = studentName;
        this.subject = subject;
        this.evaluationName = evaluationName;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        return grades.isEmpty() ? 0.0 : grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public String getSubject() {
        return subject;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public String getStudentName() {
        return studentName;
    }
}
