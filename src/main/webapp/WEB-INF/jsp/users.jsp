<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>User List</title>
     <link rel="stylesheet" href="/../css/Profile.css">
        <script src="/../js/validation.js"> </script>
         <script src="/../js/jQuery-3.4.1.js"></script>
    <%@include file="bootstrapFiles.jsp" %>
</head>
<body>
<h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>

<h4 class="text-center">Users</h4>

<c:choose>
    <c:when test="${empty usersList}">
        <div class="alert alert-info table-div text-center">
            Currently, no user is available in this system.
        </div>
    </c:when>
    <c:otherwise>
        <div class="table-responsive table-div">
            <table class="table table-hover">
                <thead class="thead-light">
                <tr>
                    <th>Full Name</th>
                    <th>User Name</th>
                    <th>Mail</th>
                    <th>Contact</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${usersList}">
                    <tr>
                        <td>${user.fullName}</td>
                        <td>${user.userName}</td>
                        <td>${user.userMail}</td>
                        <td>${user.userContact}</td>
                        <td>
                            <c:url value="/view/post" var = "view">
                                <c:param name="userId" value="${user.id}"/>
                            </c:url>
                            <div class="btn-group">
                                <a href="${view}" class="btn btn-outline-primary">View Posts</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
