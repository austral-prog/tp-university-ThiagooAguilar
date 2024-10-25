package com.university.universitysystem.universitysystem.evaluationsystem.data;

import com.university.evaluation.evaluationtypes.Evaluation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OrderData {


    public static void sortEvaluations(List<Evaluation> evaluations) {
        Collections.sort(evaluations, new Comparator<Evaluation>() {
            @Override
            public int compare(Evaluation e1, Evaluation e2) {
                // Ordena únicamente por el nombre de la materia (subject)
                return e1.getSubject().compareToIgnoreCase(e2.getSubject());
            }
        });
    }
}

