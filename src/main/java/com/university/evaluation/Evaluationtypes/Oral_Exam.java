package com.university.evaluation.Evaluationtypes;

import com.university.evaluation.methods.Gradeable;

import java.util.Map;

public class Oral_Exam extends Evaluation implements Gradeable {

    public Oral_Exam(String name, String evaluationName, String subject,Map<String,Integer> exercise) {
        super(name, evaluationName,subject, exercise);
    }

    @Override
    public boolean isPassed() {
        if (calcularPromedioDeMap(this.getExercises())>6.0){
            return true;
        }
        return false;
    }
}


