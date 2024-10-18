package com.university.universitymanager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Apptest2 {

    private static final String INPUT_CSV = "src/main/resources/input_test.csv";
    private static final String OUTPUT_CSV = "src/main/resources/solution_test.csv";

    @BeforeEach
    public void setUp() throws IOException {
        // Crear un archivo de entrada de prueba
        try (PrintWriter writer = new PrintWriter(new File(INPUT_CSV))) {
            writer.println("Class,Subject,Student_Name,Email,Professor");
            writer.println("101,Math,Alice,alice@example.com,Prof. Smith");
            writer.println("101,Math,Bob,bob@example.com,Prof. Smith");
            writer.println("102,Physics,Alice,alice@example.com,Prof. Jones");
            writer.println("103,Chemistry,Charlie,charlie@example.com,Prof. Brown");
            writer.println("101,Math,Alice,alice@example.com,Prof. Smith");
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Eliminar los archivos creados durante las pruebas
        Files.deleteIfExists(Paths.get(INPUT_CSV));
        Files.deleteIfExists(Paths.get(OUTPUT_CSV));
    }

    @Test
    public void testContarCursosGeneratesCorrectOutput() {

        App.contarCursos(INPUT_CSV, OUTPUT_CSV);

        // Verificar que el archivo de salida se ha creado
        assertTrue(Files.exists(Paths.get(OUTPUT_CSV)), "El archivo de salida no se generó.");

        // Leer el contenido del archivo de salida
        List<String> outputLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputLines.add(line);
            }
        } catch (IOException e) {
            fail("Error al leer el archivo de salida: " + e.getMessage());
        }

        // Comprobar que el contenido es el esperado
        List<String> expectedLines = Arrays.asList(
                "Student_Name,Course_Count",
                "Alice,2",
                "Bob,1",
                "Charlie,1"
        );

        assertEquals(expectedLines, outputLines, "El contenido del archivo de salida no es el esperado.");
    }

}