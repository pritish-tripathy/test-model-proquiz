<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Question</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Edit Quiz Question</h2>
    <form action="updateQuestion" method="post"  target="_top">
        <input type="hidden" name="questionId" value="${question.questionId}"/>

        <div class="mb-3">
            <label>Course</label>
            <select name="courseId" class="form-select" required>
                <c:forEach var="c" items="${courses}">
                    <option value="${c.courseId}" ${c.courseId == question.course.courseId ? 'selected' : ''}>${c.courseName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Topic</label>
            <select name="topicId" class="form-select" required>
                <c:forEach var="t" items="${topics}">
                    <option value="${t.topicId}" ${t.topicId == question.topic.topicId ? 'selected' : ''}>${t.topicName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Question</label>
            <textarea name="question" class="form-control" required>${question.question}</textarea>
        </div>

        <c:forEach var="opt" items="${options}" varStatus="i">
            <div class="mb-2">
                <label>Option ${i.index + 1}</label>
                <input type="hidden" name="optionId${i.index}" value="${opt.optionId}"/>
                <textarea name="option${i.index}" class="form-control" required>${opt.optionData}</textarea>
            </div>
        </c:forEach>

        <div class="mb-3">
            <label>Correct Answer</label>
            <input type="text" name="correctAnswer" class="form-control" value="${question.correctAnswer}" required/>
        </div>

        <button type="submit" class="btn btn-primary">Update Question</button>
        <a href="home" class="btn btn-secondary ms-2" target="_top">Cancel</a>
    </form>
</div>
</body>
</html>
