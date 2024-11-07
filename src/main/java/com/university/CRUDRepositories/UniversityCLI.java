package com.university.CRUDRepositories;

import com.university.CLI;
import com.university.CRUDRepository;
import com.university.Entity;
import com.university.course.Course;
import com.university.evaluation.evaluationtypes.*;
import com.university.evaluation.evaluationtypes.evaluationcriteria.EvaluationCriteria;
import com.university.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityCLI implements CLI {
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Selecciona una opción:");
                System.out.println("1. Crear entidad");
                System.out.println("2. Leer entidad");
                System.out.println("3. Actualizar entidad");
                System.out.println("4. Eliminar entidad");
                System.out.println("5. Salir");

                int option = Integer.parseInt(scanner.nextLine().trim());

                if (option == 5) {
                    System.out.println("Saliendo...");
                    break;
                }

                if (option < 1 || option > 5) {
                    System.out.println("Opción inválida, por favor ingrese un número entre 1 y 5.");
                    continue;
                }

                System.out.println("Selecciona el tipo de entidad:");
                for (int i = 0; i < crudInterfaces.length; i++) {
                    System.out.println((i + 1) + ". " + crudInterfaces[i].getIdentifier());
                }

                int entityTypeIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

                if (entityTypeIndex < 0 || entityTypeIndex >= crudInterfaces.length) {
                    System.out.println("Tipo de entidad inválido, inténtalo de nuevo.");
                    continue;
                }

                CRUDRepository<?> selectedRepository = crudInterfaces[entityTypeIndex];

                switch (option) {
                    case 1 -> createEntity(selectedRepository, scanner);
                    case 2 -> readEntity(selectedRepository, scanner);
                    case 3 -> updateEntity(selectedRepository, scanner);
                    case 4 -> deleteEntity(selectedRepository, scanner);
                    default -> System.out.println("Opción inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }


    private <T extends Entity> void readEntity(CRUDRepository<T> repository, Scanner scanner) {
        try {
            System.out.print("Ingresa el ID de la entidad que deseas leer: ");
            int id = scanner.nextInt();
            T entity = repository.read(id);
            if (entity != null) {
                System.out.println("Entidad encontrada: " + entity.toString());
            } else {
                System.out.println("Entidad no encontrada.");
            }
        }catch(NumberFormatException e) {
            System.out.println("El id debe ser un número.Por favor ingrese un dato correcto");
        }catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }

    private <T extends Entity> void deleteEntity(CRUDRepository<T> repository, Scanner scanner) {
        try {
            System.out.print("Ingresa el ID de la entidad que deseas eliminar: ");
            int id = scanner.nextInt();
            repository.delete(id);
        }catch(NumberFormatException e) {
            System.out.println("El id debe ser un número.Por favor ingrese un dato correcto");
        }catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }
    private <T extends Entity> void createEntity(CRUDRepository<T> repository, Scanner scanner) {
        T entity = null;
        try {
            if (repository instanceof EvaluationCriteriaRepository) {
                System.out.print("Ingrese el nombre de la materia: ");
                String subjectName = scanner.nextLine();

                System.out.print("Ingrese el tipo de criterio : ");
                String criteriaType = scanner.nextLine();

                System.out.print("Ingrese el valor del criterio: ");
                double criteriaValue = Double.parseDouble(scanner.nextLine());

                System.out.print("Ingrese la cantidad de nombres de evaluaciones que desea agregar: ");
                int evaluationCount = Integer.parseInt(scanner.nextLine());

                List<String> evaluationNames = new ArrayList<>();
                for (int i = 0; i < evaluationCount; i++) {
                    System.out.print("Ingrese el nombre de la evaluación " + (i + 1) + ": ");
                    evaluationNames.add(scanner.nextLine());
                }

                EvaluationCriteria criteria = new EvaluationCriteria(subjectName, criteriaType, criteriaValue, evaluationNames);
                entity = (T) criteria;
            }


        if (repository instanceof CourseRepository) {

            System.out.print("Ingrese el nombre del curso: ");
            String subject = scanner.nextLine();

            System.out.print("Ingrese el aula: ");
            int classroom = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el profesor: ");
            String teacher = scanner.nextLine();

            Course course = new Course(classroom, subject, teacher);
            entity = (T) course;

        } else if (repository instanceof StudentRepository) {

            System.out.print("Ingrese el nombre del estudiante: ");
            String studentName = scanner.nextLine();

            System.out.print("Ingrese la matrícula del estudiante: ");
            String studentId = scanner.nextLine();

            Student student = new Student(studentName, studentId);
            entity = (T) student;

        } else if (repository instanceof EvaluationRepository) {

            System.out.println("Seleccione el tipo de evaluación:");
            System.out.println("1. Oral Exam");
            System.out.println("2. Written Exam");
            System.out.println("3. Practical Work");
            System.out.println("4. Final Practical Work");
            int choice = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el nombre del estudiante: ");
            String studentName = scanner.nextLine();

            System.out.print("Ingrese la materia: ");
            String subject = scanner.nextLine();

            System.out.print("Ingrese el nombre de la evaluación: ");
            String evaluationName = scanner.nextLine();


            switch (choice) {
                case 1 -> entity = (T) new OralExam(studentName, "ORAL_EXAM", subject, evaluationName);
                case 2 -> entity = (T) new WrittenExam(studentName, "WRITTEN_EXAM", subject, evaluationName);
                case 3 -> entity = (T) new PracticalWork(studentName, "PRACTICAL_WORK", subject, evaluationName);
                case 4 ->
                        entity = (T) new FinalPracticalWork(studentName, "FINAL_PRACTICAL_WORK", subject, evaluationName);
                default -> System.out.println("Opción inválida.");
            }
        }

        if (entity != null) {
            repository.create(entity);
            System.out.println("Entidad creada exitosamente.");
        } else {
            System.out.println("No se pudo crear la entidad.");
        }
    } catch(NumberFormatException e){
            System.out.println("Error: Se esperaba un número.");
        }
        catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }
    private <T extends Entity> void updateEntity(CRUDRepository<T> repository, Scanner scanner) {
        System.out.print("Ingrese el ID de la entidad que desea actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        T existingEntity = repository.read(id);

        try {

            if (existingEntity == null) {
                System.out.println("Entidad con ID " + id + " no encontrada.");
                return;
            }

            T updatedEntity = null;

            if (repository instanceof EvaluationCriteriaRepository) {
                System.out.print("Ingrese el nuevo nombre de la materia (actual: " + ((EvaluationCriteria) existingEntity).getSubjectName() + "): ");
                String subjectName = scanner.nextLine();

                System.out.print("Ingrese el nuevo tipo de criterio (actual: " + ((EvaluationCriteria) existingEntity).getCriteriaType() + "): ");
                String criteriaType = scanner.nextLine();
                System.out.print("Ingrese el nuevo valor del criterio (actual: " + ((EvaluationCriteria) existingEntity).getCriteriaValue() + "): ");
                double criteriaValue = Double.parseDouble(scanner.nextLine());

                System.out.print("Ingrese la cantidad de nombres de evaluaciones que desea actualizar: ");
                int evaluationCount = Integer.parseInt(scanner.nextLine());

                List<String> evaluationNames = new ArrayList<>();
                for (int i = 0; i < evaluationCount; i++) {
                    System.out.print("Ingrese el nombre de la evaluación " + (i + 1) + ": ");
                    evaluationNames.add(scanner.nextLine());
                }

                EvaluationCriteria updatedCriteria = new EvaluationCriteria(subjectName, criteriaType, criteriaValue, evaluationNames);
                updatedCriteria.setId(id); // Mantener el ID de la entidad existente
                updatedEntity = (T) updatedCriteria;
            }

            if (repository instanceof CourseRepository) {
                System.out.print("Ingrese el nuevo nombre del curso (actual: " + ((Course) existingEntity).getSubject() + "): ");
                String subject = scanner.nextLine();

                System.out.print("Ingrese el nuevo aula (actual: " + ((Course) existingEntity).getClassroom() + "): ");
                int classroom = Integer.parseInt(scanner.nextLine());
                System.out.print("Ingrese el nuevo profesor (actual: " + ((Course) existingEntity).getTeacher() + "): ");
                String teacher = scanner.nextLine();

                Course updatedCourse = new Course(classroom, subject, teacher);
                updatedCourse.setId(id);
                updatedEntity = (T) updatedCourse;

            } else if (repository instanceof StudentRepository) {
                System.out.print("Ingrese el nuevo nombre del estudiante (actual: " + ((Student) existingEntity).getName() + "): ");
                String studentName = scanner.nextLine();

                System.out.print("Ingrese la nueva matrícula del estudiante (actual: " + ((Student) existingEntity).getId() + "): ");
                String studentId = scanner.nextLine();

                Student updatedStudent = new Student(studentName, studentId);
                updatedStudent.setId(id);
                updatedEntity = (T) updatedStudent;

            } else if (repository instanceof EvaluationRepository) {

                System.out.println("Seleccione el tipo de evaluación:");
                System.out.println("1. Oral Exam");
                System.out.println("2. Written Exam");
                System.out.println("3. Practical Work");
                System.out.println("4. Final Practical Work");
                int choice = Integer.parseInt(scanner.nextLine());

                System.out.print("Ingrese el nuevo nombre del estudiante (actual: " + ((Evaluation) existingEntity).getStudentName() + "): ");
                String studentName = scanner.nextLine();

                System.out.print("Ingrese la nueva materia (actual: " + ((Evaluation) existingEntity).getSubject() + "): ");
                String subject = scanner.nextLine();

                System.out.print("Ingrese el nuevo nombre de la evaluación (actual: " + ((Evaluation) existingEntity).getEvaluationName() + "): ");
                String evaluationName = scanner.nextLine();

                switch (choice) {
                    case 1 -> updatedEntity = (T) new OralExam(studentName, "ORAL_EXAM", subject, evaluationName);
                    case 2 -> updatedEntity = (T) new WrittenExam(studentName, "WRITTEN_EXAM", subject, evaluationName);
                    case 3 ->
                            updatedEntity = (T) new PracticalWork(studentName, "PRACTICAL_WORK", subject, evaluationName);
                    case 4 ->
                            updatedEntity = (T) new FinalPracticalWork(studentName, "FINAL_PRACTICAL_WORK", subject, evaluationName);
                    default -> System.out.println("Opción inválida.");
                }

                if (updatedEntity != null) {
                    ((Evaluation) updatedEntity).setId(id);
                }
            }

            if (updatedEntity != null) {
                repository.update(id, updatedEntity);
                System.out.println("Entidad actualizada exitosamente.");
            } else {
                System.out.println("No se pudo actualizar la entidad.");
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado" + e.getMessage());
        }
    }


}

