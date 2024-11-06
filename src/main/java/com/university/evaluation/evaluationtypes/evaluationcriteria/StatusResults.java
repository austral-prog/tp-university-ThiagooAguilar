package com.university.evaluation.evaluationtypes.evaluationcriteria;

import java.util.List;

public class StatusResults {
    private String subject_name;
    private List<String> evaluation_names;
    private String student_name;
    private double grade;
    private String status;

    public  StatusResults(String subject_name, List<String> evaluation_name, String student_name, double grade, String status) {
        this.subject_name = subject_name;
        this.evaluation_names = evaluation_name;
        this.student_name = student_name;
        this.grade = grade;
        this.status = status;
    }
    public String getSubjectName() {
        return subject_name;
    }
    public void setSubjectName(String subject_name) {
        this.subject_name = subject_name;
    }
    public List<String> getEvaluationName() {
        return evaluation_names;
    }
    public void setEvaluationName(List<String> evaluation_name) {
        this.evaluation_names = evaluation_name;
    }
    public String getStudentName() {
        return student_name;
    }
    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }
    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}