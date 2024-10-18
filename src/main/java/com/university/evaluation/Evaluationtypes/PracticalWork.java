package com.university.evaluation.Evaluationtypes;

import com.university.evaluation.methods.Gradeable;

import java.util.Map;

public class PracticalWork extends Evaluation implements Gradeable {
    public PracticalWork(String name, String evaluationName, String subject, Map<String,Integer> exercise) {
        super(name,evaluationName,subject, exercise);
    }

    @Override
    public boolean isPassed() {
        if (calcularPromedioDeMap(this.getExercises())>8.0){
            return true;
        }
        return false;
    }
}

