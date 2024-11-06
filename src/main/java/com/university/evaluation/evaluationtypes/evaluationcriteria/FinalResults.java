package com.university.evaluation.evaluationtypes.evaluationcriteria;
public class FinalResults {
    private String subject_name;
    private String evaluation_name;
    private String student_name;
    private double grade;
    public  FinalResults(String subject_name, String evaluation_name, String student_name, double grade) {
        this.subject_name = subject_name;
        this.evaluation_name = evaluation_name;
        this.student_name = student_name;
        this.grade = grade;
    }
    public String getSubjectName() {
        return subject_name;
    }
    public void setSubjectName(String subject_name) {
        this.subject_name = subject_name;
    }
    public String getEvaluationName() {
        return evaluation_name;
    }
    public void setEvaluationName(String evaluation_name) {
        this.evaluation_name = evaluation_name;
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

}
