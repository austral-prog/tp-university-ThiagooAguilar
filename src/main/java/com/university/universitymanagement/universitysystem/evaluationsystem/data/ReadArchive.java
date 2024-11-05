package com.university.universitymanagement.universitysystem.evaluationsystem.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadArchive {

    public static List<Data> readArchive(String inputCsv) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsv))) {
            String linea = br.readLine();
            List<Data> information = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");

                if (columnas.length < 6) {
                    System.err.println("Línea inválida en el CSV: " + linea);
                    continue;
                }

                String studentName = columnas[0];
                String subject = columnas[1];
                String evaluationType = columnas[2];
                String evaluationName = columnas[3];
                String exercise = columnas[4];
                Integer grade = Integer.valueOf(columnas[5]);
                Data lineData = new Data(studentName, subject, evaluationType, evaluationName, exercise, grade);
                information.add(lineData);
            }
            return information;
        }
    }
}
