package com.quizpro.dao;

import com.quizpro.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Course> getAllCourses(){
        String jpaQl = "SELECT c FROM Course c";
        List<Course> courseList = entityManager.createQuery(jpaQl, Course.class).getResultList();
        return courseList != null ? courseList : new ArrayList<>();
    }

    @Transactional
    public Course findById(Long id){
        return entityManager.find(Course.class, id);
    }
}

