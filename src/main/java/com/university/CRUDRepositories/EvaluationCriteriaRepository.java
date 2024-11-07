package com.university.CRUDRepositories;

import com.university.CRUDRepository;
import com.university.evaluation.evaluationtypes.evaluationcriteria.EvaluationCriteria;

import java.util.HashMap;
import java.util.Map;

public class EvaluationCriteriaRepository implements CRUDRepository<EvaluationCriteria> {
    private Map<Integer,EvaluationCriteria> criterias = new HashMap<>();
    private int currentdId = 1;

    @Override
    public void create(EvaluationCriteria evaluationcriteria) {
        evaluationcriteria.setId(currentdId);
        criterias.put(evaluationcriteria.getId(), evaluationcriteria);
        currentdId++;
        System.out.println("Evaluation Criteria created");
    }

    @Override
    public EvaluationCriteria read(int id) {
        return criterias.get(id);
    }

    @Override
    public void update(int id, EvaluationCriteria entity) {
        if (criterias.containsKey(id)) {
            entity.setId(id);
            criterias.put(id, entity);
            System.out.println("Curso actualizado con ID: " + id);
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    @Override
    public void delete(int id) {
        if(criterias.containsKey(id)) {
            criterias.remove(id);
            System.out.println("Criterio eliminado con ID: " + id);
        }else{
            System.out.println("Criterio no encontrado.");
        }
    }

    @Override
    public String getIdentifier() {
        return "Evaluation Criteria";
    }

    @Override
    public Class<EvaluationCriteria> getEntityClass() {
        return EvaluationCriteria.class;
    }
}
