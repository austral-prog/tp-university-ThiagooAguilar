package com.university.universitymanagement.universitysystem.evaluationcriteriasystem;

import com.university.evaluation.evaluationtypes.evaluationcriteria.FinalResults;
import com.university.evaluation.evaluationtypes.evaluationcriteria.StatusResults;

import java.util.Collections;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortName{


    public static void sortResults(List<StatusResults> resultsList) {

        Collections.sort(resultsList, new Comparator<StatusResults>() {
            public int compare(StatusResults o1, StatusResults o2) {
                int studentComparison = o1.getStudentName().compareTo(o2.getStudentName());
                if (studentComparison != 0) {
                    return studentComparison;
                }
                return o1.getSubjectName().compareTo(o2.getSubjectName());
            }
        });
    }
}

