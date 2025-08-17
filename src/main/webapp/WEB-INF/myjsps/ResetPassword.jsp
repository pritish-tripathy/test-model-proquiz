<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
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

.reset-card {
	max-width: 450px;
	width: 100%;
	margin-top: 60px;
	margin-bottom: 80px;
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

	<!-- Reset Password Form -->
	<div class="container d-flex justify-content-center align-items-center">
		<div class="card reset-card p-4">
			<h3 class="text-center mb-3">Reset Your Password</h3>
			<p class="text-muted text-center">Enter your User ID and a new
				password below</p>

			<form method="post" action="resetPassword">
				<div class="mb-3">
					<label for="userIdOrUsername" class="form-label">User ID</label> <input
						type="text" class="form-control" id="userIdOrUsername"
						name="userIdOrUsername" placeholder="Enter User ID" required>
				</div>
				<div class="mb-3">
					<label for="newPassword" class="form-label">New Password</label> <input
						type="password" class="form-control" id="newPassword"
						name="newPassword" placeholder="Enter New Password" required>
				</div>

				<div class="d-grid mb-3">
					<button type="submit" class="btn btn-success">Reset
						Password</button>
				</div>

				<c:if test="${not empty msg}">
					<p class="text-success text-center">${msg}</p>
				</c:if>
				<c:if test="${not empty errorMsg}">
					<p class="text-danger text-center">${errorMsg}</p>
				</c:if>

				<div class="d-grid mt-3">
					<a href="backLogin" class="btn btn-primary">Back to Login</a>
				</div>
			</form>
		</div>
	</div>

	<!-- Footer -->
	<div class="footer">QuizPro &copy; 2025, All Rights Reserved</div>
</body>
</html>
