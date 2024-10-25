package com.university.evaluation.methods;

import java.util.List;

public interface IGradeable{

    public default double calculateGradesResult(List<Integer> grades) {
        double sum = 0;
        for (Integer notes : grades) {
            sum += notes;
        }

        double average = sum / grades.size();

        return average;
    }}

