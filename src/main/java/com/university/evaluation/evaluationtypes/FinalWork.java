package com.university.evaluation.evaluationtypes;

import com.university.evaluation.methods.IGradeable;

import java.util.ArrayList;
import java.util.List;

public class FinalWork extends Evaluation implements IGradeable {
    private List<Integer> grades;

    public FinalWork (String studentName, String subject, String evaluationName) {
        super(studentName, subject, evaluationName);
        this.grades = new ArrayList<>();
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }
    @Override
    public void results(Integer grade) {
        grades.add(grade);
    }
}