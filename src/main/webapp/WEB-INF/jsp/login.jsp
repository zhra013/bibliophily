<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>LOGIN PAGE</title>
    <!-- <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css"> -->
    <link rel="stylesheet" href="/../css/Style.css">
  </head>
  <body>
    <div class="login">
      <h2>Login Bibliophily Connect</h2>
       <form:form modelAttribute="user" method="post" enctype="multipart/form-data">
      <div class="textbook">
          <i class="fas fa-user"></i>
        <form:input cssClass="input" path="userName" size="15" maxlength="15" placeholder="Username" onblur="checkUsername()" required="required"/>
      </div>
      <div class="textbook">
        <i class="fas fa-lock"></i>
         <form:password cssClass="password" path="userPassword" size="15" placeholder="Password" required="required" />
        <form:errors path="userPassword"/>
      </div>
   <div class="textbook">
    <i class="fas fa-user"></i>
    <form:select cssClass="dropbtn" path="userType" items="${userType}"/>

   </div>
      <input type="submit" class="submit" value="Sign In">
           <input type="button" class="button" value="Sign Up" onclick="window.location.href='/signUp'"/>
           <a href="/forgotPassword"> forgot password</a>
     </div>
   </form:form>

   <script src="/../js/jQuery-3.4.1.js"> </script>

  </body>
</html>

