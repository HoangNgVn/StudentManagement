<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Student</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/form.css"/>
</head>
<body>
<a href="${pageContext.request.contextPath}/students">Return to list students</a>
<h1>Create Student</h1>
<form action="${pageContext.request.contextPath}/students" method="post" class="student-form">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" class="form-input"/>
    <br/>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" class="form-input"/>
    <br/>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" class="form-input"/>
    <br/>
    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" class="form-input"/>
    <br/>
    <label for="score">Score:</label>
    <input type="number" id="score" name="score" class="form-input"/>
    <br/>
    <label for="classId">Class ID:</label>
    <input type="number" id="classId" name="classId" class="form-input"/>
    <br/>
    <input type="submit" value="Create" class="form-submit"/>
</form>
</body>
</html>
