package com.quizpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizpro.dao.PracticeTestResultDAO;
import com.quizpro.dao.QuestionDAO;
import com.quizpro.model.Course;
import com.quizpro.model.PracticeTestQuestionResult;
import com.quizpro.model.PracticeTestResult;
import com.quizpro.model.Question;

@Service
public class PracticeTestService {

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private PracticeTestResultDAO resultDAO;

    @Autowired
    private QuizService quizService;

    public List<Question> getAllQuestionsForTopic(Long courseId, Long topicId) {
        long total = questionDAO.countQuestionsByCourseAndTopic(courseId, topicId);
        return questionDAO.getQuestionsWithOptionsByCourseAndTopic(courseId, topicId, 0, (int) total);
    }

    public PracticeTestResult createEmptyResult(Long studentId, String studentName,
                                                String courseName, String topicName,
                                                int totalQuestions) {
        PracticeTestResult r = new PracticeTestResult();
        r.setStudentId(studentId);
        r.setStudentName(studentName);
        r.setCourseName(courseName);
        r.setTopicName(topicName);
        r.setNoOfQuestions(totalQuestions);
        r.setMarksObtained(0);
        return resultDAO.save(r);
    }

    public void addQuestionResult(PracticeTestResult result, Question q, String yourAnswer) {
        PracticeTestQuestionResult qr = new PracticeTestQuestionResult();
        qr.setPracticeTestResult(result);
        qr.setQuestionId(q.getQuestionId());
        qr.setQuestion(q.getQuestion());
        qr.setCorrectAnswer(q.getCorrectAnswer());
        qr.setYourAnswer(yourAnswer);
        qr.setStatus(q.getCorrectAnswer() != null && q.getCorrectAnswer().equalsIgnoreCase(yourAnswer)
                ? "CORRECT" : "INCORRECT");
        result.getQuestionWiseResult().add(qr);

        int current = Optional.ofNullable(result.getMarksObtained()).orElse(0);
        if ("CORRECT".equals(qr.getStatus())) {
            result.setMarksObtained(current + 1);
        }
    }

    public PracticeTestResult finalizeAndSave(PracticeTestResult result) {
        return resultDAO.save(result);
    }

    public List<Course> getAllCourses() {
        return quizService.getAllCourses(); // reuse existing service method. :contentReference[oaicite:5]{index=5}
    }
}