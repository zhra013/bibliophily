<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile Information</title>
    <link rel="stylesheet" href="/../css/Profile.css">
    <script src="/../js/validation.js"> </script>
     <script src="/../js/jQuery-3.4.1.js"></script>

    <style>
        body, html {
          height: 100%
        }

        .bgimg {
          background-image: url('/../img/img.jpg');
          height: 100%;
          background-position: center;
          background-size: cover;
          color: black;
          background-repeat: no-repeat;
          background-attachment: fixed;
          font-family: "Courier New", Courier, monospace;
          font-size: 25px;
        }

        /* Position text in the top-left corner */
        .topleft {
          position: relative;
          top: 0;
          left: 16px;
        }

        /* Position text in the bottom-left corner */
        .bottomleft {
          position: absolute;
          bottom: 0;
          left: 16px;
        }

        /* Position text in the middle */
        .middle {
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
          text-align: center;
        }

        /* Style the <hr> element */
        hr {
          margin: auto;
          width: 40%;
        }
    </style>
    <link rel="stylesheet" href="/../css/Profile.css">

</head>
<body>
<%@include file="bootstrapFiles.jsp"%>
<%@include file="header.jsp" %>
    <div class="bgimg">
      <div class="topleft">
        <p>Bibliophily Connect</p>
      </div>
      <div class="middle">
        <h1>COMING SOON</h1>
        <hr>

      </div>
      <div class="bottomleft">

      </div>
    </div>
</body>
</html>