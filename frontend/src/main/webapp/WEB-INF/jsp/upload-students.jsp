<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload Students</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
</head>
<body>
<a href="${pageContext.request.contextPath}/students">Return to list students</a>
<h1>Upload Students</h1>
<form action="${pageContext.request.contextPath}/students/save-batch" method="post" modelAttribute="studentWrapper">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Score</th>
            <th>Class ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentWrapper.students}" varStatus="studentStat">
            <tr>
                <td><label>
                    <input type="number" name="students[${studentStat.index}].id" value="${student.id}"/>
                </label></td>
                <td><label>
                    <input type="text" name="students[${studentStat.index}].firstName" value="${student.firstName}" />
                </label></td>
                <td><label>
                    <input type="text" name="students[${studentStat.index}].lastName" value="${student.lastName}" />
                </label></td>
                <td><label>
                    <input type="email" name="students[${studentStat.index}].email" value="${student.email}" />
                </label></td>
                <td><label>
                      <input type="text" name="students[${studentStat.index}].gender" value="${student.gender}" />
                </label></td>
                <td><label>
                    <input type="number" name="students[${studentStat.index}].score" value="${student.score}" />
                </label></td>
                <td><label>
                    <input type="text" name="students[${studentStat.index}].classId" value="${student.classId}" />
                </label></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Save All" />
</form>
</body>
</html>
