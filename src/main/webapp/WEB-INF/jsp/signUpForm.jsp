<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="/../css/signUp.css">
    <link rel="shortcut icon" href="/../img/favicon.png" type="image/x-icon">
    <script src="/../js/validation.js"> </script>
  </head>
  <body>
<form:form modelAttribute="user" cssClass="box" method="post" enctype="multipart/form-data" autocomplete="off">
    <h1>SIGN UP</h1>

  <form:input path="fullName" size = "50" maxlength="50" cssClass="text" placeholder="Full-Name" required="required"/>
                      <form:errors path="fullName" />
  <form:input path="userName" size = "20" maxlength="20" cssClass="text" placeholder="User Name" required="required" onblur="validateUserName()"/>
                      <form:errors path="userName" />
  <div id="userName_error" class="d-none invalid-feedback"></div>
  <form:password path="userPassword" size = "20" maxlength="20" cssClass="password" placeholder="Password" required="required"/>
                                          <form:errors path="userPassword" />
  <form:input type="email"  path="userMail" size = "35" maxlength="35"  cssClass="email" placeholder="E-mail" required="required" onblur="validateEmail()"/>
                           <form:errors path="userMail" />
  <div id="userMail_error" class="d-none invalid-feedback"></div>
  <form:input type="text" path="userContact" size = "20" maxlength="20" cssClass="number" placeholder="Contact Number" required="required" onblur="validateContact()"/>
                       <form:errors path="userContact"/>
  <div id="userContact_error" class="d-none invalid-feedback"></div>
  <input type="submit" name="button" id="submitBtn" class="btn btn-lg btn-primary btn-block" value="Sign Up"/>

    </form:form>

    <script src="/../js/jQuery-3.4.1.js"> </script>
  </body>
</html>

