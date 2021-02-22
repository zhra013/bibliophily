<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Post Feed</title>
     <link rel="stylesheet" href="/../css/Profile.css">
      <link rel="stylesheet" href="/../css/table.css">
        <script src="/../js/validation.js"> </script>
         <script src="/../js/jQuery-3.4.1.js"></script>
    <%@include file="bootstrapFiles.jsp" %>
</head>
<body>
<h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>

<h4 class="text-center">Post List</h4>

<c:choose>
    <c:when test="${empty posts}">
        <div class="alert alert-info table-div text-center">
            Currently, no post is available.
        </div>
    </c:when>
    <c:otherwise>
        <div class="table-responsive table-div">
            <table id="myTable" class="table table-hover"  style="width:100%; color: purple;">
                <thead>
                <tr>
                    <th>Post Type</th>
                    <th>Title</th>
                    <th>Edition</th>
                    <th>Author/Publisher</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="post" items="${posts}">
                    <tr>
                        <td>${post.postType}</td>
                        <td>${post.title}</td>
                        <td>${post.edition}</td>
                        <td>${post.author}</td>
                        <td>
                                    <c:url value="/post/detail" var="postDetail">
                                        <c:param name="postId" value="${post.id}"/>
                                    </c:url>

                                     <div class="btn-group">
                                    <a href="${postDetail}" class="btn btn-outline-primary">Details</a>
                                      </div>

                                    <c:url value="/post/review" var="review">
                                          <c:param name="postId" value="${post.id}"/>
                                    </c:url>

                                    <div class="btn-group">
                                        <a href="${review}" class="btn btn-outline-dark">Review</a>
                                    </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>
 <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script>
$(document).ready( function () {
    $('#myTable').DataTable();
} );
</script>

</body>
</html>
