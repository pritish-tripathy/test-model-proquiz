<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Practice Test</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
	crossorigin="anonymous"></script>
<style>
body {
	margin: 0;
	padding: 0;
}

.header {
	background-color: black;
	color: white;
	text-align: center;
	padding: 10px;
	font-size: 24px;
	font-weight: bold;
}
.footer {
	background-color: black;
	color: white;
	text-align: center;
	padding: 10px;
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	font-size: 14px;
}
</style>
</head>
<body>
	<div class="header">
		QuizPro<sup>TM</sup> Application
	</div>
	<div class="container mt-4">
	<h5>Practice Test - Course: ${course.courseName}</h5>
	<h6>Topic: ${topic.topicName}</h6>
	<hr />
	<c:if test="${not empty error}">
		<div class="alert alert-warning">${error}</div>
	</c:if>
	<c:if test="${empty error}">
		<p>
			<strong>Q${index}/${total}:</strong> ${question.question}
		</p>
		<form method="post"
			action="${pageContext.request.contextPath}/student/practice/answer">
			<input type="hidden" name="questionId" value="${question.questionId}" />
			<c:forEach var="opt" items="${options}">
				<div class="form-check">
					<input class="form-check-input" type="radio" name="yourAnswer"
						id="opt_${opt.optionId}" value="${opt.optionData}"> <label
						class="form-check-label" for="opt_${opt.optionId}">${opt.optionData}</label>
				</div>
			</c:forEach>
			<br />
			<button type="submit" class="btn btn-primary">
				<c:choose>
					<c:when test="${index == total}">End Test</c:when>
					<c:otherwise>Next Question</c:otherwise>
				</c:choose>
			</button>
		</form>
	</c:if>
	</div>
	<div class="footer mt-5">
        QuizPro &copy; 2025, All Rights Reserved
    </div>
</body>
</html>
