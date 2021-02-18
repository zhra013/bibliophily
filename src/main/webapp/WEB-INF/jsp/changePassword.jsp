<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Profile Information Update</title>
    <link rel="stylesheet" href="/../css/signUp.css">

</head>
<body>
<h1 style="text-align:left;">Bibliophily Connect</h1>
<div style="float:right;" class="">
 <input type="button" class="button" value="Logout" onclick="window.location.href='/logout'">
</div>
<form:form modelAttribute="user" cssClass="box" method="post" enctype="multipart/form-data">
    <h1>Update User Password</h1>
    <form:input type="password" path="userPassword" size = "20" maxlength="20" cssClass="password" placeholder="Change Password" required="required"/>
                       <form:errors path="userPassword"/>
          <input type="hidden" name="userType" value="${user.userType}">
                      <input type="hidden" name="userId" value="${user.id}">
    <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block" onclick="window.location.href='/changePassword'" value="Update"/>
    </form:form>
</body>
</html>
