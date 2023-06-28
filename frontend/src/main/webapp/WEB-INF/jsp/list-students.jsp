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

<a href="${pageContext.request.contextPath}/students/create">Insert</a>
<br>
<a href="${pageContext.request.contextPath}/students/export">Export to CSV</a>


<form action="${pageContext.request.contextPath}/students/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" required>
    <br>
    <input type="submit" value="Upload">
</form>
<table>
    <thead>
    <tr>
        <th><a href="${pageContext.request.contextPath}/students?sort=id&page=${currentPage}">Id</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=firstName&page=${currentPage}">First Name</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=lastName&page=${currentPage}">Last Name</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=email&page=${currentPage}">Email</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=gender&page=${currentPage}">Gender</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=score&page=${currentPage}">Score</a></th>
        <th><a href="${pageContext.request.contextPath}/students?sort=classId&page=${currentPage}">Class Id</a></th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td><span>${student.id}</span></td>
            <td><span>${student.firstName}</span></td>
            <td><span>${student.lastName}</span></td>
            <td><span>${student.email}</span></td>
            <td><span>${student.gender}</span></td>
            <td><span>${student.score}</span></td

            >
            <td><span>${student.classId}</span></td>
            <td>
                <a href="${pageContext.request.contextPath}/students/${student.id}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/students/${student.id}/delete" onclick="return confirmDelete()">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <ul class="pagination">
        <li class="${currentPage == 0 ? 'disabled' : ''}">
            <a href="${pageContext.request.contextPath}/students?page=0">First</a>
        </li>
        <li class="${currentPage == 0 ? 'disabled' : ''}">
            <a href="${pageContext.request.contextPath}/students?page=${currentPage - 1}">Previous</a>
        </li>

        <c:if test="${currentPage - 2 >= 0}">
            <li>
                <a href="${pageContext.request.contextPath}/students?page=0">1</a>
            </li>
            <li>
                <span>...</span>
            </li>
        </c:if>

        <c:forEach begin="${currentPage - 2 < 0 ? 0 : currentPage - 2}" end="${currentPage + 2 >= totalPages ? totalPages - 1 : currentPage + 2}" var="pageNumber">
            <li class="${currentPage == pageNumber ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/students?page=${pageNumber}">${pageNumber + 1}</a>
            </li>
        </c:forEach>

        <c:if test="${currentPage + 2 < totalPages - 1}">
            <li>
                <span>...</span>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/students?page=${totalPages - 1}">${totalPages}</a>
            </li>
        </c:if>

        <li class="${currentPage == totalPages - 1 ? 'disabled' : ''}">
            <a href="${pageContext.request.contextPath}/students?page=${currentPage + 1}">Next</a>
        </li>
        <li class="${currentPage == totalPages - 1 ? 'disabled' : ''}">
            <a href="${pageContext.request.contextPath}/students?page=${totalPages - 1}">Last</a>
        </li>
    </ul>
</div>


<script>
    function confirmDelete() {
        if (confirm("Are you sure to delete?")) {
            return true;
        } else {
            return false;
        }
    }
</script>

</body>
</html>