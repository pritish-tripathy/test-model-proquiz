package com.quizpro.service;

import com.quizpro.dao.CourseDAO;
import com.quizpro.dao.QuestionDAO;
import com.quizpro.dao.QuestionOptionDAO;
import com.quizpro.dao.TopicDAO;
import com.quizpro.model.Course;
import com.quizpro.model.Question;
import com.quizpro.model.QuestionOption;
import com.quizpro.model.Topic;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private TopicDAO topicDAO;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private QuestionOptionDAO optionDAO;

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public List<Topic> getAllTopics() {
        return topicDAO.getAllTopics();
    }

    public List<Topic> getTopicsByCourseId(Long courseId) {
        return topicDAO.getTopicsByCourseId(courseId);
    }

    public void saveQuestionWithOptions(Question question, List<String> options) {
        Question saved = questionDAO.saveQuestion(question);
        for (String optionData : options) {
            QuestionOption opt = new QuestionOption();
            opt.setOptionData(optionData);
            opt.setQuestion(saved);
            optionDAO.saveOption(opt);
        }
    }

    public Course getCourseById(Long courseId) {
        return courseDAO.findById(courseId);
    }

    public Topic getTopicById(Long topicId) {
        return topicDAO.findById(topicId);
    }

    public List<Question> getPaginatedQuestions(Long courseId, Long topicId, int page, int size) {
        int offset = (page - 1) * size;
        return questionDAO.getQuestionsByCourseAndTopic(courseId, topicId, offset, size);
    }

    public long getTotalQuestions(Long courseId, Long topicId) {
        return questionDAO.countQuestionsByCourseAndTopic(courseId, topicId);
    }

    public Question getQuestionById(Long id) {
        return questionDAO.findById(id);
    }

    public List<QuestionOption> getOptionsByQuestionId(Long qid) {
        return optionDAO.findByQuestionId(qid);
    }

    public void updateQuestion(Question question) {
        questionDAO.updateQuestion(question);
    }

    public void updateOption(Long optionId, String optionData) {
        QuestionOption opt = optionDAO.findById(optionId);
        opt.setOptionData(optionData);
        optionDAO.updateOption(opt);
    }

    public void deleteQuestion(Long id) {
        Question question = questionDAO.findById(id);
        if (question != null) {
            questionDAO.delete(question);
        }
    }

    public Long getQuestionCountByTopicId(Long topicId) {
        return questionDAO.getQuestionCountByTopicId(topicId);
    }

    public void saveQuestionsFromCSV(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                // Extract and parse values
                Long courseId = (long) row.getCell(0).getNumericCellValue();
                String courseName = row.getCell(1).getStringCellValue();
                Long topicId = (long) row.getCell(2).getNumericCellValue();
                String topicName = row.getCell(3).getStringCellValue();
                String questionText = row.getCell(4).getStringCellValue();
                String correctAnswer = row.getCell(5).getStringCellValue();
                String option1 = row.getCell(6).getStringCellValue();
                String option2 = row.getCell(7).getStringCellValue();
                String option3 = row.getCell(8).getStringCellValue();
                String option4 = row.getCell(9).getStringCellValue();

                // Fetch existing course and topic
                Course course = courseDAO.findById(courseId);
                Topic topic = topicDAO.findById(topicId);

                if (course == null || topic == null) {
                    System.out.println("Skipping row " + row.getRowNum() + " - Course or Topic not found.");
                    continue;
                }

                // Create question
                Question question = new Question();
                question.setCourse(course);
                question.setTopic(topic);
                question.setQuestion(questionText);
                question.setCorrectAnswer(correctAnswer);

                // Save question and options
                List<String> options = Arrays.asList(option1, option2, option3, option4);
                saveQuestionWithOptions(question, options);
            }

            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Error processing uploaded Excel file", e);
        }
    }
}
