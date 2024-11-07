package com.university.CRUDRepositories;

import com.university.CRUDRepository;
import com.university.evaluation.evaluationtypes.Evaluation;

import java.util.HashMap;
import java.util.Map;

public class EvaluationRepository<T extends Evaluation> implements CRUDRepository<T> {

    private Map<Integer, T> evaluations = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(T evaluation) {
        evaluation.setId(currentId); // Asignamos un ID único
        evaluations.put(currentId, evaluation); // Agregamos al mapa
        currentId++; // Incrementamos el ID para el próximo
        System.out.println("Evaluación creada con ID: " + evaluation.getId());
    }

    @Override
    public T read(int id) {
        return evaluations.get(id); // Retornamos la evaluación según el ID
    }

    @Override
    public void update(int id, T updatedEvaluation) {
        if (evaluations.containsKey(id)) {
            updatedEvaluation.setId(id); // Aseguramos que mantenga el mismo ID
            evaluations.put(id, updatedEvaluation); // Actualizamos el registro
            System.out.println("Evaluación actualizada con ID: " + id);
        } else {
            System.out.println("Evaluación no encontrada.");
        }
    }

    @Override
    public void delete(int id) {
        if (evaluations.remove(id) != null) {
            System.out.println("Evaluación eliminada con ID: " + id);
        } else {
            System.out.println("Evaluación no encontrada.");
        }
    }

    @Override
    public String getIdentifier() {
        return "Evaluation";
    }

    @Override
    public Class<T> getEntityClass() {
        return (Class<T>) Evaluation.class;
    }
}