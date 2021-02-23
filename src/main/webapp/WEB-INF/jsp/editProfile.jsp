<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Profile Information Update</title>
    <link rel="stylesheet" href="/../css/Profile.css">
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
                    <form:label path="fullName">Full name</form:label>
                </div>
                <div class="col-sm-8  form-group">
                    <form:input path="fullName" size = "50" maxlength="50" cssClass="text" placeholder="Full-Name" required="required"/>
                    <form:errors path="fullName" />
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userName">Username</form:label>
                </div>
                <div class="col-sm-8 form-group">
                  <form:input path="userName" size = "20" maxlength="20" cssClass="text" placeholder="User Name" required="required" onblur="validateUserName()"/>
                    <form:errors path="userName" />
                     <div id="userName_error" class="d-none invalid-feedback"></div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userMail">Email Address</form:label>
                </div>
                <div class="col-sm-8 form-group">
                   <form:input type="email"  path="userMail" size = "35" maxlength="35"  cssClass="email" placeholder="E-mail" required="required" onblur="validateEmail()"/>
                   <form:errors path="userMail" />
                    <div id="userMail_error" class="d-none invalid-feedback"></div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 form-group">
                    <form:label path="userContact">Phone</form:label>
                </div>
               <form:input type="text" path="userContact" size = "20" maxlength="20" cssClass="number" placeholder="Contact Number" required="required" onblur="validateContact()"/>
                                       <form:errors path="userContact"/>
                  <div id="userContact_error" class="d-none invalid-feedback"></div>
            </div>

            <input type="hidden" name="userType" value="${user.userType}">
            <input type="hidden" name="userId" value="${user.id}">
        </div>
    </div>

   <div class="logo-div text-right" style="margin-top: 20px;" >
        <input type="submit" name="button" id="submitBtn" class="btn btn-lg btn-primary btn-block"  value="Update"/>
   </div>
</form:form>
<script>
var currentEmail, currentUserContact, currentUserName;
$(document).ready(function() {
    currentEmail = document.getElementById("userMail").value;
    currentUserContact = document.getElementById("userContact").value;
    currentUserName = document.getElementById("userName").value;
});

function validateUserName(){
    var userName = document.getElementById("userName").value;
     if(typeof userName !== 'undefined' && userName !== currentUserName){
    $.ajax({
        type: 'get',
        url: 'validate',
        data: { userName: userName, action: "validateUserName" },
        success: function (data) {
            if(data === "UserName Already Exist"){
                $("#userName_error").removeClass("d-none");
                document.getElementById("userName_error").innerHTML = data;
                document.getElementById("userName_error").style.color = "red";
                document.getElementById("userName_error").style.display = "block";
                $("#userName_error").addClass("is-invalid");
                document.getElementById("submitBtn").disabled = true;
                document.getElementById("userName_error").style.paddingBottom = "10px";
            }
            else if(data === "Allow"){
                document.getElementById("submitBtn").disabled = false;
                $("#userName_error").removeClass("is-invalid");
                document.getElementById("userName_error").innerHTML = "";
                document.getElementById("userName_error").style.display = "none";
                $("#userName_error").addClass("d-none");
                document.getElementById("userName_error").style.paddingBottom = "0px";
            }
        }
    })
    }else{
                   document.getElementById("submitBtn").disabled = false;
                    $("#userName_error").removeClass("is-invalid");
                    document.getElementById("userName_error").innerHTML = "";
                    document.getElementById("userName_error").style.display = "none";
                    $("#userName_error").addClass("d-none");
                    document.getElementById("userName_error").style.paddingBottom = "0px";
    }
}

function validateEmail(){
    var userMail = document.getElementById("userMail").value;
    var atposition=userMail.indexOf("@");
    var dotposition=userMail.lastIndexOf(".");
    console.log(userMail !== currentEmail);
    if (atposition<1 || dotposition<atposition+2 || dotposition+2>=userMail.length){
        $("#userMail_error").removeClass("d-none");
        document.getElementById("userMail_error").innerHTML = "Please Enter Valid Email Address. E.G. abc@gmail.com";
        document.getElementById("userMail_error").style.color = "red";
        document.getElementById("userMail_error").style.display = "block";
        $("#userMail_error").addClass("is-invalid");
        document.getElementById("submitBtn").disabled = true;
    }
    if(typeof userMail !== 'undefined' && userMail !== currentEmail){
        $.ajax({
            type: 'get',
            url: 'validate',
            data: { userMail: userMail, action: "validateEmail" },
            success: function (data) {
                if(data === "User Already Exist"){
                    $("#userMail_error").removeClass("d-none");
                    document.getElementById("userMail_error").innerHTML = data;
                    document.getElementById("userMail_error").style.color = "red";
                    document.getElementById("userMail_error").style.display = "block";
                    $("#userMail_error").addClass("is-invalid");

                    document.getElementById("submitBtn").disabled = true;
                }
                else if(data === "Allow"){
                    document.getElementById("submitBtn").disabled = false;
                    $("#userMail_error").removeClass("is-invalid");
                    document.getElementById("userMail_error").innerHTML = "";
                    document.getElementById("userMail_error").style.display = "none";
                    $("#userMail_error").addClass("d-none");
                }
            }
        })
    }
    else{
        document.getElementById("submitBtn").disabled = false;
        $("#userMail_error").removeClass("is-invalid");
        document.getElementById("userMail_error").innerHTML = "";
        document.getElementById("userMail_error").style.display = "none";
        $("#userMail_error").addClass("d-none");
    }
}

function validateContact(){
    var userContact = document.getElementById("userContact").value;
    var re = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;

    if(!re.test(userContact)){
        $("#userContact_error").removeClass("d-none");
        document.getElementById("userContact_error").innerHTML = "Enter Valid Contact Number";
        document.getElementById("userContact_error").style.color = "red";
        document.getElementById("userContact_error").style.display = "block";
        $("#userContact_error").addClass("is-invalid");
        document.getElementById("submitBtn").disabled = true;
    }
    if(typeof userContact !== 'undefined' && currentUserContact !== userContact){
    console.log("Inside If");
        $.ajax({
            type: 'get',
            url: 'validate',
            data: { userContact: userContact, action: "validateContact" },
            success: function (data) {
                if(data === "Contact Number Already Exist"){
                    $("#userContact_error").removeClass("d-none");
                    document.getElementById("userContact_error").innerHTML = data;
                    document.getElementById("userContact_error").style.color = "red";
                    document.getElementById("userContact_error").style.display = "block";
                    $("#userContact_error").addClass("is-invalid");
                    document.getElementById("submitBtn").disabled = true;
                }
                else if(data === "Allow"){
                    document.getElementById("submitBtn").disabled = false;
                    $("#userContact_error").removeClass("is-invalid");
                    document.getElementById("userContact_error").innerHTML = "";
                    document.getElementById("userContact_error").style.display = "none";
                    $("#userContact_error").addClass("d-none");
                }
            }
    })
    }
    else{
        document.getElementById("submitBtn").disabled = false;
        $("#userContact_error").removeClass("is-invalid");
        document.getElementById("userContact_error").innerHTML = "";
        document.getElementById("userContact_error").style.display = "none";
        $("#userContact_error").addClass("d-none");
    }
}


</script>
</body>
</html>