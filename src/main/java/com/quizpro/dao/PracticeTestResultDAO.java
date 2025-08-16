package com.quizpro.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.quizpro.model.PracticeTestResult;

@Repository
public class PracticeTestResultDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public PracticeTestResult save(PracticeTestResult result) {
        entityManager.persist(result);
        return result;
    }

    public PracticeTestResult findById(Long id) {
        return entityManager.find(PracticeTestResult.class, id);
    }
}