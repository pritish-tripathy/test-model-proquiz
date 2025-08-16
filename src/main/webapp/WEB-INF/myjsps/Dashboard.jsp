<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Course Overview</h2>
    <c:forEach var="course" items="${courseData}">
        <div class="card mb-3">
            <div class="card-header bg-dark text-white">
                ${course.courseName}
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <c:forEach var="topic" items="${course.topics}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            ${topic.topicName}
                            <span class="badge bg-primary rounded-pill">${topic.questionCount} Questions</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
