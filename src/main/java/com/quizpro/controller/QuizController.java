package com.quizpro.controller;

import com.quizpro.model.Course;
import com.quizpro.model.Question;
import com.quizpro.model.QuestionOption;
import com.quizpro.model.Topic;
import com.quizpro.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {
    private final static Logger log = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    @GetMapping("/addQuestionForm")
    public String showAddForm(Model model) {
        log.info("GET - ADD QUESTION FORM");
        List<Course> courses = quizService.getAllCourses();
        List<Topic> topics = quizService.getAllTopics();

        model.addAttribute("courses", courses);
        model.addAttribute("topics", topics);
        return "AddQuestion";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(HttpServletRequest request, Model model) {
        log.info("POST - ADD QUESTION");
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        Long topicId = Long.parseLong(request.getParameter("topicId"));
        String questionText = request.getParameter("question");
        String correctAnswer = request.getParameter("correctAnswer");

        Question question = new Question();
        question.setQuestion(questionText);
        question.setCorrectAnswer(correctAnswer);
        question.setCourse(quizService.getCourseById(courseId));
        question.setTopic(quizService.getTopicById(topicId));

        List<String> optionDataList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            optionDataList.add(request.getParameter("option" + i));
        }

        quizService.saveQuestionWithOptions(question, optionDataList);

        return "redirect:/addQuestionForm?success=true";
    }

    @GetMapping("/goHomepage")
    public String goToHomepage(){
        log.info("GET - GO HOMEPAGE");
        return "HomePage";
    }

    @GetMapping("/goLoginpage")
    public String goToLoginpage(){
        log.info("GET - GO LOGIN PAGE");
        return "LoginPage";
    }

    @GetMapping("/showQuestions")
    public String showQuestions(
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) Long topicId,
            @RequestParam(defaultValue = "1") int page,
            Model model) {
        log.info("GET - SHOW QUESTIONS");
        List<Course> courses = quizService.getAllCourses();
        List<Topic> topics = quizService.getAllTopics();
        model.addAttribute("courses", courses);
        model.addAttribute("topics", topics);

        if (courseId != null && topicId != null) {
            List<Question> questions = quizService.getPaginatedQuestions(courseId, topicId, page, 5);
            long total = quizService.getTotalQuestions(courseId, topicId);

            model.addAttribute("questions", questions);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", (int) Math.ceil((double) total / 5));
            model.addAttribute("selectedCourse", courseId);
            model.addAttribute("selectedTopic", topicId);
        }

        return "ShowQuestions";
    }

    @GetMapping("/editQuestionForm")
    public String showEditForm(@RequestParam Long id, Model model) {
        log.info("GET - EDIT QUESTION FORM");
        Question question = quizService.getQuestionById(id);
        List<QuestionOption> options = quizService.getOptionsByQuestionId(id);
        List<Course> courses = quizService.getAllCourses();
        List<Topic> topics = quizService.getAllTopics();

        model.addAttribute("question", question);
        model.addAttribute("options", options);
        model.addAttribute("courses", courses);
        model.addAttribute("topics", topics);

        return "EditQuestion";
    }

    @PostMapping("/updateQuestion")
    public String updateQuestion(HttpServletRequest request) {
        log.info("POST - UPDATE QUESTION");
        Long questionId = Long.parseLong(request.getParameter("questionId"));
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        Long topicId = Long.parseLong(request.getParameter("topicId"));
        String questionText = request.getParameter("question");
        String correctAnswer = request.getParameter("correctAnswer");

        Question question = quizService.getQuestionById(questionId);
        question.setQuestion(questionText);
        question.setCorrectAnswer(correctAnswer);
        question.setCourse(quizService.getCourseById(courseId));
        question.setTopic(quizService.getTopicById(topicId));
        quizService.updateQuestion(question);

        for (int i = 0; i < 4; i++) {
            String optionValue = request.getParameter("option" + i);
            Long optionId = Long.parseLong(request.getParameter("optionId" + i));
            quizService.updateOption(optionId, optionValue);
        }

        return "redirect:/goHomepage";
    }

    @GetMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam Long id) {
        log.info("GET - DELETE QUESTION");
        quizService.deleteQuestion(id);
        return "redirect:/goHomepage";
    }

    @GetMapping("/viewQuestion")
    public String viewFullQuestion(@RequestParam Long id, Model model) {
        log.info("GET - VIEW QUESTION");
        Question question = quizService.getQuestionById(id);
        List<QuestionOption> options = quizService.getOptionsByQuestionId(id);

        model.addAttribute("question", question);
        model.addAttribute("options", options);

        return "ViewQuestion";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        log.info("GET - DASHBOARD");
        List<Course> courses = quizService.getAllCourses();

        List<Map<String, Object>> courseData = new ArrayList<>();

        for (Course course : courses) {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("courseName", course.getCourseName());

            List<Map<String, Object>> topicList = new ArrayList<>();

            for (Topic topic : quizService.getTopicsByCourseId(course.getCourseId())) {
                Map<String, Object> topicMap = new HashMap<>();
                topicMap.put("topicName", topic.getTopicName());
                topicMap.put("questionCount", quizService.getQuestionCountByTopicId(topic.getTopicId()));
                topicList.add(topicMap);
            }

            courseMap.put("topics", topicList);
            courseData.add(courseMap);
        }

        model.addAttribute("courseData", courseData);
        return "Dashboard";
    }

    @GetMapping("/uploadCSVForm")
    public String showUploadCSVForm() {
        log.info("GET - UPLOAD CSV FORM");
        return "UploadCSV";
    }

    @PostMapping("/uploadQuestions")
    public String uploadQuestions(@RequestParam("file") MultipartFile file, Model model) {
        log.info("POST - UPLOAD QUESTIONS");
        if (file.isEmpty()) {
            model.addAttribute("error", "Please select a file to upload.");
            return "UploadCSV";
        }

        try {
            quizService.saveQuestionsFromCSV(file);
            model.addAttribute("message", "Questions uploaded successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error uploading file: " + e.getMessage());
        }

        return "UploadCSV";
    }
}
