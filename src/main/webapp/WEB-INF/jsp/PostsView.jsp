<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Post Feed</title>

    <script src="/../js/validation.js"> </script>
    <script src="/../js/jQuery-3.4.1.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/../css/bulma.css">
    <link rel="stylesheet" href="/../css/app.css">
    <link rel="stylesheet" href="/../css/core.css">
    <link rel="stylesheet" href="/../css/stylesheet.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
        integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
        crossorigin="anonymous" />
    <link rel="stylesheet" href="/../css/Profile.css">
    <%@include file="bootstrapFiles.jsp" %>
</head>
<body>
    <div class="pageloader"></div>

<h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>

<div class="view-wrapper" id="main-div">
<c:choose>
    <c:when test="${empty posts}">
        <div class="alert alert-info table-div text-center">
            Currently, no post is available.
        </div>
    </c:when>
    <c:otherwise>
                <!-- Container -->
                        <div id="main-feed" class="container">

                            <!-- Feed page main wrapper -->
                            <div id="activity-feed" class="view-wrap true-dom is-hidden">
                                <div class="columns">
                                    <div class="column is-3 is-hidden-mobile">

                                    </div>
                                    <!-- /Left side column -->

                                    <!-- Middle column -->
                                    <div class="column is-6" id="post_area">
                                        <c:forEach var="post" items="${posts}">
                                        <div id="feed-post-1" class="card is-post">
                                            <!-- Main wrap -->
                                            <div class="content-wrap">
                                                <!-- Post header -->
                                                <div class="card-heading">
                                                    <!-- User meta -->
                                                    <div class="user-block">
                                                        <div class="image">
                                                            <img src="/img/profile.png" data-user-popover="1" alt="">
                                                        </div>
                                                        <c:choose>
                                                            <c:when test="${post.isShared == 'true'}">
                                                                <div class="user-info">
                                                                    <b>${post.uploader.userName}</b> shared <b>${post.postShared.uploader.userName}</b> post ${post.postShared.postType} <b>${post.postShared.title}</b> of Author <b>${post.postShared.author}</b> Edition ${post.postShared.edition}
                                                                    <fmt:parseDate  value="${post.date}" pattern="yyyy-MM-dd" type="date" var="parsedDate" />
                                                                    <p><fmt:formatDate type = "date" value = "${parsedDate}" pattern="dd/MM/yyyy" /></p>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                        <div class="user-info">
                                                            <b>${post.uploader.userName}</b> posted ${post.postType} <b>${post.title}</b> of Author <b>${post.author}</b> Edition ${post.edition}
                                                            <fmt:parseDate  value="${post.date}" pattern="yyyy-MM-dd" type="date" var="parsedDate" />
                                                            <p><fmt:formatDate type = "date" value = "${parsedDate}" pattern="dd/MM/yyyy" /></p>
                                                        </div>
                                                        </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                </div>
                                                <!-- /Post header -->
                                                <c:choose>
                                                    <c:when test="${post.isShared == 'true'}">
                                                        <c:url value="/post/review" var="review">
                                                            <c:param name="postId" value="${post.postShared.id}"/>
                                                       </c:url>

                                                        <c:url value="/post/share" var="share">
                                                             <c:param name="postId" value="${post.postShared.id}"/>
                                                             <c:param name="userId" value="${sessionScope.currentUser.id}"/>
                                                         </c:url>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:url value="/post/review" var="review">
                                                            <c:param name="postId" value="${post.id}"/>
                                                       </c:url>

                                                        <c:url value="/post/share" var="share">
                                                             <c:param name="postId" value="${post.id}"/>
                                                             <c:param name="userId" value="${sessionScope.currentUser.id}"/>
                                                         </c:url>
                                                    </c:otherwise>
                                                </c:choose>
                                                   <c:url value="/post/delete" var="delete">
                                                      <c:param name="postId" value="${post.id}"/>
                                                      <c:param name="userId" value="${sessionScope.currentUser.id}"/>
                                                   </c:url>

                                                <!-- Post body -->
                                                <div class="card-body">
                                                    <!-- Post body text -->
                                                    <div class="post-text">
                                                        <p>${post.blog}</p>
                                                    <!-- <c:choose>
                                                        <c:when test="${post.isShared == 'true'}">
                                                            <p>${post.postShared.rating}</p>
                                                        </c:when>
                                                        <c:otherwise>

                                                        </c:otherwise>
                                                    </c:choose>-->

                                                    </div>
                                                    <div class="post-text">
                                                     Rating
                                                     <c:forEach begin="1" end="5" varStatus="loop">
                                                        <c:choose>
                                                                <c:when test="${post.isShared == 'true'}">
                                                                    <span class="fa fa-star ${loop.index <= post.postShared.rating ? 'checked' : ''}"></span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="fa fa-star ${loop.index <= post.rating ? 'checked' : ''}"></span>
                                                                </c:otherwise>
                                                        </c:choose>
                                                       </c:forEach>
                                                    </div>
                                                    <!-- Featured image -->
                                                    <div class="post-image">

                                                        <a data-fancybox="post1" data-lightbox-type="comments">
                                                        <c:choose>
                                                            <c:when test="${post.isShared == 'true'}">
                                                                <img style="height:200px; max-width: -webkit-fill-available;" src="/post/coverPhoto?postId=${post.postShared.id}" alt="">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img style="height:200px; max-width: -webkit-fill-available;" src="/post/coverPhoto?postId=${post.id}" alt="">
                                                            </c:otherwise>
                                                        </c:choose>
                                                        </a>
                                                        <!-- Action buttons -->
                                                        <!-- /partials/pages/feed/buttons/feed-post-actions.html -->
                                                        <!-- <div class="like-wrapper">
                                                            <a href="javascript:void(0);" class="like-button">
                                                                <i class="mdi mdi-heart not-liked bouncy"></i>
                                                                <i class="mdi mdi-heart is-liked bouncy"></i>
                                                                <span class="like-overlay"></span>
                                                            </a>
                                                        </div> -->
                                                        <div class="del">
                                                            <div class="fab-wrapper is-comment">
                                                               <a onclick="deletePost(${post.id})" class="small-fab share-fab modal-trigger">
                                                                  <i class="fas fa-trash"></i>
                                                                </a>
                                                            </div>
                                                          </div>
                                                        <div class="fab-wrapper is-share">
                                                            <a href="${review}" class="small-fab share-fab modal-trigger" style="text-decoration:none;">
                                                                <i class="fas fa-comments"></i>
                                                            </a>
                                                        </div>
                                                        <div class="like-wrapper">
                                                            <a data-toggle="modal" onclick="setUrl('${share}')" data-target="#addSection"  class="like-button" style="text-decoration:none;">
                                                                <i class="fas fa-share"></i>
                                                            </a>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!-- /Post body -->

                                                <!-- Post footer -->
                                                <div class="card-footer">

                                                </div>

                                            </div>

                                        </div>
                                        </c:forEach>
                                        <!-- POST #1 -->

                                    </div>
                                    <!-- /Middle column -->

                                    <!-- Right side column -->
                                    <div class="column is-3">

                                    </div>
                                    <!-- /Right side column -->
                                </div>
                            </div>
                            <!-- /Feed page main wrapper -->
                        </div>
                        <!-- /Container -->

                        <div>
                            <nav aria-label="Page navigation example">
                              <ul class="pagination">
                                <li class="page-item"><a class="page-link" id="previousBtn" onclick="LoadPreviousData()">Previous</a></li>
                                <c:if test="${posts.size() == 4}">
                                    <li class="page-item"><a class="page-link" id="nextBtn" onclick="LoadNewData()">Next</a></li>
                                </c:if>
                              </ul>
                            </nav>
                        </div>
        </div>
    </c:otherwise>
