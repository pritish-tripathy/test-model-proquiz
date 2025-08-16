<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Practice Topics</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
	crossorigin="anonymous"></script>
</head>
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
<body>
	<div class="header">
		QuizPro<sup>TM</sup> Application
	</div>
	<div class="container mt-4">
	<h4>${course.courseName}-Practice Tests Available in the Following
		Topics</h4>
	<p>Select the Topic to take a Practice Test.</p>
	<div class="row">
		<c:forEach var="t" items="${topics}">
			<div class="col-md-4 mb-3">
				<div class="card p-3 h-100 d-flex justify-content-between">
					<h6>${t.topicName}</h6>
					<a class="btn btn-primary btn-sm"
						href="${pageContext.request.contextPath}/student/practice/instructions?courseId=${course.courseId}&topicId=${t.topicId}">
						Choose Topic </a>
				</div>
			</div>
		</c:forEach>
	</div>
	</div>
	<div class="footer mt-5">
        QuizPro &copy; 2025, All Rights Reserved
    </div>
</body>
</html>
