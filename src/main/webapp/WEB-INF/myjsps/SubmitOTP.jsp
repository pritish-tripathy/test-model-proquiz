<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verify OTP</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet"
	crossorigin="anonymous">
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
	.otp-card {
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

	<!-- OTP Form -->
	<div class="container d-flex justify-content-center align-items-center">
		<div class="card otp-card p-4">
			<h3 class="text-center mb-3">Verify OTP</h3>
			<p class="text-muted text-center">Enter the OTP sent to your registered email/phone</p>
			<form method="post" action="verifyOTP">
				<div class="mb-3">
					<label for="otp" class="form-label">One-Time Password</label>
					<input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP" required>
				</div>
				<div class="d-grid mb-3">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
				<c:if test="${not empty error}">
					<p class="text-danger text-center mt-2">${error}</p>
				</c:if>
			</form>
		</div>
	</div>

	<!-- Footer -->
	<div class="footer">
		QuizPro &copy; 2025, All Rights Reserved
	</div>
</body>
</html>
