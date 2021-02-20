<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Profile Information</title>
    <link rel="stylesheet" href="/../css/Profile.css">
    <script src="/../js/validation.js"> </script>
     <script src="/../js/jQuery-3.4.1.js"></script>

</head>
<body>
    <h1 style="text-align:left;">Bibliophily Connect</h1>
 <%@include file="bootstrapFiles.jsp"%>

<%@include file="header.jsp"%>
<h4 class="text-center">${user.fullName}'s Information</h4>
<div class="card share-card">
    <div class="card-body">
        <div class="row">
            <div class="col-sm-4 form-group">
                <label>Full Name</label>
            </div>
            <div class="col-sm-8 form-group">
                <c:out value="${user.fullName}"/>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-4 form-group">
                <label>Username</label>
            </div>
            <div class="col-sm-8 form-group">
                <c:out value="${user.userName}"/>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-4 form-group">
                <label>Email Address</label>
            </div>
            <div class="col-sm-8 form-group">
                <c:out value="${user.userMail}"/>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-4 form-group">
                <label>Phone</label>
            </div>
            <div class="col-sm-8 form-group">
                <c:out value="${user.userContact}"/>
            </div>
        </div>
    </div>

    <c:url value="editProfile" var = "editProfile">
        <c:param name="userId" value="${user.id}"/>
    </c:url>


</div>

<div class="logo-div text-right" style="margin-top: 20px;" >
 <input type="button"  class="button" value="Update Information" onclick="window.location.href='${editProfile}'">
</div>

</div>

</body>
</html>
