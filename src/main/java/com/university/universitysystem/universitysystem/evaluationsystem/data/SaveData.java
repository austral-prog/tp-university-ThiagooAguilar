package com.university.universitysystem.universitysystem.evaluationsystem.data;

import com.university.evaluation.EvaluationManagement;
import com.university.evaluation.evaluationtypes.*;

import java.util.ArrayList;
import java.util.List;

public class SaveData<T extends Evaluation> {

    public static List<Evaluation> saveData(List<Data> information, EvaluationManagement evaluationManagement) {
        List<Evaluation> evaluations = new ArrayList<>();


        for (Data data : information) {
            if (data.getEvaluationType().equals("WRITTEN_EXAM")) {
                WrittenExam evaluation = new WrittenExam(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                if (evaluations.contains(evaluation)) {
                    evaluation.results(data.getGrade());
                } else {
                    evaluations.add(evaluation);
                    evaluation.results(data.getGrade());
                }
            }
            if (data.getEvaluationType().equals("FINAL_PRACTICAL_WORK")) {
                FinalPracticalWork evaluation = new FinalPracticalWork(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                if (evaluations.contains(evaluation)) {
                    evaluation.results(data.getGrade());
                } else {
                    evaluations.add(evaluation);
                    evaluation.results(data.getGrade());
                }
            }
            if (data.getEvaluationType().equals("ORAL_EXAM")) {
                OralExam evaluation = new OralExam(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                if (evaluations.contains(evaluation)) {
                    evaluation.results(data.getGrade());
                } else {
                    evaluations.add(evaluation);
                    evaluation.results(data.getGrade());
                }
            }
            if (data.getEvaluationType().equals("PRACTICAL_WORK")) {
                PracticalWork evaluation = new PracticalWork(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                if (evaluations.contains(evaluation)) {
                    evaluation.results(data.getGrade());
                } else {
                    evaluations.add(evaluation);
                    evaluation.results(data.getGrade());
                }
            }
        }
        return evaluations;
    }
}

