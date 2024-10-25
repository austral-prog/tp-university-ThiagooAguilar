package com.university.evaluation;

import com.university.evaluation.EvaluationManagement;
import com.university.evaluation.evaluationtypes.PartialExam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
public class EvaluationTest {

    private PartialExam partialExam;

    @BeforeEach
    public void setUp() {
        partialExam = new PartialExam("Juan", "Matemáticas", "Primer Parcial");
    }

    @Test
    public void testGetStudentName() {
        assertEquals("Juan", partialExam.getStudentName());
    }

    @Test
    public void testGetSubject() {
        assertEquals("Matemáticas", partialExam.getSubject());
    }

    @Test
    public void testGetEvaluationName() {
        assertEquals("Primer Parcial", partialExam.getEvaluationName());
    }

    @Test
    public void testAddGrades() {
        partialExam.results(8);
        partialExam.results(7);
        List<Integer> expectedGrades = Arrays.asList(8,7);
        assertEquals(expectedGrades, partialExam.getGrades());
    }

  @Test
   public void testSetGrades() {
        List<Integer> newGrades = Arrays.asList(10);

        partialExam.setGrades(newGrades);
       assertEquals(newGrades, partialExam.getGrades());
    }

    @Test
    public void testAddEvaluation() {
        EvaluationManagement evaluationManagement = new EvaluationManagement();
        evaluationManagement.addEvaluation(partialExam);
        Integer finalExam= (evaluationManagement.getFinalExams()).size();
        Integer finalWork= (evaluationManagement.getFinalWork()).size();
        Integer partialExam=(evaluationManagement.getPartialExam()).size();
        Integer practicalWork=(evaluationManagement.getPracticalWork()).size();

        assertEquals(0, finalExam);
        assertEquals(0, finalWork);
        assertEquals(1, partialExam);
        assertEquals(0, practicalWork);
    }
}

