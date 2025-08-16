<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Full View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div style="background-color: black;">
	<h1 style="text-align: center; color: white; padding: 10px;">QuizPro<sup>TM</sup> Application</h1>
</div>

<div class="container mt-4">
<h2 class="mb-4">Add New Question</h2>
    <table class="table table-bordered">
        <tr>
            <th>Course ID</th>
            <td>${question.course.courseId}</td>
        </tr>
        <tr>
            <th>Course Name</th>
            <td>${question.course.courseName}</td>
        </tr>
        <tr>
            <th>Topic ID</th>
            <td>${question.topic.topicId}</td>
        </tr>
        <tr>
            <th>Topic Name</th>
            <td>${question.topic.topicName}</td>
        </tr>
        <tr>
            <th>Question ID</th>
            <td>${question.questionId}</td>
        </tr>
        <tr>
            <th>Question</th>
            <td>${question.question}</td>
        </tr>
        <tr>
            <th>Options</th>
            <td>
                <ul>
                    <c:forEach var="opt" items="${options}">
                        <li>${opt.optionData}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <th>Correct Answer</th>
            <td>${question.correctAnswer}</td>
        </tr>
    </table>
    <a href="showQuestions" class="btn btn-secondary ms-3">Back</a>
</div>
<footer style="background-color: black; color: white; text-align: center; padding: 10px 0; margin-top: 40px;">
    Copyright &copy; 2025 QuizPro Application
</footer>
</body>
</html>
