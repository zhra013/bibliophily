<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Report</title>
    <script src="/../js/jQuery-3.4.1.js"></script>
    <%@include file="bootstrapFiles.jsp"%>
    <link rel="stylesheet" href="/../css/table.css">
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light">

     <c:url value="/changePassword" var = "changePassword">
        <c:param name="userId" value="${sessionScope.currentUser.id}"/>
     </c:url>


    <ul class="navbar-nav mr-auto">

        <div class="dropdown">
          <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            My Profile
          </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="nav-link" href="/profile">Profile</a>
                <a class="nav-link" href="${changePassword}">Change Password</a>
            </div>
        </div>


        <li class="nav-item">
            <a class="nav-link" href="/logout">Logout</a>
        </li>


    </ul>
</nav>
<div class="card book-share-card">
    <div class="card-body">
        <c:choose>
            <c:when test="${empty topContributor}">
                <div class="alert alert-info table-div text-center">
                    Currently, no user is available in this system.
                </div>
            </c:when>
            <c:otherwise>

                <div class="logo-div text-right" style="margin-top: 20px;" >
                   <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block"  onclick="showTopContributors()" value="Top Active User"/>
                </div>
                <div class="logo-div text-right" style="margin-top: 20px;" ></div>

                <div class="table-responsive table-div" id="TopContributor" style="display:none;">
                    <table id="topContributor" style="width:100%" class="table table-hover"  style="color: purple;">
                        <thead>
                        <tr>
                            <th>User Name</th>
                            <th>Mail</th>
                            <th>No. of Post</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${topContributor}">
                            <tr>
                                <td>${user.userName}</td>
                                <td>${user.userMail}</td>
                                <td>${user.totalPost}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>


        <c:choose>
            <c:when test="${empty postContributionList}">
                <div class="alert alert-info table-div text-center">
                    Currently, no user is available in this system.
                </div>
            </c:when>
            <c:otherwise>

                <div class="logo-div text-right" style="margin-top: 20px;" >
                   <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block" onclick="showTopSharedPost()"  value="Top Shared Post"/>
                </div>
                <div class="logo-div text-right" style="margin-top: 20px;" ></div>
                <div class="table-responsive table-div" id="TopSharedPost" style="display:none;">
                    <table id="postContributionList" style="width:100%" class="table table-hover"  style="color: purple;">
                        <thead>
                        <tr>
                            <th>Uploader User Name</th>
                            <th>Post Type</th>
                            <th>Post Title</th>
                            <th>Post Author</th>
                            <th>No. of times Shared</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="post" items="${postContributionList}">
                            <tr>
                                <td>${post.uploader.userName}</td>
                                <td>${post.postType}</td>
                                <td>${post.author}</td>
                                <td>${post.title}</td>
                                <td>${post.shareCounter}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>


<c:choose>
            <c:when test="${empty postRatingList}">
                <div class="alert alert-info table-div text-center">
                    Currently, no user is available in this system.
                </div>
            </c:when>
            <c:otherwise>

                <div class="logo-div text-right" style="margin-top: 20px;" >
                   <input type="submit" name="button"  class="btn btn-lg btn-primary btn-block" onclick="showTopRatedPost()"  value="Top Rated Post"/>
                </div>
                <div class="logo-div text-right" style="margin-top: 20px;" ></div>
                <div class="table-responsive table-div" id="TopRatedPost" style="display:none;">
                    <table id="postContributionList" style="width:100%" class="table table-hover"  style="color: purple;">
                        <thead>
                        <tr>
                            <th>Uploader User Name</th>
                            <th>Post Type</th>
                            <th>Post Title</th>
                            <th>Post Author</th>
                            <th>Rating</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="post" items="${postRatingList}">
                            <tr>
                                <td>${post.uploader.userName}</td>
                                <td>${post.postType}</td>
                                <td>${post.author}</td>
                                <td>${post.title}</td>
                                <td>${post.rating}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>


   </div>
</div>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
  <script>
$(document).ready(function() {
    $('#topContributor').DataTable();
    $('#postContributionList').DataTable();
} );
function showTopContributors(){
    var style = document.getElementById("TopContributor").style.display;
    if(style == "none"){
        document.getElementById("TopContributor").style.display = "block";
    }
    else if(style == "block"){
        document.getElementById("TopContributor").style.display = "none";
    }

}

function showTopSharedPost(){
    var style = document.getElementById("TopSharedPost").style.display;
        if(style == "none"){
            document.getElementById("TopSharedPost").style.display = "block";
        }
        else if(style == "block"){
            document.getElementById("TopSharedPost").style.display = "none";
        }
}

function showTopRatedPost(){
    var style = document.getElementById("TopRatedPost").style.display;
        if(style == "none"){
            document.getElementById("TopRatedPost").style.display = "block";
        }
        else if(style == "block"){
            document.getElementById("TopRatedPost").style.display = "none";
        }
}
</script>
</body>
</html>
