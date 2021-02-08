<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sign Up</title>
</head>

<body class="container" style="padding-top: 10%;">

<div class="card book-share-card">
    <div class="card-body">
        <form:form modelAttribute="user" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="fullName">Full name</form:label>
                </div>
                <div class="col-sm-8  form-group">
                    <form:input path="fullName" size = "50" maxlength="50" cssClass="form-control" placeholder="Full-Name" required="required"/>
                    <form:errors path="fullName" />
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userName">Username</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <form:input path="userName" size = "20" maxlength="20" cssClass="form-control" placeholder="UserName" required="required"/>
                    <form:errors path="userName" />
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4  form-group">
                    <form:label path="userPassword">Password</form:label>
                </div>
                <div class="col-sm-8  form-group">
                    <form:password path="userPassword" size = "20" maxlength="20" cssClass="form-control" placeholder="Password" required="required"/>
                    <form:errors path="userPassword" />
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userMail">Email Address</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <form:input type="email"  path="userMail" size = "35" maxlength="35"  cssClass="form-control" placeholder="E-mail" required="required"/>
                    <form:errors path="userMail" />
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userContact">Phone</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <form:input type="number" path="userContact" size = "20" maxlength="20" cssClass="form-control" placeholder="Contact number" required="required"/>
                    <form:errors path="userContact"/>
                </div>
            </div>
            <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block" value="Sign Up"/>
        </form:form>
    </div>
</div>
</body>
</html>
