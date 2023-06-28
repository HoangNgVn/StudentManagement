<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=YTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
    <title>Students Management System</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="container-fluid">
      <div>
        <h2>Manage Students</h2>
        <a href="${pageContext.request.contextPath}/students/new">Create New Student</a>
      </div>

      <div>
        <form action="${pageContext.request.contextPath}/students/page/1" class="form-inline m-3">
          Filter:&nbsp;
          <input type="search" name="keyword" value="${keyword}" class="form-control" pattern="^\S.*" required />
          &nbsp;&nbsp;
          <input type="submit" value="Search" class="btn btn-primary" />
          &nbsp;&nbsp;
          <input type="button" value="Clear" class="btn btn-secondary" onclick="clearFilter()" />
        </form>
      </div>

      <div>
        <table class="table table-responsive-xl table-bordered table-striped table-hover">
          <thead class="thead-dark">
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Gender</th>
              <th>Score</th>
              <th>Class ID</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="student" items="${listStudents}">
              <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.email}</td>
                <td>${student.gender}</td>
                <td>${student.score}</td>
                <td>${student.classId}</td>
                <td>
                  <a href="/students/edit/${student.id}" class="fas fa-edit fa-2x icon-green" title="Edit this student"></a>
                  &nbsp;
                  <a href="/students/delete/${student.id}" class="fas fa-trash fa-2x icon-dark link-delete" title="Delete this student"></a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="text-center m-1">
        <c:if test="${totalItems > 0}">
          <span>Showing students # ${startCount} to ${endCount} of ${totalItems}</span>
        </c:if>
      </div>

      <div class="text-center m-1">
        <c:if test="${!(totalItems > 0)}">
          <span>No students found</span>
        </c:if>
      </div>

      <div>
        <c:if test="${totalPages > 1}">
          <nav>
            <ul class="pagination justify-content-center">
              <li class="${currentPage > 1 ? 'page-item' : 'page-item disabled'}">
                <a class="page-link" href="/students/page/1?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">First</a>
              </li>
              <li class="${currentPage > 1 ? 'page-item' : 'page-item disabled'}">
                <a class="page-link" href="/students/page/${currentPage - 1}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">Previous</a>
              </li>
              <c:choose>
                <c:when test="${totalPages <= 10}">
                  <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="${currentPage != i ? 'page-item' : 'page-item active'}">
                      <a class="page-link" href="/students/page/${i}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">${i}</a>
                    </li>
                  </c:forEach>
                </c:when>
                <c:when test="${totalPages > 10}">
                  <c:choose>
                    <c:when test="${currentPage <= 5 || currentPage > totalPages - 4}">
                      <c:forEach var="i" begin="1" end="5">
                        <li class="${currentPage != i ? 'page-item' : 'page-item active'}">
                          <a class="page-link" href="/students/page/${i}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">${i}</a>
                        </li>
                      </c:forEach>
                      <a class="page-link" href="/students/page/6?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">...</a>
                      <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}">
                        <li class="${currentPage != i ? 'page-item' : 'page-item active'}">
                          <a class="page-link" href="/students/page/${i}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">${i}</a>
                        </li>
                      </c:forEach>
                    </c:when>
                    <c:when test="${currentPage > 5 && currentPage <= totalPages - 4}">
                      <a class="page-link" href="/students/page/${currentPage - 5}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">...</a>
                      <c:forEach var="i" begin="${currentPage - 4}" end="${currentPage + 4}">
                        <li class="${currentPage != i ? 'page-item' : 'page-item active'}">
                          <a class="page-link" href="/students/page/${i}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">${i}</a>
                        </li>
                      </c:forEach>
                      <a class="page-link" href="/students/page/${currentPage + 5}">...</a>
                    </c:when>
                  </c:choose>
                </c:when>
              </c:choose>
              <li class="${currentPage < totalPages ? 'page-item' : 'page-item disabled'}">
                <a class="page-link" href="/students/page/${currentPage + 1}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">Next</a>
              </li>
              <li class="${currentPage < totalPages ? 'page-item' : 'page-item disabled'}">
                <a class="page-link" href="/students/page/${totalPages}?<c:if test="${keyword != null}">keyword=${keyword}</c:if>">Last</a>
              </li>
            </ul>
          </nav>
        </c:if>
      </div>
    </div>
    <script type="text/javascript">
      function clearFilter() {
        window.location = "[[@{/students}]]";
      }
    </script>
  </body>
</html>