</c:choose>

    </div>
    <div class="modal fade section-body" id="addSection" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content" id="putModalData">

                   <div class="modal-header border-bottom-0">
                    <h5 class="modal-title" id="modalHeader">Share Post</h5>
                    <div class="d-flex header-icon card-header-btn">
                        <button type="button" class="align-self-center" data-dismiss="modal" aria-label="Close"><i class="fas fa-times"></i></button>
                        </div>
                    </div>
                    <div class="modal-body h-auto pt-0" >
                        <div class="row">

                            <div class="col-md-12">
                                <div class="card-element">
                                    <div class="pb-2"><label for="username">Write Something</label>
                                </div>
                                    <input type="text" id="post_caption">
                                </div>
                                </div>
                                <input type="hidden" name="shareUrl" id="shareUrl">
                                <div class="col-md-2 mt-3 text-center d-flex justify-content-center">
                                <button type="button" onclick="SharePost()" class="Submit-btn align-self-center py-1">Submit </button>
                    </div>
                    </div>
                    </div>

            </div>
        </div>
    </div>

    <script src="https://js.stripe.com/v3/"></script>

    <!-- Core js -->

    <script src="/../js/global.js"></script>
    <script src="/../js/main.js"></script>

    <!-- Page and UI related js -->
    <script src="/../js/feed.js"></script>
    <script src="/../js/stories.js"></script>
    <script src="/../js/chat.js"></script>
    <script src="/../js/inbox.js"></script>
    <script src="/../js/profile.js"></script>
    <script src="/../js/friends.js"></script>
    <script src="/../js/events.js"></script>
    <script src="/../js/explorer.js"></script>
    <script src="/../js/news.js"></script>
    <script src="/../js/questions.js"></script>
    <script src="/../js/videos.js"></script>
    <script src="/../js/shop.js"></script>
    <script src="/../js/settings.js"></script>

    <!-- Components js -->
    <script src="/../js/widgets.js"></script>
    <script src="/../js/autocompletes.js"></script>
    <script src="/../js/modal-uploader.js"></script>
    <script src="/../js/popovers-users.js"></script>
    <script src="/../js/popovers-pages.js"></script>
    <script src="/../js/go-live.js"></script>
    <script src="/../js/lightbox.js"></script>
    <script src="/../js/touch.js"></script>
    <script src="/../js/tour.js"></script>
