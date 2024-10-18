package com.university.Evaluation;

public abstract class Evaluation {
    private String evaluationType;
    private String evaluationName;
    private String exercise;
    private double grade;

    // Constructor
    public Evaluation(String evaluationType, String evaluationName, String exercise, double grade) {
        this.evaluationType = evaluationType;
        this.evaluationName = evaluationName;
        this.exercise = exercise;
        this.grade = grade;
    }

    // Getters and setters
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    //Creo 2 metodos abstractos para definir como calcular las calificaciones y otro que me de los detalles
    //de la evaluacion
    public abstract void calculateGrade();

    public abstract String getEvaluationDetails();
}
