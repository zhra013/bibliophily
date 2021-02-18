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
 <input type="button" class="button" value="Logout" onclick="window.location.href='#'">
</div>
<form:form modelAttribute="user" cssClass="box" method="post" enctype="multipart/form-data">
    <h1>Update User Details</h1>
  <form:input path="fullName" size = "50" maxlength="50" cssClass="text" placeholder="Full-Name" required="required"/>
                      <form:errors path="fullName" />
  <form:input path="userName" size = "20" maxlength="20" cssClass="text" placeholder="UserName" required="required"/>
                      <form:errors path="userName" />
   <form:password path="userPassword" size = "20" maxlength="20" cssClass="password" placeholder="Password"/>
                                          <form:errors path="userPassword" />
        <form:input type="email"  path="userMail" size = "35" maxlength="35"  cssClass="email" placeholder="E-mail" required="required"/>
                           <form:errors path="userMail" />
    <form:input type="number" path="userContact" size = "20" maxlength="20" cssClass="number" placeholder="Contact number" required="required"/>
                       <form:errors path="userContact"/>

          <input type="hidden" name="userType" value="${user.userType}">
                      <input type="hidden" name="userId" value="${user.id}">
    <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block" onclick="isEdit()" value="Save"/>

    </form:form>
</body>
</html>
