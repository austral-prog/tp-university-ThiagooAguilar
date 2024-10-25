package com.university.evaluation;

import com.university.evaluation.evaluationtypes.Evaluation;
import com.university.evaluation.evaluationtypes.OralExam;

import java.util.ArrayList;
import java.util.List;


public class EvaluationManagement <T extends Evaluation> {
    List<T> writtenExams;
    List<T> finalPracticalWorks;
    List<T> OralExams;
    List<T> practicalWorks;

    public EvaluationManagement() {
        writtenExams = new ArrayList<>();
        finalPracticalWorks = new ArrayList<>();
        OralExams = new ArrayList<>();
        practicalWorks = new ArrayList<>();
    }
    public void addEvaluation(T evaluation) {
        String name = evaluation.getEvaluationType();

        if (name.equals("WRITTEN_EXAM")) {
            writtenExams.add(evaluation);
        } else if (name.equals("ORAL_EXAM")) {
            OralExams.add(evaluation);
        } else if (name.equals("FINAL_PRACTICAL_WORK")) {
            finalPracticalWorks.add(evaluation);
        }
        else{practicalWorks.add(evaluation);}
        }

    public List<T> getwrittenExams() {
        return writtenExams;
    }
    public List<T> getFinalPracticalWork() {
        return finalPracticalWorks;
    }
    public List<T> getOralExam() {
        return OralExams;
    }
    public List<T> getPracticalWork() {
        return practicalWorks;
    }

}
