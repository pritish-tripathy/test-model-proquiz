<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QuizPro Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<style>
body {
	margin: 0;
	padding: 0;
	background-color: #f8f9fa;
	font-family: Arial, sans-serif;
}

.header {
	background-color: black;
	color: white;
	text-align: center;
	padding: 15px;
	font-size: 28px;
	font-weight: bold;
}

.login-card {
	max-width: 400px;
	width: 100%;
	margin-top: 60px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
	border-radius: 12px;
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
	<!-- Header -->
	<div class="header">
		QuizPro<sup>TM</sup> Application
	</div>

	<!-- Login Form -->
	<div class="container d-flex justify-content-center align-items-center">
		<div class="card login-card p-4">
			<h3 class="text-center mb-4">Login</h3>
			<form method="post" action="verifyUser">
				<div class="mb-3">
					<label for="userId" class="form-label">User ID</label> <input
						type="text" class="form-control" id="userId" name="userId"
						placeholder="Enter your User ID" required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" id="password" name="password"
						placeholder="Enter your Password" required>
				</div>
				<div class="d-grid mb-3">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
				<div class="d-grid mb-2">
					<a href="forgotPassword" class="btn btn-outline-danger">Forgot
						Password?</a>
				</div>
				<c:if test="${not empty errorMsg}">
					<p class="text-danger text-center mt-2">${errorMsg}</p>
				</c:if>
			</form>
		</div>
	</div>

	<!-- Footer -->
	<div class="footer">QuizPro &copy; 2025, All Rights Reserved</div>
</body>
</html>