</body>

<script>
var count = 0;
var deleted = "";
$(document).ready(function() {
    var x = document.getElementsByClassName("del");
    deleted = "${isDeleted}";
    console.log(deleted);
    $("#previousBtn").addClass("d-none");
    deletebtn(x, deleted);
});
function deletebtn(obj, result){
    if(result == "yes"){
        for(var i = 0; i < obj.length; i++){
                document.getElementsByClassName("del")[i].style.display = "block";
        }
    }
    else {
        for(var i = 0; i < obj.length; i++){
            document.getElementsByClassName("del")[i].style.display = "none";
        }
    }
}

function deletePost(id){
    var userid = ${sessionScope.currentUser.id};
    if(confirm("Delete this post?")){
        window.location.href = "/post/delete?postId="+id + "&userId="+userid;
    }
}
function setUrl(str){
    document.getElementById("shareUrl").value = str;
    document.getElementById("post_caption").value = "";

}
function LoadNewData(){
    var userid = "${param.userId}";
    var excludeOwner = "${param.excludeOwner}";
    count++;
    $.ajax({
        type: 'get',
        url: '/list/pagination/page',
        data: { page : count, userId : userid, excludeOwner: excludeOwner },
        success: function (data) {
           var obj = JSON.parse(data);
           console.log(obj);
           if(obj.length > 0){
                var list = document.getElementById("post_area");

                // If the <ul> element has any child nodes, remove its first child node
                if (list.hasChildNodes()) {
                  list.removeChild(list.childNodes[0]);
                }
                  var str = "";
                for(var i = 0; i < obj.length; i++){


                  if(obj[i].isShared){
                      var stars = "";
                      for(var j = 1; j <= 5; j++){

                            if(j <= obj[i].postShared.rating){
                                stars += '<span class="fa fa-star checked"></span>';
                            }
                            else{
                                stars += '<span class="fa fa-star"></span>';
                            }
                        }
                      var date = new Date(obj[i].date);
                      var dd = date.getDate();
                      var mm = date.getMonth() + 1;
                      var yyyy = date.getFullYear();
                      if (dd < 10) {
                          dd = '0' + dd;
                      }

                      if (mm < 10) {
                          mm = '0' + mm;
                      }
                         str += '<div id="feed-post-1" class="card is-post"><div class="content-wrap">'
                             + '<div class="card-heading"><div class="user-block"><div class="image"><img src="/img/profile.png" data-user-popover="1" alt=""></div><div class="user-info"><b>'
                             + obj[i].uploader.userName + '</b> shared <b>' + obj[i].postShared.uploader.userName + '</b> post ' + obj[i].postShared.postType + ' <b> ' + obj[i].postShared.title + '</b> of Author <b> ' + obj[i].postShared.author + '</b> Edition '
                             + obj[i].postShared.edition + '<p>' + dd + '/' + mm + '/' + yyyy + '</p></div></div></div><div class="card-body"><div class="post-text"><p>'
                             + obj[i].postShared.blog + '</p></div><div class="post-text">Rating'
                             + stars
                             + '</div><div class="post-image"><a data-fancybox="post1" data-lightbox-type="comments"> '
                             + '<img style="height:200px; max-width: -webkit-fill-available;" src="/post/coverPhoto?postId=' + obj[i].postShared.id + '" alt=""></a>'
                             + '<div class="fab-wrapper is-share"><a style="text-decoration:none;" href="/post/review?postId='+obj[i].postShared.id+'" class="small-fab share-fab modal-trigger"><i class="fas fa-comments"></i></a></div>'
                             + '<div class="like-wrapper"><a data-toggle="modal" onclick="setUrl("/post/share?postId='+obj[i].postShared.id+'&userId='+${sessionScope.currentUser.id}+'")" data-target="#addSection"  class="like-button" style="text-decoration:none;">'
                             + '<i class="fas fa-share"></i></a></div><div class="del"> <div class="fab-wrapper is-comment"><a onclick="deletePost('+obj[i].postShared.id+')" class="small-fab share-fab modal-trigger"><i class="fas fa-trash"></i>'
                             + '</a></div></div></div></div><div class="card-footer"></div></div></div></div>';
                    }
                    else{
                        var stars = "";
                        for(var j = 1; j <= 5; j++){

                              if(j <= obj[i].rating){
                                  stars += '<span class="fa fa-star checked"></span>';
                              }
                              else{
                                  stars += '<span class="fa fa-star"></span>';
                              }
                          }
                        var date = new Date(obj[i].date);
                        var dd = date.getDate();
                        var mm = date.getMonth() + 1;
                        var yyyy = date.getFullYear();
                        if (dd < 10) {
                            dd = '0' + dd;
                        }

                        if (mm < 10) {
                            mm = '0' + mm;
                        }
                      str += '<div id="feed-post-1" class="card is-post"><div class="content-wrap">'
                          + '<div class="card-heading"><div class="user-block"><div class="image"><img src="/img/profile.png" data-user-popover="1" alt=""></div><div class="user-info"><b>'
                          + obj[i].uploader.userName + '</b> posted ' + obj[i].postType + ' <b> ' + obj[i].title + '</b> of Author <b> ' + obj[i].author + '</b> Edition '
                          + obj[i].edition + '<p>' + dd + '/' + mm + '/' + yyyy + '</p></div></div></div><div class="card-body"><div class="post-text"><p>'
                          + obj[i].blog + '</p></div><div class="post-text">Rating'
                          + stars
                          + '</div><div class="post-image"><a data-fancybox="post1" data-lightbox-type="comments"> '
                          + '<img style="height:200px; max-width: -webkit-fill-available;" src="/post/coverPhoto?postId=' + obj[i].id + '" alt=""></a>'
                          + '<div class="fab-wrapper is-share"><a style="text-decoration:none;" href="/post/review?postId='+obj[i].id+'" class="small-fab share-fab modal-trigger"><i class="fas fa-comments"></i></a></div>'
                          + '<div class="like-wrapper"><a data-toggle="modal" onclick="setUrl("/post/share?postId='+obj[i].id+'&userId='+${sessionScope.currentUser.id}+'")" data-target="#addSection"  class="like-button" style="text-decoration:none;">'
                          + '<i class="fas fa-share"></i></a></div><div class="del"> <div class="fab-wrapper is-comment"><a onclick="deletePost('+obj[i].id+')" class="small-fab share-fab modal-trigger"><i class="fas fa-trash"></i>'
                          + '</a></div></div></div></div><div class="card-footer"></div></div></div></div>';
                    }
                }
                  document.getElementById("post_area").innerHTML = str;
                  var x = document.getElementsByClassName("del");
                  deletebtn(x, deleted);
                   if(obj.length < 4){
                        $("#nextBtn").addClass("d-none");
                   }
                   else{
                        $("#nextBtn").removeClass("d-none");
                   }
                }
                if(count > 0){
                    $("#previousBtn").removeClass("d-none");
                }
                else{
                    $("#nextBtn").addClass("d-none");
                }
           }
        });
}

