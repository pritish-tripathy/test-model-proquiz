<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Dashboard</title>
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
	<div class="d-flex justify-content-between align-items-center mb-3">
		<h4>Hello ${username}</h4>
		<a href="${pageContext.request.contextPath}/logout"
			class="btn btn-outline-danger btn-sm">Logout</a>
	</div>

	<div class="row">
		<div class="col-md-6">
			<h5>Assigned Tests</h5>
			<div class="border p-3">${assignedTestsMsg}</div>
		</div>
		<div class="col-md-6">
			<h5>Practice Test</h5>
			<ul class="list-group">
				<c:forEach var="c" items="${courses}">
					<li
						class="list-group-item d-flex justify-content-between align-items-center">
						${c.courseName} <a class="btn btn-primary btn-sm"
						href="${pageContext.request.contextPath}/student/practice/topics?courseId=${c.courseId}">
							Select </a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</div>
	<div class="footer mt-5">
        QuizPro &copy; 2025, All Rights Reserved
    </div>
</body>
</html>
