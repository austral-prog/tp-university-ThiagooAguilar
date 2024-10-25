package com.university.evaluation.evaluationtypes;

import com.university.evaluation.methods.IGradeable;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

public class OralExam extends  Evaluation implements IGradeable {
        private List<Integer> grades;

        public OralExam(String studentName,String evaluationType, String subject, String evaluationName) {
            super(studentName, evaluationType, subject, evaluationName);
            this.grades = new ArrayList<>();
        }

        @Override
        public void results(Integer grade) {
            grades.add(grade);
        }

        @Override
        public List<Integer> getGrades() {
            return grades;
        }
    }

