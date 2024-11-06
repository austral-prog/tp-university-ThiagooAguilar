package com.university.evaluation;

import com.university.evaluation.evaluationtypes.Evaluation;
import com.university.evaluation.evaluationtypes.evaluationcriteria.EvaluationCriteria;
import com.university.evaluation.evaluationtypes.WrittenExam;
import com.university.evaluation.evaluationtypes.evaluationcriteria.FinalResults;
import com.university.universitymanagement.universitysystem.evaluationsystem.data.Data;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.Groupofstudents.groupResultsByEvaluationCriteria;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.Groupofstudents.groupResultsByStudent;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.ReadArchive2.readArchive2;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.ReadArchive2.readArchive3;
import static com.university.universitymanagement.universitysystem.evaluationsystem.data.SaveData.saveData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EvaluationTest {

    @Test
    public void evaluationmethod() {
        Evaluation exam = new WrittenExam("Thiago", "WRITTEN_EXAM", "Maths", "Primer Parcial");
        exam.results(9);
        exam.results(8);
        exam.results(7);
        assertEquals(3, exam.getGrades().size(), "Wrong number of results");
        assertEquals(8, exam.calculateGradesResult(exam.getGrades()), "The average is not being calculated correctly");
        assertEquals("Thiago", exam.getStudentName());
        assertEquals("WRITTEN_EXAM", exam.getEvaluationType());
        assertEquals("Maths", exam.getSubject());
        assertEquals("Primer Parcial", exam.getEvaluationName());
    }

    @Test
    public void Datamethods() {
        List<Data> infos = new ArrayList<>();
        Data info = new Data("Thiago", "Maths", "WRITTEN_EXAM", "Primer Parcial", "Ej 1", 9);
        Data info2 = new Data("Thiago", "Maths", "WRITTEN_EXAM", "Primer Parcial", "Ej 2", 8);
        Data info3 = new Data("Marta", "Physics", "ORAL_EXAM", "Primer Parcial", "Ej1", 9);
        infos.add(info);
        infos.add(info2);
        infos.add(info3);
        List<Evaluation> evaluations = saveData(infos);
        Evaluation exam = evaluations.get(0);
        assertEquals("Thiago", exam.getStudentName());
        assertEquals("WRITTEN_EXAM", exam.getEvaluationType());
        assertEquals("Maths", exam.getSubject());
        assertEquals("Primer Parcial", exam.getEvaluationName());
        assertEquals("[9, 8]", exam.getGrades().toString());
        assertEquals(8.5, exam.calculateGradesResult(exam.getGrades()), "The average is not being calculated correctly");
        info.setSubject("Physics");
        info.setStudentName("Roberto");
        info.setEvaluationType("ORAL_EXAM");
        info.setEvaluationName("TP1");
        info.setExercise("EJ 9");
        info.setGrade(0);
        assertEquals(0, info.getGrade());
        assertEquals("Roberto",info.getStudentName());
        assertEquals("Physics", info.getSubject());
        assertEquals("ORAL_EXAM", info.getEvaluationType());
        assertEquals("TP1", info.getEvaluationName());
        assertEquals("EJ 9", info.getExercise());

    }

    @Test
    public void Evaluationcriteria() {
        List<String> names = new ArrayList<>();
        names.add("TP1");
        names.add("TP2");
        names.add("TP3");
        EvaluationCriteria exam = new EvaluationCriteria("Maths", "AVERAGE_ABOVE_VALUE", 6.0, names);
        assertEquals("Maths", exam.getSubjectName());
        assertEquals("AVERAGE_ABOVE_VALUE", exam.getCriteriaType());
        assertEquals(6.0, exam.getCriteriaValue());
        assertEquals(names, exam.getEvaluationNames());
        exam.setCriteriaValue(7.0);
        assertEquals(7.0, exam.getCriteriaValue());
        names.clear();
        names.add("Examen Parcial");
        exam.setEvaluationNames(names);
        assertEquals(names, exam.getEvaluationNames());
        exam.setSubjectName("Chemistry");
        assertEquals("Chemistry", exam.getSubjectName());
        exam.setCriteriaType("MAX_ABOVE_VALUE");
        assertEquals("MAX_ABOVE_VALUE", exam.getCriteriaType());
    }

    @Test
    public void Evaluationcriteriareader() {
        String inputCsv3 = "src/main/resources/input_3.csv";
        List<EvaluationCriteria> considerations = readArchive2(inputCsv3);
        EvaluationCriteria consideration = considerations.get(0);
        assertEquals("Geography", consideration.getSubjectName());
        assertEquals("AVERAGE_ABOVE_VALUE", consideration.getCriteriaType());
        assertEquals(6.0, consideration.getCriteriaValue());
        List<String> expectedEvaluationNames = Arrays.asList("TP1", "TP4", "TP2", "TP3");
        assertEquals(expectedEvaluationNames, consideration.getEvaluationNames());
        assertEquals(33, considerations.size());
    }

    @Test
    public void Finalresults() {
        String test = "src/main/resources/filetest.csv";
        List<FinalResults> finalresults = readArchive3(test);
        FinalResults finalresult = finalresults.get(0);
        assertEquals("Art", finalresult.getSubjectName());
        assertEquals("TP Final", finalresult.getEvaluationName());
        assertEquals("Alice Azure", finalresult.getStudentName());
        assertEquals(2.0, finalresult.getGrade());
    }

    @Test
    public void testGroupResultsByStudent() {
        List<FinalResults> finalResultsList = Arrays.asList(
                new FinalResults("Math", "TP1", "Nick White", 7.0),
                new FinalResults("History", "TP2", "Nick White", 6.0),
                new FinalResults("Math", "TP1", "Olivia Azure", 8.0),
                new FinalResults("History", "TP3", "Nick White", 5.0),
                new FinalResults("Math", "TP2", "Olivia Azure", 9.0));

        List<EvaluationCriteria>List = Arrays.asList(
                new EvaluationCriteria("Math", "Homework", 10.0, Arrays.asList("TP1", "TP2")),
                new EvaluationCriteria("History", "Exam", 15.0, Arrays.asList("TP2", "TP3"))
        );


        Map<String, List<FinalResults>> groupedResults = groupResultsByStudent(finalResultsList);
        assertTrue(groupedResults.containsKey("Nick White"));
        assertTrue(groupedResults.containsKey("Olivia Azure"));
        List<FinalResults> nickWhiteResults = groupedResults.get("Nick White");
        assertEquals(3, nickWhiteResults.size());
        assertTrue(nickWhiteResults.stream().anyMatch(r -> r.getEvaluationName().equals("TP1")));
        assertTrue(nickWhiteResults.stream().anyMatch(r -> r.getEvaluationName().equals("TP2")));
        assertTrue(nickWhiteResults.stream().anyMatch(r -> r.getEvaluationName().equals("TP3")));
        List<FinalResults> oliviaAzureResults = groupedResults.get("Olivia Azure");
        assertEquals(2, oliviaAzureResults.size());
        assertTrue(oliviaAzureResults.stream().anyMatch(r -> r.getEvaluationName().equals("TP1")));
        assertTrue(oliviaAzureResults.stream().anyMatch(r -> r.getEvaluationName().equals("TP2")));
        assertEquals("Nick White", nickWhiteResults.get(0).getStudentName());
        assertEquals("Olivia Azure", oliviaAzureResults.get(0).getStudentName());
    }
}




