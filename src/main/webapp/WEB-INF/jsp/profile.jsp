<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Profile Information</title>
    <link rel="stylesheet" href="/../css/Profile.css">
</head>
<body>
    <h1 style="text-align:left;">Bibliophily Connect</h1>
<div style="float:right;" class="profile">
 <input type="button" class="button1" value="Logout" onclick="window.location.href='#'">
</div>
<section>
  <h1>User Details</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>Full Name</th>
          <th>User Name</th>
          <th>Email Address</th>
          <th>Phone Number</th>
          <th>User Type</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr>
          <td> <c:out value="${user.fullName}"/></td>
          <td><c:out value="${user.userName}"/> </td>
          <td> <c:out value="${user.userMail}"/></td>
          <td><c:out value="${user.userContact}"/></td>
          <td> <c:out value="${user.userType}"/></td>
        </tr>
         </tbody>
            </table>
          </div>
        </section>

    <c:url value="editProfile" var = "editProfile">
        <c:param name="userId" value="${user.id}"/>
    </c:url>
</div>

<div class="profile" >
 <input type="button"  class="button" value="Update Information" onclick="window.location.href='${editProfile}'">
</div>
</body>
</html>
