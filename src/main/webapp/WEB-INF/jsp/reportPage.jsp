<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Report</title>
    <%@include file="bootstrapFiles.jsp"%>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light">

     <c:url value="/changePassword" var = "changePassword">
        <c:param name="userId" value="${sessionScope.currentUser.id}"/>
     </c:url>


    <ul class="navbar-nav mr-auto">

        <div class="dropdown">
          <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            My Profile
          </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="nav-link" href="/profile">Profile</a>
                <a class="nav-link" href="${changePassword}">Change Password</a>
            </div>
        </div>


        <li class="nav-item">
            <a class="nav-link" href="/logout">Logout</a>
        </li>


    </ul>

    <div class="form-inline search-div dropdown">
        <input type="search" name="param" class="form-control mr-sm-2" placeholder="Search">
        <button class="search-button btn btn-outline-success" type="button" onclick ="search()" data-toggle="dropdown">Search</button>
        <div class="dropdown-menu search-menu w-100">
        </div>
    </div>
</nav>
<div class="card book-share-card">
    <div class="card-body">
        <div class="row">
            <div class="col-sm-12 form-group">
                <div class="logo-div text-right" style="margin-top: 20px;">
                    <a href="/admin/topUsers" class="btn btn-primary btn-block">Top Contributors of the System</a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 form-group">
                <div class="logo-div text-right" style="margin-top: 20px;">
                    <a href="/admin/topSharedBooks" class="btn btn-primary btn-block">Top Shared Books</a>
                </div>
            </div>
        </div>
   </div>
</div>
</body>
</html>
