package com.quizpro.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizpro.model.Course;
import com.quizpro.model.PracticeTestResult;
import com.quizpro.model.Question;
import com.quizpro.model.Topic;
import com.quizpro.service.PracticeTestService;
import com.quizpro.service.QuizService;

@Controller
@RequestMapping("/student")
public class StudentPracticeController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private PracticeTestService practiceService;

    // Dashboard
    @GetMapping("/dashboard")
    public String studentDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("assignedTestsMsg", "No Test Assigned to you as of Now");
        model.addAttribute("courses", practiceService.getAllCourses());
        return "StudentDashboard";
    }

    // List topics for a selected course
    @GetMapping("/practice/topics")
    public String showPracticeTopics(@RequestParam("courseId") Long courseId, Model model) {
        Course course = quizService.getCourseById(courseId);
        List<Topic> topics = quizService.getTopicsByCourseId(courseId);
        model.addAttribute("course", course);
        model.addAttribute("topics", topics);
        return "PracticeTopics";
    }

    // Instructions page before starting
    @GetMapping("/practice/instructions")
    public String practiceInstructions(@RequestParam("courseId") Long courseId,
                                       @RequestParam("topicId") Long topicId,
                                       Model model) {
        Course course = quizService.getCourseById(courseId);
        Topic topic = quizService.getTopicById(topicId);
        model.addAttribute("course", course);
        model.addAttribute("topic", topic);
        
        List<String> instructions = Arrays.asList(
                "Read each question carefully before selecting your answer.",
                "You cannot go back to previous questions once you submit an answer.",
                "Each correct answer carries 1 mark; there is no negative marking.",
                "Do not refresh or close the browser during the test.",
                "Click the End Test button after the final question to view your results."
        );
        model.addAttribute("instructions", instructions); // as per requirement
        return "PracticeInstructions";
    }

    // Start the test
    @PostMapping("/practice/start")
    public String startPractice(@RequestParam("courseId") Long courseId,
                                @RequestParam("topicId") Long topicId,
                                HttpSession session) {
        List<Question> questions = practiceService.getAllQuestionsForTopic(courseId, topicId); // uses DAO count+list :contentReference[oaicite:6]{index=6}
        session.setAttribute("practiceQuestions", questions);
        session.setAttribute("practiceAnswers", new HashMap<Long, String>());
        session.setAttribute("practiceIndex", 0);
        session.setAttribute("practiceCourseId", courseId);
        session.setAttribute("practiceTopicId", topicId);
        return "redirect:/student/practice/question";
    }

    // Render current question
    @GetMapping("/practice/question")
    public String showQuestion(HttpSession session, Model model) {
        List<Question> questions = (List<Question>) session.getAttribute("practiceQuestions");
        Integer idx = (Integer) session.getAttribute("practiceIndex");
        if (questions == null || questions.isEmpty()) {
            model.addAttribute("error", "No questions available for this topic.");
            return "PracticeTest";
        }
        Question q = questions.get(idx);

        model.addAttribute("question", q);
        model.addAttribute("options", q.getOptions());
        model.addAttribute("index", idx + 1);
        model.addAttribute("total", questions.size());

        Course course = quizService.getCourseById((Long) session.getAttribute("practiceCourseId"));
        Topic topic = quizService.getTopicById((Long) session.getAttribute("practiceTopicId"));
        model.addAttribute("course", course);
        model.addAttribute("topic", topic);

        return "PracticeTest";
    }

    // Capture answer and move to next
    @PostMapping("/practice/answer")
    public String submitAnswer(@RequestParam("questionId") Long questionId,
                               @RequestParam(value = "yourAnswer", required = false) String yourAnswer,
                               HttpSession session) {
        Map<Long, String> answers = (Map<Long, String>) session.getAttribute("practiceAnswers");
        if (answers == null) answers = new HashMap<>();
        answers.put(questionId, yourAnswer == null ? "" : yourAnswer);
        session.setAttribute("practiceAnswers", answers);

        Integer idx = (Integer) session.getAttribute("practiceIndex");
        List<Question> questions = (List<Question>) session.getAttribute("practiceQuestions");
        if (idx + 1 < questions.size()) {
            session.setAttribute("practiceIndex", idx + 1);
            return "redirect:/student/practice/question";
        } else {
            return "redirect:/student/practice/end";
        }
    }

    // End test, compute result, persist and show table
    @GetMapping("/practice/end")
    public String endPractice(HttpSession session, Model model) {
        List<Question> questions = (List<Question>) session.getAttribute("practiceQuestions");
        Map<Long, String> answers = (Map<Long, String>) session.getAttribute("practiceAnswers");
        Long courseId = (Long) session.getAttribute("practiceCourseId");
        Long topicId = (Long) session.getAttribute("practiceTopicId");

        if (questions == null || answers == null) {
            model.addAttribute("error", "Session expired or test not started.");
            return "StudentDashboard";
        }

        Course course = quizService.getCourseById(courseId);
        Topic topic = quizService.getTopicById(topicId);

        Long studentId = (Long) session.getAttribute("userIdLong");
        String studentName = (String) session.getAttribute("username");

        // Build and persist result
        PracticeTestResult result = practiceService.createEmptyResult(
                studentId, studentName, course.getCourseName(), topic.getTopicName(), questions.size());

        for (Question q : questions) {
            String ans = answers.getOrDefault(q.getQuestionId(), "");
            practiceService.addQuestionResult(result, q, ans);
        }
        practiceService.finalizeAndSave(result);

        model.addAttribute("result", result);
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);

        // cleanup session state for safety
        session.removeAttribute("practiceQuestions");
        session.removeAttribute("practiceAnswers");
        session.removeAttribute("practiceIndex");
        session.removeAttribute("practiceCourseId");
        session.removeAttribute("practiceTopicId");

        return "PracticeResult";
    }
}