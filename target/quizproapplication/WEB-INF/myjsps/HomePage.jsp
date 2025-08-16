<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
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

        .sub-header {
            background-color: #00B8F1;
            color: white;
            padding: 10px 20px;
            font-size: 18px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logout-btn {
            background-color: red;
            color: white;
            border: none;
            padding: 8px 14px;
            font-weight: bold;
            border-radius: 4px;
            text-decoration: none;
        }

        .main-container {
            display: flex;
            height: calc(100vh - 100px);
        }

        .sidebar {
            width: 250px;
            padding: 20px;
            border-right: 2px solid #ccc;
            background-color: #f8f9fa;
        }

        .sidebar button {
            width: 100%;
            padding: 15px;
            margin-bottom: 20px;
            font-size: 16px;
            color: white;
            border: none;
            cursor: pointer;
        }

        .content-frame {
            flex-grow: 1;
            border: none;
        }
    </style>
</head>

<body>
    <div class="header">
        QuizPro<sup>TM</sup> Application
    </div>

    <div class="sub-header">
        <div>
            Welcome <%= session.getAttribute("username") != null ? session.getAttribute("username") : "User" %>
        </div>

            <a href="goLoginpage" class="btn btn-danger" target="_top">Log Out</a>

    </div>

    <div class="main-container">
        <div class="sidebar">
            <button class="btn btn-success" onclick="loadPage('addQuestionForm')">Add Question</button>
            <button class="btn btn-primary" onclick="loadPage('showQuestions')">View Question</button>
            <button class="btn btn-warning" onclick="loadPage('uploadCSVForm')">Upload Questions</button>
        </div>

        <iframe id="contentFrame" name="contentFrame" class="content-frame" src="dashboard"></iframe>

    </div>

    <script>
        function loadPage(url) {
            document.getElementById("contentFrame").src = url;
        }
    </script>

</body>
</html>
