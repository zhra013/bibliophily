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
      <h2>Login Bibliophily Connect</h2>
       <form:form modelAttribute="user" method="post" enctype="multipart/form-data">
      <div class="textbook">
          <i class="fas fa-user"></i>
        <form:input cssClass="input" path="userMail" size="15" maxlength="15" placeholder="User-email" required="required"/>
      </div>
      <input type="submit" class="submit" value="send mail">
           <input type="button" class="button" value="Sign Up" onclick="window.location.href='/forgotPassword'">
     </div>
   </form:form>
  </body>
</html>
