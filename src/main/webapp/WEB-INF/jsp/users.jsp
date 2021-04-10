<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>User List</title>

     <link rel="stylesheet" href="/../css/Profile.css">

       <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <script src="/../js/validation.js"> </script>
         <script src="/../js/jQuery-3.4.1.js"></script>
    <%@include file="bootstrapFiles.jsp" %>
</head>
 <link rel="stylesheet" href="/../css/table.css">
<body>
<h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>

<c:if test="${friend == 'no'}">
<h4 class="text-center">Users</h4>
 </c:if>

 <c:if test="${friend == 'yes'}">
 <h4 class="text-center">Friends</h4>
  </c:if>
<c:choose>
    <c:when test="${empty usersList}">
     <c:if test="${friend == 'no'}">
        <div class="alert alert-info table-div text-center">
            Currently, no user is available in this system.
        </div>
      </c:if>
       <c:if test="${friend == 'yes'}">
              <div class="alert alert-info table-div text-center">
                  Currently, no friends found, make friends.
              </div>
            </c:if>
    </c:when>
    <c:otherwise>


        <div class="table-responsive table-div">
            <table id="example" style="width:100%" class="table table-hover"  style="color: purple;">
                <thead>
                <tr>
                    <th>Full Name</th>
                    <th>User Name</th>
                    <th>Mail</th>
                    <th>Contact</th>
                    <th>Action</th>
                    <th>Friendship Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${usersList}">
                    <tr>
                        <td>${user.fullName}</td>
                        <td>${user.userName}</td>
                        <td>${user.userMail}</td>
                        <td>${user.userContact}</td>
                        <td>
                            <c:url value="/post/list/page" var = "view">
                                <c:param name="userId" value="${user.id}"/>
                                <c:param name="page" value="0"/>
                                <c:param name="excludeOwner" value="false"/>
                                <c:param name="users" value="true"/>
                            </c:url>
                            <div class="btn-group">
                                <a href="${view}" class="btn btn-outline-primary">View Posts</a>
                            </div>
                        </td>
                        <td>
                            <div class="btn-group">
                                <c:if test="${user.friendStatus == 'Allow'}">
                                     <button onclick="window.location.href='/friend/${sessionScope.currentUser.id}/sendRequest/${user.id}'" type="button" class="btn btn-outline-primary" title="Add Friend"><i class="fas fa-user-plus"></i></button>
                                </c:if>
                                <c:if test="${user.friendStatus == 'Friends'}">
                                    <button type="button" class="btn btn-outline-primary" title="Friends" disabled><i class="fas fa-user-friends"></i></button>
                                    &nbsp;
                                    <button type="button" class="btn btn-outline-primary" title="UnFriend" onclick="RemoveFriend(${sessionScope.currentUser.id}, ${user.id})"><i class="fas fa-user-times"></i></button>
                                </c:if>
                                <c:if test="${user.friendStatus == 'Requested'}">
                                    <button type="button" class="btn btn-outline-primary" title="Request Pending" disabled><i class="far fa-user-clock"></i></button>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
 <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
  <script>
$(document).ready(function() {
    $('#example').DataTable();
} );

function RemoveFriend(sessionuser, friendid){
    if(confirm("Are you sure you want to unfriend?")){
    $.ajax({
         type: 'get',
         url: '/friend/'+friendid+'/user/'+sessionuser,
         data: { },
         success: function (data) {
                location.reload();
            }
     });
}
}
</script>

