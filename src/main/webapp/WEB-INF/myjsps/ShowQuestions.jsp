<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show Questions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div style="background-color: black;">
		<h1 style="text-align: center; color: white; margin: 0; padding: 10px 0;">QuizPro<sup>TM</sup> Application</h1>
</div>
<div class="container mt-4">
    <h2>Show Questions</h2>

    <form method="get" action="showQuestions" class="row g-3 mb-4">
        <div class="col-md-4">
            <label class="form-label">Course</label>
            <select name="courseId" class="form-select" required>
                <c:forEach var="c" items="${courses}">
                    <option value="${c.courseId}" ${c.courseId == selectedCourse ? 'selected' : ''}>${c.courseName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-4">
            <label class="form-label">Topic</label>
            <select name="topicId" class="form-select" required>
                <c:forEach var="t" items="${topics}">
                    <option value="${t.topicId}" ${t.topicId == selectedTopic ? 'selected' : ''}>${t.topicName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-4 d-flex align-items-end">
            <button type="submit" class="btn btn-primary">Filter</button>
            <br/>
            <br/>
            <a href="goHomepage" class="btn btn-warning ms-3" target="_top">Go To Home</a>
        </div>
    </form>

    <c:if test="${not empty questions}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Question</th>
                <th>Correct Answer</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="q" items="${questions}">
                <tr>
                    <td>${q.question}</td>
                    <td>${q.correctAnswer}</td>
                    <td>
                        <a href="viewQuestion?id=${q.questionId}" class="btn btn-info">Full View</a>
                        <a href="editQuestionForm?id=${q.questionId}" class="btn btn-warning">Edit</a>
                        <a href="deleteQuestion?id=${q.questionId}" class="btn btn-danger" target="_top"
                           onclick="return confirm('Delete this question?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Pagination -->
        <div class="d-flex justify-content-between">
            <c:if test="${currentPage > 1}">
                <a href="showQuestions?courseId=${selectedCourse}&topicId=${selectedTopic}&page=${currentPage - 1}" class="btn btn-secondary">Previous</a>
            </c:if>

            <c:if test="${currentPage < totalPages}">
                <a href="showQuestions?courseId=${selectedCourse}&topicId=${selectedTopic}&page=${currentPage + 1}" class="btn btn-secondary ms-auto">Next</a>
            </c:if>
        </div>
    </c:if>
</div>
</body>
</html>