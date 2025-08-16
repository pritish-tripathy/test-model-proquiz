<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QuizPro Login</title>
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
<body style="margin: 0; padding: 0; background-color: #FEEBF6">
	<div style="background-color: black;">
		<h1
			style="text-align: center; color: white; margin: 0; padding: 10px 0;">
			QuizPro<sup>TM</sup> Application
		</h1>
	</div>
	<div
		style="display: flex; justify-content: center; align-items: center; flex-direction: column; margin-top: 40px;">
		<form method="post" action="verifyUser">
			<table>
				<tr>
					<td><label class="form-label">User ID </label></td>
					<td><input type="text" name="userId"  class="form-control" id="exampleFormControlInput1" /></td>
				</tr>
				<tr>
					<td><br/></td>
					<td><br/></td>
				</tr>
				<tr>
					<td><label class="form-label">Password </label></td>
					<td><input type="password" name="password"   class="form-control" id="exampleFormControlInput1" /></td>
				</tr>
				<tr>
					<td><br/></td>
					<td><br/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Login"  class="btn btn-primary"/></td>
				</tr>
				<tr>
					<td><br/></td>
					<td><br/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><a href="forgotPassword"
						 class="btn btn-danger">Forgot Password?</a></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><p style="color: red">${errorMsg}</p></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>




