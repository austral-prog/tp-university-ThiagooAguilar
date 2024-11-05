package com.university.universitymanagement.universitysystem.evaluationsystem.data;

import com.university.evaluation.evaluationtypes.*;

import java.util.ArrayList;
import java.util.List;

public class SaveData<T extends Evaluation> {

    public static List<Evaluation> saveData(List<Data> information) {
        List<Evaluation> evaluations = new ArrayList<>();

        for (Data data : information) {
            Evaluation existingEvaluation = null;

            for (Evaluation eval : evaluations) {
                if (eval.getStudentName().equals(data.getStudentName()) &&
                        eval.getSubject().equals(data.getSubject()) &&
                        eval.getEvaluationType().equals(data.getEvaluationType()) &&
                        eval.getEvaluationName().equals(data.getEvaluationName())) {

                    existingEvaluation = eval;
                    break;
                }
            }

            if (existingEvaluation != null) {
                existingEvaluation.results(data.getGrade());
                System.out.println("Adding grade " + data.getGrade() + " to existing evaluation: " + existingEvaluation.getGrades());
            } else {
                Evaluation newEvaluation;
                switch (data.getEvaluationType()) {
                    case "WRITTEN_EXAM":
                        newEvaluation = new WrittenExam(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                        break;
                    case "FINAL_PRACTICAL_WORK":
                        newEvaluation = new FinalPracticalWork(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                        break;
                    case "ORAL_EXAM":
                        newEvaluation = new OralExam(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                        break;
                    case "PRACTICAL_WORK":
                        newEvaluation = new PracticalWork(data.getStudentName(), data.getEvaluationType(), data.getSubject(), data.getEvaluationName());
                        break;
                    default:
                        continue;
                }
                newEvaluation.results(data.getGrade());
                evaluations.add(newEvaluation);
                System.out.println("Creating new evaluation with grade " + data.getGrade() + ": " + newEvaluation.getGrades());
            }
        }

        return evaluations;
    }
}
