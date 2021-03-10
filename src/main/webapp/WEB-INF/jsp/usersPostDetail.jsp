<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Book Information</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     <link rel="stylesheet" href="/../css/Profile.css">
            <script src="/../js/validation.js"> </script>
             <script src="/../js/jQuery-3.4.1.js"></script>
    <%@include file="bootstrapFiles.jsp" %>
</head>
<body>
<h1 style="text-align:left;">Bibliophily Connect</h1>
<h4 class="text-center">${post.title}'s Information</h4>

<div class="card detail-page detail-card">
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

            </div>
        </div>
    </div>
</div>

</body>
</html>
