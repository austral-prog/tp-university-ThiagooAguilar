package com.university.evaluation.evaluationtypes;

import com.university.evaluation.methods.IGradeable;

import java.util.Objects;

public abstract class Evaluation implements IGradeable {
    private String studentName;
    private String subject;
    private String evaluationName;

    public Evaluation(String studentName, String subject, String evaluationName) {
        this.studentName = studentName;
        this.subject = subject;
        this.evaluationName = evaluationName;
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
    public abstract void results(Integer grade);

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Evaluation that = (Evaluation) obj;
        return Objects.equals(studentName, that.studentName) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(evaluationName, that.evaluationName);
    }

    public int hashCode() {
        return Objects.hash(studentName, subject, evaluationName);
    }
}




