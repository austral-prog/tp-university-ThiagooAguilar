package com.university.statusresults;

import com.university.evaluation.evaluationtypes.evaluationcriteria.StatusResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusResultsTest {

    private StatusResults statusResults;
    @BeforeEach
    public void setUp() {
        List<String > test= new ArrayList<>();
        test.add("A");
        statusResults = new StatusResults("Mathematics", test, "John Doe", 85.5, "Passed");
    }

    @Test
    public void testConstructor() {
        List<String > test= new ArrayList<>();
        test.add("A");
        assertEquals("Mathematics", statusResults.getSubjectName());
        assertEquals(test, statusResults.getEvaluationName());
        assertEquals("John Doe", statusResults.getStudentName());
        assertEquals(85.5, statusResults.getGrade());
        assertEquals("Passed", statusResults.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        statusResults.setSubjectName("Physics");
        List<String> test= new ArrayList<>();
        test.add("Final Exam");
        statusResults.setEvaluationName(test);
        statusResults.setStudentName("Jane Doe");
        statusResults.setGrade(92.0);
        statusResults.setStatus("Passed");

        assertEquals("Physics", statusResults.getSubjectName());
        assertEquals(test, statusResults.getEvaluationName());
        assertEquals("Jane Doe", statusResults.getStudentName());
        assertEquals(92.0, statusResults.getGrade());
        assertEquals("Passed", statusResults.getStatus());
    }

}