package com.university.evaluation.evaluationtypes;

import com.university.evaluation.methods.IGradeable;

import java.util.ArrayList;
import java.util.List;

public class PracticalWork extends Evaluation implements IGradeable {
    private List<Integer> grades;

    public PracticalWork(String studentName, String evaluationType, String subject, String evaluationName) {
        super(studentName,evaluationType, subject, evaluationName);
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
    @Override
    public double calculateGradesResult(List<Integer> grades) {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        Integer index= grades.size();
        Integer grade=grades.get(index-1);
        return grade;
    }
}
