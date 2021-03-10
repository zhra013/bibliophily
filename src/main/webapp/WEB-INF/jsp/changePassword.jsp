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
                    <form:label path="fullName">Password</form:label>
                </div>
                <div class="col-sm-8  form-group">
                    <form:input type="password" path="userPassword" size = "20" maxlength="20" cssClass="password" placeholder="Change Password" required="required"/>
                                           <form:errors path="userPassword"/>
                              <input type="hidden" name="userType" value="${user.userType}">
                                          <input type="hidden" name="userId" value="${user.id}">
                    </br>
                    <input type="checkbox" id="showpass" onclick="showPassword()"> Show Password
                </div>
            </div>

            <input type="hidden" name="userType" value="${user.userType}">
            <input type="hidden" name="userId" value="${user.id}">
        </div>
    </div>

   <div class="logo-div text-right" style="margin-top: 20px;" >
   <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block"  value="Update Password"/>
   </div>

</form:form>
<script>
function showPassword(){
  var x = document.getElementById("userPassword");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
</script>
</body>
</html>