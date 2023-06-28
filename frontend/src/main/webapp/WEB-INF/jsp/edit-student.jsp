<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/form.css"/>
</head>
<body>
<a href="${pageContext.request.contextPath}/students">Return to list students</a>
<h1>Edit Student</h1>
<form action="${pageContext.request.contextPath}/students/${student.id}/update" method="post" class="student-form">
    <input type="hidden" id="id" name="id" value="${student.id}"/>
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" value="${student.firstName}" class="form-input"/>
    <br/>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" value="${student.lastName}" class="form-input"/>
    <br/>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${student.email}" class="form-input"/>
    <br/>
    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" value="${student.gender}" class="form-input"/>
    <br/>
    <label for="score">Score:</label>
    <input type="number" id="score" name="score" value="${student.score}" class="form-input"/>
    <br/>
    <label for="classId">Class ID:</label>
    <input type="number" id="classId" name="classId" value="${student.classId}" class="form-input"/>
    <br/>
    <input type="submit" value="Update" class="form-submit"/>
</form>
</body>
</html>