function SharePost(){
 var caption = document.getElementById("post_caption").value;
 var url = document.getElementById("shareUrl").value;
 $.ajax({
     type: 'get',
     url: url,
     data: { comment : caption},
     success: function (data) {
            location.reload();
        }
     });
}

function LoadPreviousData(){
    var userid = "${param.userId}";
    var excludeOwner = "${param.excludeOwner}";
    count--;
    $.ajax({
        type: 'get',
        url: '/list/pagination/page',
        data: { page : count, userId : userid, excludeOwner: excludeOwner },
        success: function (data) {
           var obj = JSON.parse(data);
            console.log(obj);
           if(obj.length > 0){
                var list = document.getElementById("post_area");

                // If the <ul> element has any child nodes, remove its first child node
                if (list.hasChildNodes()) {
                  list.removeChild(list.childNodes[0]);
                }

                  var str = "";
                  for(var i = 0; i < obj.length; i++){


                    if(obj[i].isShared){
                        var stars = "";
                        for(var j = 1; j <= 5; j++){

                              if(j <= obj[i].postShared.rating){
                                  stars += '<span class="fa fa-star checked"></span>';
                              }
                              else{
                                  stars += '<span class="fa fa-star"></span>';
                              }
                          }
                        var date = new Date(obj[i].date);
                        var dd = date.getDate();
                        var mm = date.getMonth() + 1;
                        var yyyy = date.getFullYear();
                        if (dd < 10) {
                            dd = '0' + dd;
                        }

                        if (mm < 10) {
                            mm = '0' + mm;
                        }
                           str += '<div id="feed-post-1" class="card is-post"><div class="content-wrap">'
                               + '<div class="card-heading"><div class="user-block"><div class="image"><img src="/img/profile.png" data-user-popover="1" alt=""></div><div class="user-info"><b>'
                               + obj[i].uploader.userName + '</b> shared <b>' + obj[i].postShared.uploader.userName + '</b> post ' + obj[i].postShared.postType + ' <b> ' + obj[i].postShared.title + '</b> of Author <b> ' + obj[i].postShared.author + '</b> Edition '
                               + obj[i].postShared.edition + '<p>' + dd + '/' + mm + '/' + yyyy + '</p></div></div></div><div class="card-body"><div class="post-text"><p>'
                               + obj[i].postShared.blog + '</p></div><div class="post-text">Rating'
                               + stars
                               + '</div><div class="post-image"><a data-fancybox="post1" data-lightbox-type="comments"> '
                               + '<img style="height:200px; max-width: -webkit-fill-available;" src="/post/coverPhoto?postId=' + obj[i].postShared.id + '" alt=""></a>'
                               + '<div class="fab-wrapper is-share"><a style="text-decoration:none;" href="/post/review?postId='+obj[i].postShared.id+'" class="small-fab share-fab modal-trigger"><i class="fas fa-comments"></i></a></div>'
                               + '<div class="like-wrapper"><a data-toggle="modal" onclick="setUrl("/post/share?postId='+obj[i].postShared.id+'&userId='+${sessionScope.currentUser.id}+'")" data-target="#addSection"  class="like-button" style="text-decoration:none;">'
                               + '<i class="fas fa-share"></i></a></div><div class="del"> <div class="fab-wrapper is-comment"><a onclick="deletePost('+obj[i].postShared.id+')" class="small-fab share-fab modal-trigger"><i class="fas fa-trash"></i>'
                               + '</a></div></div></div></div><div class="card-footer"></div></div></div></div>';
                      }
                      else{
                          var stars = "";
                          for(var j = 1; j <= 5; j++){

                                if(j <= obj[i].rating){
                                    stars += '<span class="fa fa-star checked"></span>';
                                }
                                else{
                                    stars += '<span class="fa fa-star"></span>';
                                }
                            }
                          var date = new Date(obj[i].date);
                          var dd = date.getDate();
                          var mm = date.getMonth() + 1;
                          var yyyy = date.getFullYear();
                          if (dd < 10) {
                              dd = '0' + dd;
                          }

                          if (mm < 10) {
                              mm = '0' + mm;
                          }
                        str += '<div id="feed-post-1" class="card is-post"><div class="content-wrap">'
                            + '<div class="card-heading"><div class="user-block"><div class="image"><img src="/img/profile.png" data-user-popover="1" alt=""></div><div class="user-info"><b>'
                            + obj[i].uploader.userName + '</b> posted ' + obj[i].postType + ' <b> ' + obj[i].title + '</b> of Author <b> ' + obj[i].author + '</b> Edition '
                            + obj[i].edition + '<p>' + dd + '/' + mm + '/' + yyyy + '</p></div></div></div><div class="card-body"><div class="post-text"><p>'
                            + obj[i].blog + '</p></div><div class="post-text">Rating'
                            + stars
                            + '</div><div class="post-image"><a data-fancybox="post1" data-lightbox-type="comments"> '
                            + '<img style="height:200px; max-width: -webkit-fill-available;" src="/post/coverPhoto?postId=' + obj[i].id + '" alt=""></a>'
                            + '<div class="fab-wrapper is-share"><a style="text-decoration:none;" href="/post/review?postId='+obj[i].id+'" class="small-fab share-fab modal-trigger"><i class="fas fa-comments"></i></a></div>'
                            + '<div class="like-wrapper"><a data-toggle="modal" onclick="setUrl("/post/share?postId='+obj[i].id+'&userId='+${sessionScope.currentUser.id}+'")" data-target="#addSection"  class="like-button" style="text-decoration:none;">'
                            + '<i class="fas fa-share"></i></a></div><div class="del"> <div class="fab-wrapper is-comment"><a onclick="deletePost('+obj[i].id+')" class="small-fab share-fab modal-trigger"><i class="fas fa-trash"></i>'
                            + '</a></div></div></div></div><div class="card-footer"></div></div></div></div>';
                      }
                  }
                  document.getElementById("post_area").innerHTML = str;
                  var x = document.getElementsByClassName("del");
                  deletebtn(x, deleted);
                }
                if(count > 0){
                    $("#nextBtn").removeClass("d-none");
                }
                else{
                    $("#previousBtn").addClass("d-none");
                }
           }
        });
}
</script>
</html>

