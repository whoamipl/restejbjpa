package com.example.restejbjpa.service;

import com.example.restejbjpa.domain.Computer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Stateless
public class ComputerService {

    private String GPU_MODEL = "GTX1050";
    @PersistenceContext
    EntityManager em;

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Computer> computerCirteriaQuery = criteriaBuilder.createQuery(Computer.class);

    Root<Computer> computerRoot = computerCirteriaQuery.from(Computer.class);

    Predicate condition = criteriaBuilder.equal(computerRoot.get(Computer_.gpu,GPU_MODEL);
    computerCirteriaQuery.where(condition);

    TypedQuery<Computer> typedQuery = em.createQuery(computerCirteriaQuery);

    List<Computer> result = typedQuery.getResultList();
    return result;
}
