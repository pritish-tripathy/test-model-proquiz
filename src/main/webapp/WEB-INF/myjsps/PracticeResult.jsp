<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Practice Result</title>
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
	<h4>Result Summary</h4>
	<p>
		Student: ${result.studentName} (ID: ${result.studentId})<br />
		Course: ${result.courseName} | Topic: ${result.topicName}<br />
		Score: ${result.marksObtained} / ${result.noOfQuestions}
	</p>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Question ID</th>
				<th>Question</th>
				<th>Correct Answer</th>
				<th>Your Answer</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qr" items="${result.questionWiseResult}">
				<tr>
					<td>${qr.questionId}</td>
					<td>${qr.question}</td>
					<td>${qr.correctAnswer}</td>
					<td>${qr.yourAnswer}</td>
					<td><span
						class="badge <c:out value='${qr.status == "CORRECT" ? "bg-success" : "bg-danger"}'/>">
							${qr.status} </span></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a class="btn btn-outline-primary"
		href="${pageContext.request.contextPath}/student/dashboard"> Take
		another Test </a>
	</div>
	<div class="footer mt-5">
        QuizPro &copy; 2025, All Rights Reserved
    </div>
</body>
</html>
