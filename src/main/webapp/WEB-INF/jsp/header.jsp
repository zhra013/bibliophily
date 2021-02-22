<nav class="navbar navbar-expand-sm bg-light">

    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="/profile">Profile</a>
        </li>

                <li class="nav-item">
                    <a class="nav-link" href="#">Latest Post</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/post/upload">Upload Post</a>
                </li>

              <c:url var="postList" value="/post/list">
                    <c:param name="userId" value="${sessionScope.currentUser.id}"/>
                    <c:param name="excludeOwner" value="false"/>
                </c:url>
                <li class="nav-item">
                    <a class="nav-link" href="${postList}">My Post</a>
                </li>
                 <li class="nav-item">
                 <c:url value="/users" var = "users">
                      <c:param name="userId" value="${user.id}"/>
                  </c:url>
                      <a class="nav-link" href="${users}">Users</a>
                  </li>

            <c:url value="changePassword" var = "changePassword">
                            <c:param name="userId" value="${user.id}"/>
            </c:url>
        <li class="nav-item">
            <a class="nav-link" href="/logout">Logout</a>
        </li>

         <li class="nav-item">
                    <a class="nav-link" href="${changePassword}">Change Password</a>
                </li>
    </ul>

    <div class="form-inline search-div dropdown">
        <input type="search" name="param" class="form-control mr-sm-2" placeholder="Search">
        <button class="search-button btn btn-outline-success" type="button" data-toggle="dropdown">Search</button>
        <div class="dropdown-menu search-menu w-100">
        </div>
    </div>
</nav>

