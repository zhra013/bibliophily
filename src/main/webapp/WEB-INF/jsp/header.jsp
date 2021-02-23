<nav class="navbar navbar-expand-sm bg-light">

     <c:url var="postList" value="/post/list">
        <c:param name="userId" value="${sessionScope.currentUser.id}"/>
        <c:param name="excludeOwner" value="false"/>
     </c:url>

     <c:url value="changePassword" var = "changePassword">
        <c:param name="userId" value="${sessionScope.currentUser.id}"/>
     </c:url>


    <ul class="navbar-nav mr-auto">

        <li class="nav-item">
            <a class="nav-link" href="#">Home</a>
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
        <input type="search" name="param" class="form-control mr-sm-2" placeholder="Search">
        <button class="search-button btn btn-outline-success" type="button" data-toggle="dropdown">Search</button>
        <div class="dropdown-menu search-menu w-100">
        </div>
    </div>
</nav>


