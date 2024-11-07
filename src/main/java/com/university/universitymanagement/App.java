package com.university.universitymanagement;
import com.university.CRUDRepositories.*;
import com.university.CRUDRepository;
import com.university.evaluation.evaluationtypes.Evaluation;
import com.university.evaluation.evaluationtypes.evaluationcriteria.EvaluationCriteria;
import com.university.evaluation.evaluationtypes.evaluationcriteria.FinalResults;
import com.university.evaluation.evaluationtypes.evaluationcriteria.StatusResults;
import com.university.student.Student;
import com.university.course.Course;
import com.university.universitymanagement.universitysystem.evaluationsystem.data.Data;


import java.io.IOException;
import java.util.*;

import static com.university.universitymanagement.universitysystem.Universitysystem.*;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.Csvwriter.writeToCsv2;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.EvaluationUtils.*;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.Groupofstudents.groupResultsByEvaluationCriteria;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.Groupofstudents.groupResultsByStudent;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.ReadArchive2.readArchive2;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.ReadArchive2.readArchive3;
import static com.university.universitymanagement.universitysystem.evaluationcriteriasystem.SortName.sortResults;
import static com.university.universitymanagement.universitysystem.evaluationsystem.data.OrderData.sortEvaluations;
import static com.university.universitymanagement.universitysystem.evaluationsystem.data.ReadArchive.readArchive;
import static com.university.universitymanagement.universitysystem.evaluationsystem.data.SaveData.saveData;
import static com.university.universitymanagement.universitysystem.evaluationsystem.data.WriteData.writeToCsv;


public class App {
    public static void contarCursos(String inputCsv, String outputCsv) {
        Map<String, Student> estudiantesMap = new HashMap<>();
        Map<String, Course> cursosMap = new HashMap<>();

        eliminarArchivoSiExiste(outputCsv);

        leerArchivoCSV(inputCsv, estudiantesMap, cursosMap);

        List<Student> estudiantesOrdenados = new ArrayList<>(estudiantesMap.values());
        estudiantesOrdenados.sort(Comparator.comparing(Student::getName));

        escribirArchivoCSV(outputCsv, estudiantesOrdenados);
    }






    public static void main(String[] args) throws IOException {
        String inputCsv = "src/main/resources/input.csv";
        String outputCsv = "src/main/resources/solution.csv";
        String inputCsv2 = "src/main/resources/input_2.csv";
        String outputCsv2 = "src/main/resources/solution2.csv";
        String inputCsv3 = "src/main/resources/input_3.csv";
        String outputCsv3 = "src/main/resources/solution3.csv";

        contarCursos(inputCsv, outputCsv);
        List<Data> data= readArchive(inputCsv2);
        List<Evaluation> evaluations =  saveData(data);
        sortEvaluations(evaluations);
        writeToCsv(outputCsv2, evaluations);
        List<EvaluationCriteria> criterias=readArchive2(inputCsv3);
        List<FinalResults> finalResults = readArchive3(outputCsv2);
        Map<String, List<FinalResults>> finalresultsbystudents=groupResultsByStudent(finalResults);
        Map<String, Map<EvaluationCriteria, List<FinalResults>>> finalresultsandcriterias=groupResultsByEvaluationCriteria(finalresultsbystudents,criterias);
        List<StatusResults> listofstatusresults= evaluateResults(finalresultsandcriterias);
        sortResults(listofstatusresults);
        writeToCsv2(outputCsv3, listofstatusresults);

        CourseRepository courseRepository = new CourseRepository();
        StudentRepository studentRepository = new StudentRepository();
        EvaluationRepository evaluationRepository = new EvaluationRepository();
        EvaluationCriteriaRepository evaluationCriteriaRepository = new EvaluationCriteriaRepository();


        CRUDRepository<?>[] repositories = {
                courseRepository,
                studentRepository,
                evaluationRepository,
                evaluationCriteriaRepository
        };

        UniversityCLI cli = new UniversityCLI();
        cli.runCLI(repositories);
    }
}





