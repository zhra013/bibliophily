<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>LOGIN PAGE</title>
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/../css/Style.css">
  </head>
  <body>
    <div class="login">
      <h2>Forgot Password</h2>
       <form:form modelAttribute="user" method="post" enctype="multipart/form-data">
      <div class="textbook">
          <i class="fas fa-user"></i>
      <form:input type="email" path="userMail" size = "35" maxlength="35"  cssClass="email" placeholder="E-mail" required="required" onblur="validateForgotPasswordEmail()"/>
                                 <form:errors path="userMail" />
        <div id="userMail_error" class="d-none invalid-feedback"></div>
      </div>
      <div id="userContact_error" class="d-none invalid-feedback"></div>
        <input type="submit" name="button" id="submitBtn" class="btn btn-lg btn-primary btn-block" value="Send Mail"/>
     </div>
   </form:form>
  </body>
</html>
