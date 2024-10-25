package com.university.evaluation.evaluationtypes;

import com.university.evaluation.methods.IGradeable;

import java.util.ArrayList;
import java.util.List;

public class FinalPracticalWork extends Evaluation implements IGradeable {
    private List<Integer> grades;

    public FinalPracticalWork(String studentName, String evaluationType,String subject, String evaluationName) {
        super(studentName, evaluationType, subject, evaluationName);
        this.grades = new ArrayList<>();
    }


    @Override
    public void results(Integer grade) {
        grades.add(grade);
    }

    @Override
    public List<Integer> getGrades() {
        return grades;
    }
}