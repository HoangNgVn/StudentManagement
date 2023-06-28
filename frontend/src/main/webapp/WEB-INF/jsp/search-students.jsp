<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<a href="${pageContext.request.contextPath}/students">Return to list students</a>
<form action="${pageContext.request.contextPath}/students/search" method="GET">
    <label>
        <input type="number" name="id" placeholder="Search by Id"/>
    </label>
    <label>
        <input type="text" name="firstName" placeholder="Search by First Name"/>
    </label>
    <label>
        <input type="text" name="lastName" placeholder="Search by Last Name"/>
    </label>
    <label>
        <input type="text" name="email" placeholder="Search by Last Email"/>
    </label>
    <label>
        <input type="text" name="gender" placeholder="Search by Last Gender"/>
    </label>
    <label>
        <input type="number" name="score" placeholder="Search by Score"/>
    </label>
    <label>
        <input type="text" name="classId" placeholder="Search by Class Id"/>
    </label>
    <button type="submit">Search</button>
</form>

<table>
    <thead>
    <tr>
        <th><a href="${pageContext.request.contextPath}/students?sort=id">Id</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=firstName">First Name</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=lastName">Last Name</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=email">Email</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=gender">Gender</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=score">Score</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=classId">Class Id</a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td><span><c:out value="${student.id}"/></span></td>
            <td><span><c:out value="${student.firstName}"/></span></td>
            <td><span><c:out value="${student.lastName}"/></span></td>
            <td><span><c:out value="${student.email}"/></span></td>
            <td><span><c:out value="${student.gender}"/></span></td>
            <td><span><c:out value="${student.score}"/></span></td>
            <td><span><c:out value="${student.classId}"/></span></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
