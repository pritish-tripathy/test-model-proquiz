package com.quizpro.dao;

import com.quizpro.model.Course;
import com.quizpro.model.Question;
import com.quizpro.model.QuestionOption;
import com.quizpro.model.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Question saveQuestion(Question question) {
        entityManager.persist(question);
        return question;
    }

    @Transactional
    public Question findById(Long id) {
        return entityManager.find(Question.class, id);
    }

    @Transactional
    public List<Question> getQuestionsByCourseAndTopic(Long courseId, Long topicId, int offset, int limit) {
        String jpaQl = "SELECT q FROM Question q WHERE q.course.courseId = :cid AND q.topic.topicId = :tid ORDER BY q.questionId";
        return entityManager.createQuery(jpaQl, Question.class)
                .setParameter("cid", courseId)
                .setParameter("tid", topicId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Transactional
    public long countQuestionsByCourseAndTopic(Long courseId, Long topicId) {
        String jpaQl = "SELECT COUNT(q) FROM Question q WHERE q.course.courseId = :cid AND q.topic.topicId = :tid";
        return entityManager.createQuery(jpaQl, Long.class)
                .setParameter("cid", courseId)
                .setParameter("tid", topicId)
                .getSingleResult();
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        Question q = entityManager.find(Question.class, questionId);
        if (q != null)
            entityManager.remove(q);
    }

    @Transactional
    public void updateQuestion(Question question) {
        entityManager.merge(question);
    }

    @Transactional
    public void delete(Question question) {
        entityManager.remove(entityManager.contains(question) ? question : entityManager.merge(question));
    }

    @Transactional
    public List<Topic> getTopicsByCourseId(Long courseId) {
        String jpql = "FROM Topic t WHERE t.course.courseId = :courseId";
        return entityManager.createQuery(jpql, Topic.class)
                .setParameter("courseId", courseId)
                .getResultList();
    }

    @Transactional
    public Long getQuestionCountByTopicId(Long topicId) {
        String jpql = "SELECT COUNT(q) FROM Question q WHERE q.topic.topicId = :topicId";
        return entityManager.createQuery(jpql, Long.class)
                .setParameter("topicId", topicId)
                .getSingleResult();
    }

    @Transactional
    public void saveCSVQuestion(Question question) {
        entityManager.persist(question);  // Insert the Question into DB
    }

    @Transactional
    public Course findOrCreateCourse(String courseName) {
        try {
            String jpql = "SELECT c FROM Course c WHERE c.courseName = :name";
            return entityManager.createQuery(jpql, Course.class)
                    .setParameter("name", courseName)
                    .getSingleResult();
        } catch (NoResultException e) {
            Course newCourse = new Course();
            newCourse.setCourseName(courseName);
            entityManager.persist(newCourse);
            return newCourse;
        }
    }

    @Transactional
    public Topic findOrCreateTopic(Course course, String topicName) {
        try {
            String jpql = "SELECT t FROM Topic t WHERE t.topicName = :name AND t.course = :course";
            return entityManager.createQuery(jpql, Topic.class)
                    .setParameter("name", topicName)
                    .setParameter("course", course)
                    .getSingleResult();
        } catch (NoResultException e) {
            Topic topic = new Topic();
            topic.setTopicName(topicName);
            topic.setCourse(course);
            entityManager.persist(topic);
            return topic;
        }
    }

    @Transactional
    public void saveQuestionWithOptions(Question question, List<String> options) {
        entityManager.persist(question);
        for (String data : options) {
            QuestionOption opt = new QuestionOption();
            opt.setQuestion(question);
            opt.setOptionData(data);
            entityManager.persist(opt);
        }
    }
    
    @Transactional
    public List<Question> getQuestionsWithOptionsByCourseAndTopic(Long courseId, Long topicId, int offset, int limit) {
        String jpql = "SELECT DISTINCT q FROM Question q " +
                      "LEFT JOIN FETCH q.options " +
                      "WHERE q.course.courseId = :cid AND q.topic.topicId = :tid " +
                      "ORDER BY q.questionId";
        return entityManager.createQuery(jpql, Question.class)
                .setParameter("cid", courseId)
                .setParameter("tid", topicId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
