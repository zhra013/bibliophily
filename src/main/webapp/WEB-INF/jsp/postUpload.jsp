<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Uploading post</title>
 <link rel="stylesheet" href="/../css/Profile.css">
    <script src="/../js/validation.js"> </script>
     <script src="/../js/jQuery-3.4.1.js"></script>
    <%@include file="bootstrapFiles.jsp" %>
</head>

<body>
 <h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>

<h4 class="text-center">Post Upload</h4>
<form:form modelAttribute="post" method="post" enctype="multipart/form-data">
    <div class="card upload-page upload-card">
        <div class="card-body">

         <div class="row">
                        <div class="col-sm-4 form-group">
                            <form:label path="postType">Post Type</form:label>
                        </div>
                        <div class="col-sm-8 form-group">
                            <form:select path="postType" items="${postTypeList}" cssClass="form-control" placeholder="Category" required="required"/>
                        </div>
                    </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="title">Title</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <form:input path="title" size="100" maxlength="100" cssClass="form-control" placeholder="Title" required="required"/>
                    <form:errors path="title"/>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="edition">Edition</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <form:input path="edition" size="20" maxlength="20" cssClass="form-control" placeholder="Edition" required="required"/>
                    <form:errors path="edition"/>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="author">Author / publisher</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <form:input path="author" size="20" maxlength="20" cssClass="form-control" placeholder="Author/Publisher" required="required"/>
                    <form:errors path="author"/>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="blog">Write Something</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <form:input path="blog" size="20" maxlength="20" cssClass="form-control" placeholder="Write Something" required="required"/>
                    <form:errors path="blog"/>
                </div>
            </div>



            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="uploadedCoverPhoto" for="coverPhotoOfBook">Cover Photo</form:label>
                </div>
                <div class="col-sm-8 form-group">
                    <input id="coverPhotoOfBook" name="uploadedCoverPhoto" type="file" cssClass="form-control" placeholder="Cover Photo of Post"/>
                </div>
            </div>



            <input type="hidden" name="uploader.id" value="${sessionScope.currentUser.id}"/>
        </div>
    </div>

    <div class="upload-page text-right" style="margin-top: 20px;">
        <input type="submit" name="button" class="btn btn-lg btn-primary" value="Upload"/>
    </div>
</form:form>
</body>
</html>
