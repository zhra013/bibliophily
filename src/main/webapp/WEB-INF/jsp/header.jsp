<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<nav class="navbar navbar-expand-sm bg-light">

     <c:url var="postList" value="/post/list/page">
        <c:param name="userId" value="${sessionScope.currentUser.id}"/>
        <c:param name="excludeOwner" value="false"/>
        <c:param name="page" value="0"/>
     </c:url>

     <c:url var="allPostList" value="/post/list/page">
             <c:param name="userId" value="${sessionScope.currentUser.id}"/>
             <c:param name="excludeOwner" value="true"/>
              <c:param name="page" value="0"/>
        </c:url>

      <c:url var="users" value="/users">
             <c:param name="userId" value="${sessionScope.currentUser.id}"/>
      </c:url>

        <c:url var="friends" value="/friend/${sessionScope.currentUser.id}/getFriends">
                   <c:param name="userId" value="${sessionScope.currentUser.id}"/>
            </c:url>

     <c:url value="/changePassword" var = "changePassword">
        <c:param name="userId" value="${sessionScope.currentUser.id}"/>
     </c:url>


    <ul class="navbar-nav mr-auto">

        <li class="nav-item">
            <a class="nav-link" href="${allPostList}">Home</a>
        </li>

         <li class="nav-item">
                    <a class="nav-link" href="${users}">Users</a>
         </li>

          <li class="nav-item">
                    <a class="nav-link" href="${friends}">Friends</a>
           </li>

        <div class="dropdown">
          <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            My Profile
          </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="nav-link" href="/profile">Profile</a>
                <a class="nav-link" href="/post/upload">Upload Post</a>
                <a class="nav-link" href="${postList}">My Post</a>
                <a class="nav-link" href="${changePassword}">Change Password</a>
            </div>
        </div>


        <li class="nav-item">
            <a class="nav-link" href="/logout">Logout</a>
        </li>


    </ul>

    <div class="form-inline search-div dropdown">
        <div class="btn-group dropdown notification-dropdown align-self-center dropleft">
                                                    <div class="header-icon card-header-btn position-relative mx-2 d-flex justify-content-center"
                                                        role="button" id="notification" data-toggle="dropdown"
                                                        aria-expanded="false" title="Notification">
                                                        <i class="fas fa-bell align-self-center"></i>
                                                       <div class="bg-primary-color" id="notification_sign"></div>


                                                    </div>

                                                    <ul class="dropdown-menu" aria-labelledby="notification" id="notifications">

                                                    </ul>

                                                </div>




        <input type="search" name="param" class="form-control mr-sm-2" placeholder="Search">
        <button class="search-button btn btn-outline-success" type="button" onclick ="search()" data-toggle="dropdown">Search</button>
        <div class="dropdown-menu search-menu w-100">
        </div>
    </div>
</nav>

<script>
function search(){
    var param = $('input[name="param"]').val();
    $('.search-menu').empty();
    if (param) {
        $.get('/post/search', {searchParam: param}, function (postList) {
                  postList.forEach(function (post, index) {
                $('.search-menu').append($('<a/>', {
                    'class': 'dropdown-item search-item',
                    href: '/post/review?postId=' + post.id
                }).text(post.title));
                })
            });
        }
}

$(document).ready(function() {
    var userid = ${sessionScope.currentUser.id};
    $.ajax({
        type: 'get',
        url: '/friend/'+userid+'/getRequest',
        data: { },
        success: function (data) {
            var obj = JSON.parse(data);
            if(obj.length > 0){
                var str = "";
                for(var i = 0; i < obj.length; i++){
                    str += '<div class="notification-section"><li class="mb-3"><div class="container-fluid"><div class="row"><div class="d-flex align-items-center w-100"><div'
                        + ' class="notification-profile align-self-baseline"><img src="/img/profile.png"alt="User profile"></div><div><div><span class="text-nowrap notify-username">'
                        + obj[i].user.userName + '</span><p class="mb-0 text-nowrap notify-amount">Wants to be your Friend<span class="ml-2"><i class="mr-1"></i></span></p></div><div class="d-flex mt-2 notification-status">'
                        + '<div onclick="acceptRequest('+obj[i].id+')"class="pr-2"><span style="font-size: 10px !important;" class="list-credit-amount py-1 nowrap"><i class="fas fa-check mr-2"></i>Accept</span>'
                        + '</div><div onclick="rejectRequest('+obj[i].id+')"><span style="font-size: 10px !important;" class="list-debit-amount py-1"><i class="fas fa-times mr-2"></i>Reject</span>'
                        + '</div></div></div></div></div></div></li></div>';
                }

                document.getElementById("notifications").innerHTML = str;
                document.getElementById("notification_sign").classList.add("notify-sign");
            }
            else{
                    var str = '<div class="alert alert-info table-div text-center">No Friend Request Found.</div>';
                   document.getElementById("notifications").innerHTML = str;
            }

        }
    });
})
function acceptRequest(friendId){
    $.ajax({
        type: 'get',
        url: '/friend/'+${sessionScope.currentUser.id}+'/acceptRequest/'+friendId,
        data: { },
        success: function (data) {
            window.location.reload();
        }
    });
}

function rejectRequest(friendId){
    $.ajax({
        type: 'get',
        url: '/friend/'+${sessionScope.currentUser.id}+'/declineRequest/'+friendId,
        data: { },
        success: function (data) {
            window.location.reload();
        }
    });
}

</script>


