<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html lang="en">

<head>
	<title>Bibliophily Connect Entry</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content="Triple Forms Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
	<script src="/../js/jQuery-3.4.1.js"> </script>
    <script src="/../js/validation.js"> </script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script src="/../js/crypto-js/core-min.js"> </script>
<script src="/../js/crypto-js/enc-utf16-min.js"> </script>
<script src="/../js/crypto-js/enc-base64-min.js"> </script>
<script src="/../js/crypto-js/aes.js"> </script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>

	<!-- Meta tag Keywords -->

	<!-- css files -->
	<link rel="stylesheet" href="/../css/entry.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link href="path/to/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //css files -->

	<!-- web-fonts -->
	<link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext"
	 rel="stylesheet">
	<!-- //web-fonts -->
</head>

<body>
	<div class="main-bg">
		<!-- title -->
		<h1>Bibliophily Connect</h1>
		<!-- //title -->
		<div class="sub-main-w3">

			<!-- vertical tabs -->
			<div class="vertical-tab">
				<div id="section1" class="section-w3ls">
					<input type="radio" name="sections" id="option1" checked>
					<label for="option1" class="icon-left-w3pvt"><span class="fa fa-user-circle" aria-hidden="true"></span>Login</label>
					<article>
						<form:form modelAttribute="user" method="post" enctype="multipart/form-data">
							<h3 class="legend">Login Here</h3>
							<div class="input">
								<span class="fa fa-envelope-o" aria-hidden="true"></span>
								 <form:input id="loginUserName" path="userName"  size="15" maxlength="15" placeholder="Username" onblur="checkUsername()" required="required"/>
							</div>
							<div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								 <form:password id="loginUserPassword" path="userPassword" size="15" placeholder="Password" required="required" />
                                        <form:errors path="userPassword"/>
							</div>

							     <form:select cssClass="input" path="userType" items="${userType}"/>


						   <button type="submit" id="submit" class="btn submit">Login</button>

						</form:form>
					</article>
				</div>
				<div id="section2" class="section-w3ls">
					<input type="radio" name="sections" id="option2">
					<label for="option2" class="icon-left-w3pvt"><span class="fa fa-pencil-square" aria-hidden="true"></span>Register</label>
					<article>
						<form:form modelAttribute="user" action="/signUp" method="post" enctype="multipart/form-data" autocomplete="off">
							<h3 class="legend">Register Here</h3>
							<div class="input">
								<span class="fa fa-user-o" aria-hidden="true"></span>
								<form:input path="fullName" size = "50" maxlength="50" placeholder="Full-Name" required="required"/>
                                                      <form:errors path="fullName" />
							</div>
							<div class="input">
                            	<span class="fa fa-user-o" aria-hidden="true"></span>
                                <form:input path="userName" size = "20"  maxlength="20" id="userName"  placeholder="User Name" required="required" onblur="validateUserName()"/>
                                <form:errors path="userName" />
                            </div>
                            <div id="userName_error" class="d-none invalid-feedback"></div>

							<div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								<form:password path="userPassword" size = "20" maxlength="20"  placeholder="Password" required="required"/>
                                                <form:errors path="userPassword" />
							</div>

							<div class="input">
                                     <form:input type="email"  path="userMail" size = "35" maxlength="35"  placeholder="E-mail" required="required" onblur="validateEmail()"/>
                                     <form:errors path="userMail" />
                            </div>
                            <div id="userMail_error" class="d-none invalid-feedback"></div>

                            <div class="input">
                                <form:input type="text" path="userContact" size = "20" maxlength="20"  placeholder="Contact Number" required="required" onblur="validateContact()"/>
                                <form:errors path="userContact"/>
                            </div>
                            <div id="userContact_error" class="d-none invalid-feedback"></div>

							<button type="submit" name="button" id="submitBtn" class="btn submit" >Register</button>
						</form:form>
					</article>
				</div>
				<div id="section3" class="section-w3ls">
					<input type="radio" name="sections" id="option3">
					<label for="option3" class="icon-left-w3pvt"><span class="fa fa-lock" aria-hidden="true"></span>Forgot Password?</label>
					<article>
						<form:form modelAttribute="user" method="post" action="/forgotPassword" enctype="multipart/form-data">
							<h3 class="legend last">Reset Password</h3>
							<p class="para-style">Enter your email address below and we'll send you an email with instructions.</p>

							<div class="input">
								<span class="fa fa-envelope-o" aria-hidden="true"></span>
								<form:input type="email" id="forgotUserMail" path="userMail" size = "35" maxlength="35"  cssClass="email" placeholder="E-mail" required="required" onblur="validateForgotPasswordEmail()"/>
                                                                 <form:errors path="userMail" />
							</div>
							<button type="submit"  class="btn submit last-btn">Reset</button>
						</form:form>
					</article>
				</div>
			</div>
			<!-- //vertical tabs -->
			<div class="clear"></div>
		</div>
	</div>

</body>




<script>
$("#submit").click(function(){

var key = CryptoJS.lib.WordArray.random(16);
var iv= CryptoJS.lib.WordArray.random(16);
var email=$("#loginUserName").val();
var password=$("#loginUserPassword").val();


var pass = CryptoJS.AES.encrypt(password, key, { iv: iv });
var encrypted =CryptoJS.AES.encrypt(email, key, { iv: iv });
encrypted=encrypted.ciphertext.toString();
       encrypted = CryptoJS.AES.encrypt( encrypted, key, { iv: iv });
        var cipherData = iv.toString(CryptoJS.enc.Base64)+":"+encrypted.ciphertext.toString()+":"+key.toString(CryptoJS.enc.Base64);

   pass=pass.ciphertext.toString();
       pass = CryptoJS.AES.encrypt( pass, key, { iv: iv });
        var cipherDatapass = iv.toString(CryptoJS.enc.Base64)+":"+pass.ciphertext.toString()+":"+key.toString(CryptoJS.enc.Base64);

$("#loginUserName").val(cipherData);
$("#loginUserPassword").val(cipherDatapass);
$("#key").val(key);
});
</script>

</html>