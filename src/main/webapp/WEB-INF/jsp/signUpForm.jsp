<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formm" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <title>Login page</title>
    <link rel="stylesheet" href="/../css/signUp.css">
  </head>
  <body>
<form:form modelAttribute="user" cssClass="box" method="post" enctype="multipart/form-data">
    <h1>SIGN UP</h1>
  <form:input path="fullName" size = "50" maxlength="50" cssClass="text" placeholder="Full-Name" required="required"/>
                      <form:errors path="fullName" />
  <form:input path="userName" size = "20" maxlength="20" cssClass="text" placeholder="UserName" required="required"/>
                      <form:errors path="userName" />
   <form:password path="userPassword" size = "20" maxlength="20" cssClass="password" placeholder="Password" required="required"/>
                                          <form:errors path="userPassword" />
        <form:input type="email"  path="userMail" size = "35" maxlength="35" placeholder="E-mail" required="required"/>
                           <form:errors path="userMail" />
    <form:input type="text"  path="userContact" size = "20" maxlength="20" cssClass="number" placeholder="Contact number" required="required"/>
                       <form:errors path="userContact"/>
    <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block" value="Sign Up"/>

    </form:form>

  </body>
</html>

