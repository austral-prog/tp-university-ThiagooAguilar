package com.university.evaluation.evaluationtypes;

import com.university.Entity;
import com.university.evaluation.methods.IGradeable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Evaluation implements IGradeable, Entity {
    private Integer id;
    private String studentName;
    private String subject;
    private String evaluationName;
    private String evaluationType;

    public Evaluation(String studentName, String evaluationType, String subject, String evaluationName) {
        this.studentName = studentName;
        this.subject = subject;
        this.evaluationName = evaluationName;
        this.evaluationType = evaluationType;
    }

    public Evaluation(){
        this.studentName = "";
        this.subject = "";
        this.evaluationName = "";
        this.evaluationType = "";
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getEvaluationType() {
        return evaluationType;
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

    public abstract List<Integer> getGrades();

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Evaluation that = (Evaluation) obj;
        return Objects.equals(studentName, that.studentName) &&
                Objects.equals(evaluationType, that.evaluationType) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(evaluationName, that.evaluationName);
    }

    public int hashCode() {
        return Objects.hash(studentName, evaluationType, subject, evaluationName);
    }


}




