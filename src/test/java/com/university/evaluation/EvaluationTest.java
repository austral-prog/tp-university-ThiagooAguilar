package com.university.evaluation;

import com.university.evaluation.evaluationtypes.Evaluation;
import com.university.evaluation.evaluationtypes.EvaluationCriteria;
import com.university.evaluation.evaluationtypes.WrittenExam;
import com.university.universitymanagement.universitysystem.evaluationsystem.data.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.university.universitymanagement.universitysystem.evaluationsystem.data.SaveData.saveData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluationTest {

    @Test
    public void evaluationmethod(){
        Evaluation exam = new WrittenExam("Thiago","WRITTEN_EXAM","Maths","Primer Parcial");
        exam.results(9);
        exam.results(8);
        exam.results(7);
        assertEquals(3,exam.getGrades().size(),"Wrong number of results");
        assertEquals(8,exam.calculateGradesResult(exam.getGrades()), "The average is not being calculated correctly");
        assertEquals("Thiago", exam.getStudentName());
        assertEquals("WRITTEN_EXAM", exam.getEvaluationType());
        assertEquals("Maths", exam.getSubject());
        assertEquals("Primer Parcial", exam.getEvaluationName());
    }

    @Test
    public void Datamethods(){
        List<Data> infos = new ArrayList<>();
        Data info= new Data("Thiago","Maths","WRITTEN_EXAM","Primer Parcial", "Ej 1", 9);
        Data info2=new Data("Thiago","Maths","WRITTEN_EXAM","Primer Parcial", "Ej 2", 8);
        Data info3= new Data("Marta","Physics","ORAL_EXAM","Primer Parcial","Ej1",9);
        infos.add(info);
        infos.add(info2);
        infos.add(info3);
        List<Evaluation> evaluations = saveData(infos);
        Evaluation exam= evaluations.get(0);
        assertEquals("Thiago", exam.getStudentName());
        assertEquals("WRITTEN_EXAM", exam.getEvaluationType());
        assertEquals("Maths", exam.getSubject());
        assertEquals("Primer Parcial", exam.getEvaluationName());
        assertEquals("[9, 8]", exam.getGrades().toString());
        assertEquals(8.5, exam.calculateGradesResult(exam.getGrades()), "The average is not being calculated correctly");
    }

    @Test
    public void Evaluationcriteria(){
        List<String> names= new ArrayList<>();
        names.add("TP1");
        names.add("TP2");
        names.add("TP3");
        EvaluationCriteria exam= new EvaluationCriteria("Maths","AVERAGE_ABOVE_VALUE",6.0, names);
        assertEquals("Maths", exam.getSubjectName());
        assertEquals("AVERAGE_ABOVE_VALUE",exam.getCriteriaType());
        assertEquals(6.0, exam.getCriteriaValue());
        assertEquals(names,exam.getEvaluationNames());
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
}

