<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Profile Information Update</title>
 <link rel="stylesheet" href="/../css/Profile.css">
 <script src="/../js/validation.js"> </script>
  <script src="/../js/jQuery-3.4.1.js"></script>

    <%@include file="bootstrapFiles.jsp" %>
</head>
<body>
 <h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>

<h4 class="text-center">${user.fullName}'s Profile Information</h4>

<form:form modelAttribute="user" method="post" enctype="multipart/form-data">
    <div class="card share-card">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="fullName">Full name</form:label>
                </div>
                <div class="col-sm-8  form-group">
                    <form:input path="fullName" size = "50" maxlength="50" cssClass="text" placeholder="Full-Name" required="required"/>
                    <form:errors path="fullName" />
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userName">Username</form:label>
                </div>
                <div class="col-sm-8 form-group">
                  <form:input path="userName" size = "20" maxlength="20" cssClass="text" placeholder="User Name" required="required" onblur="validateUserName()"/>
                    <form:errors path="userName" />
                     <div id="userName_error" class="d-none invalid-feedback"></div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userMail">Email Address</form:label>
                </div>
                <div class="col-sm-8 form-group">
                   <form:input type="email"  path="userMail" size = "35" maxlength="35"  cssClass="email" placeholder="E-mail" required="required" onblur="validateEmail()"/>
                   <form:errors path="userMail" />
                    <div id="userMail_error" class="d-none invalid-feedback"></div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userContact">Phone</form:label>
                </div>
               <form:input type="text" path="userContact" size = "20" maxlength="20" cssClass="number" placeholder="Contact Number" required="required" onblur="validateContact()"/>
                                       <form:errors path="userContact"/>
                  <div id="userContact_error" class="d-none invalid-feedback"></div>
            </div>

            <input type="hidden" name="userType" value="${user.userType}">
            <input type="hidden" name="userId" value="${user.id}">
        </div>
    </div>

   <div class="logo-div text-right" style="margin-top: 20px;" >
        <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block"  value="Update"/>
   </div>

</form:form>
</body>
</html>