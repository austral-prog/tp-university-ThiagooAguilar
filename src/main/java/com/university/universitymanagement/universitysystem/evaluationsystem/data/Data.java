package com.university.universitymanagement.universitysystem.evaluationsystem.data;

public class Data {
    private String studentName;
    private String subject;
    private String evaluationType;
    private String evaluationName;
    private String exercise;
    private Integer grade;

    public Data(String studentName, String subject, String evaluationType, String evaluationName, String exercise, Integer grade){
        this.studentName = studentName;
        this.subject = subject;
        this.evaluationType = evaluationType;
        this.evaluationName = evaluationName;
        this.exercise = exercise;
        this.grade = grade;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getEvaluationType() {
        return evaluationType;
    }
    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
    }
    public String getEvaluationName() {
        return evaluationName;
    }
    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }
    public String getExercise() {
        return exercise;

    }
    public void setExercise(String exercise) {
        this.exercise = exercise;

    }
    public Integer getGrade() {
        return grade;
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
