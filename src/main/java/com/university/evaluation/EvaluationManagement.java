package com.university.evaluation;

import com.university.evaluation.evaluationtypes.Evaluation;

import java.util.ArrayList;
import java.util.List;


public class EvaluationManagement {
    List<Evaluation> finalExams;
    List<Evaluation> finalWork;
    List<Evaluation> partialExam;
    List<Evaluation> practicalWork;

    public EvaluationManagement() {
        finalExams = new ArrayList<>();
        finalWork = new ArrayList<>();
        partialExam= new ArrayList<>();
        practicalWork= new ArrayList<>();
    }
    public void addEvaluation(Evaluation evaluation) {
        String name = evaluation.getEvaluationName();

        if (name.equals("Segundo Parcial") || name.equals("Primer Parcial")) {
            partialExam.add(evaluation);
        } else if (name.equals("Examen Final")) {
            finalExams.add(evaluation);
        } else if (name.substring(0, 2).equals("TP")) {
            if (name.equals("TP Final")) {
                partialExam.add(evaluation);
            }
            practicalWork.add(evaluation);
        }
    }
    public List<Evaluation> getFinalExams() {
        return finalExams;
    }
    public List<Evaluation> getFinalWork() {
        return finalWork;
    }
    public List<Evaluation> getPartialExam() {
        return partialExam;
    }
    public List<Evaluation> getPracticalWork() {
        return practicalWork;
    }

}
