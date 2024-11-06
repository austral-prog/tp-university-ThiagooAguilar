package com.university.universitymanagement.universitysystem.evaluationsystem.data;

import com.university.evaluation.evaluationtypes.Evaluation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OrderData {


    public static void sortEvaluations(List<Evaluation> evaluations) {
        Collections.sort(evaluations, new Comparator<Evaluation>() {
            public int compare(Evaluation e1, Evaluation e2) {
                if (e1.getSubject().equals(e2.getSubject())) {
                    if (e1.getEvaluationName().equals(e2.getEvaluationName())) {
                        return e1.getStudentName().compareTo(e2.getStudentName());
                    } else
                        return e1.getEvaluationName().compareTo(e2.getEvaluationName());
                }
                else {
                    return e1.getSubject().compareTo(e2.getSubject());
                }
            }
        });
    }
}

