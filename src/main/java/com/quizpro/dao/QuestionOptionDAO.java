package com.quizpro.dao;

import com.quizpro.model.QuestionOption;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionOptionDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void saveOption(QuestionOption option) {
        entityManager.persist(option);
    }

    @Transactional
    public List<QuestionOption> findByQuestionId(Long qid) {
        String jpaQl = "SELECT o FROM QuestionOption o WHERE o.question.questionId = :qid";
        List<QuestionOption> quesOptList = entityManager.createQuery(jpaQl, QuestionOption.class)
                .setParameter("qid", qid)
                .getResultList();
        return quesOptList != null ? quesOptList : new ArrayList<>();
    }

    @Transactional
    public QuestionOption findById(Long id) {
        return entityManager.find(QuestionOption.class, id);
    }

    @Transactional
    public void updateOption(QuestionOption option) {
        entityManager.merge(option);
    }
}
