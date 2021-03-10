<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Review</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/../css/Profile.css">
                <script src="/../js/validation.js"> </script>
                 <script src="/../js/jQuery-3.4.1.js"></script>
    <%@include file="bootstrapFiles.jsp" %>
</head>
<body>
<h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>
<h2 class="text-center">${post.title}</h2>

<div class="card book-detail-page book-detail-card">
    <div class="card-body">
        <div class="row">
            <div class="col-sm-4">
                <img src="/post/coverPhoto?postId=${post.id}" class="img-thumbnail" width="100%">
            </div>
            <div class="col-sm-8">
                <div class="row">
                                               <div class="col-sm-4 form-group">
                                                   <label>Post Type</label>
                                               </div>
                                               <div class="col-sm-8 form-group">
                                                   <c:out value="${post.postType}"/>
                                               </div>
                                           </div>

                               <div class="row">
                                   <div class="col-sm-4 form-group">
                                       <label>Title</label>
                                   </div>
                                   <div class="col-sm-8 form-group">
                                       <c:out value="${post.title}"/>
                                   </div>
                               </div>

                               <div class="row">
                                   <div class="col-sm-4 form-group">
                                       <label>Edition</label>
                                   </div>
                                   <div class="col-sm-8 form-group">
                                       <c:out value="${post.edition}"/>
                                   </div>
                               </div>

                               <div class="row">
                                   <div class="col-sm-4 form-group">
                                       <label>Author/Publisher</label>
                                   </div>
                                   <div class="col-sm-8 form-group">
                                       <c:out value="${post.author}"/>
                                   </div>
                               </div>
                     <div class="row">
                    <div class="col-sm-4 form-group">
                        <h6>Rating</h6>
                    </div>
                    <div class="col-sm-8 form-group">
                        <c:forEach begin="1" end="5" varStatus="loop">
                            <span class="fa fa-star ${loop.index <= rating ? 'checked' : ''}"></span>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="myDIV">
<h2 class="text-center">Review</h2>
<form:form modelAttribute="postReview" method="post" enctype="multipart/form-data">
    <div class="card detail-page book-detail-card">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-2 form-group">
                    <label>Rating</label>
                </div>
                <div class="col-sm-10 form-group">
                    <form:select path="rating" items="${ratings}" cssClass="form-control"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2 form-group">
                    <label>Review</label>
                </div>
                <div class="col-sm-10 form-group">
                    <form:textarea path="comment" maxlength="200" type="text" cssClass="form-control" placeholder="Write a comment. . ." rows="6"/>
                </div>
            </div>

            <input type="hidden" name="reviewer.id" value="${sessionScope.currentUser.id}"/>
            <input type="hidden" name="post.id" value="${post.id}"/>
        </div>
    </div>


    <div class="detail-page text-right" style="margin-top: 20px;">
        <input type="submit" name="button" class="btn btn-lg btn-primary" value="Submit"/>
    </div>
</form:form>
</div>

<div class=" card review-block">
    <c:forEach var="review" items="${reviewsList}">
        <div class="space">
            <span><h5>${review.reviewer.fullName}</h5></span>
            <div>
                <c:forEach begin="1" end="5" varStatus="loop">
                    <span class="fa fa-star ${loop.index <= review.rating ? 'checked' : ''}"></span>
                </c:forEach>
            </div>
        </div>
        <div class = "well">
            <c:out value="${review.comment}"/>
        </div>
    </c:forEach>
</div>
</body>

<script>
$(document).ready(function() {
             var x = document.getElementById("myDIV");
             $.ajax({
                    type: 'get',
                    url: '/validate/review',
                    data: { userId: "${sessionScope.currentUser.id}", postId: "${post.id}" },
                    success: function (data) {
                        if(data == "Allow"){
                            document.getElementById("myDIV").style.display = "block";
                        }
                        else {
                          document.getElementById("myDIV").style.display = "none";
                        }
                    }
                })

});
</script>
</html>
