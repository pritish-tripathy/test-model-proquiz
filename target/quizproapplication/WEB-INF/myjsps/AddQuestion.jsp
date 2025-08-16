<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New Quiz Question</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f8f9fa;">
<div style="background-color: black;">
		<h1 style="text-align: center; color: white; margin: 0; padding: 10px 0;">QuizPro<sup>TM</sup> Application</h1>
</div>
<div class="container mt-5">
    <h2 class="mb-4">Add New Question</h2>

    <c:if test="${param.success == 'true'}">
        <div class="alert alert-success">Question added successfully!</div>
    </c:if>

    <form method="post" action="addQuestion">
        <div class="mb-3">
            <label for="course" class="form-label">Course</label>
            <select name="courseId" class="form-select" required>
                <c:forEach var="course" items="${courses}">
                    <option value="${course.courseId}">${course.courseName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="topic" class="form-label">Topic</label>
            <select name="topicId" class="form-select" required>
                <c:forEach var="topic" items="${topics}">
                    <option value="${topic.topicId}">${topic.topicName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Question</label>
            <textarea name="question" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Option 1</label>
            <textarea name="option1" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Option 2</label>
            <textarea name="option2" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Option 3</label>
            <textarea name="option3" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Option 4</label>
            <textarea name="option4" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Correct Answer</label>
            <input name="correctAnswer" class="form-control" type="text" required/>
        </div>

        <button type="submit" class="btn btn-primary">Add Question</button>
        <a href="goHomepage" class="btn btn-warning" target="_top">Go To Home</a>
    </form>
</div>
</body>
</html>
