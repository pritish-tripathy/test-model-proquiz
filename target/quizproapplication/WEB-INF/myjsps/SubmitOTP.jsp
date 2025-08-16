<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verify OTP</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
	crossorigin="anonymous"></script>
</head>
<body style="margin: 0; padding: 0; background-color: #FEEBF6"">
	<div style="background-color: black;">
		<h1
			style="text-align: center; color: white; margin: 0; padding: 10px 0;">
			QuizPro<sup>TM</sup> Application
		</h1>
	</div>
	<div
		style="display: flex; justify-content: center; align-items: center; flex-direction: column; margin-top: 40px;">
		<form method="post" action="verifyOTP">
			<table>
				<tr>
					<td><label class="form-label">Submit OTP </label></td>
					<td><input type="text" name="otp" class="form-control" id="exampleFormControlInput1"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit"  class="btn btn-primary" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><p style="color: red">${error}</p></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>