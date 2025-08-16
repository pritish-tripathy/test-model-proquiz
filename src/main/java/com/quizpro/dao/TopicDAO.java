package com.quizpro.dao;

import com.quizpro.model.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TopicDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<Topic> getAllTopics(){
        String jpaQl = "SELECT t FROM Topic t";
        List<Topic> topicList = entityManager.createQuery(jpaQl, Topic.class).getResultList();
        return topicList != null ? topicList : new ArrayList<>();
    }

    @Transactional
    public List<Topic> getTopicsByCourseId(Long courseId) {
        String jpaQl = "SELECT t FROM Topic t WHERE t.course.courseId = :cid";
        List<Topic> topicsByCourseIdList = entityManager.createQuery(jpaQl, Topic.class)
                .setParameter("cid", courseId)
                .getResultList();
        return topicsByCourseIdList != null ? topicsByCourseIdList : new ArrayList<>();
    }

    @Transactional
    public Topic findById(Long id) {
        return entityManager.find(Topic.class, id);
    }
}
