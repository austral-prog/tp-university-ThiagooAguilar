package com.university.evaluation.evaluationtypes;

import java.util.ArrayList;
import java.util.List;

public class EvaluationCriteria {
    private String subjectName;
    private String criteriaType;
    private double criteriaValue;
    private List<String> evaluationNames;

    public EvaluationCriteria(String subjectName, String criteriaType, double criteriaValue, List<String> evaluationNames) {
        this.subjectName = subjectName;
        this.criteriaType = criteriaType;
        this.criteriaValue = criteriaValue;
        this.evaluationNames = new ArrayList<>(evaluationNames);
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public String getCriteriaType() {
        return criteriaType;
    }
    public void setCriteriaType(String criteriaType) {
        this.criteriaType = criteriaType;
    }
    public double getCriteriaValue() {
        return criteriaValue;
    }
    public void setCriteriaValue(double criteriaValue) {
        this.criteriaValue = criteriaValue;
    }
    public List<String> getEvaluationNames() {
        return evaluationNames;
    }
    public void setEvaluationNames(List<String> evaluationNames) {
        this.evaluationNames = evaluationNames;
    }

}
