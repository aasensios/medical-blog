<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
  <!-- Fixed navbar -->
  <nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="index.jsp">Advanced Medical</a>
        </li>
      </ul>
    </div>
    <c:choose>
        <c:when test="${!logged_in}">
            <a class="btn btn-primary my-2 my-sm-0" href='login.jsp'>Login</a>
        </c:when>
        <c:otherwise>
            <c:if test="${role == 'admin'}">
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Users</a>
                  <div class="dropdown-menu" aria-labelledby="dropdown02">
                    <a class="dropdown-item" href="users?action=list">List</a>
                    <a class="dropdown-item" href="users?action=register_form">Add</a>
                    <a class="dropdown-item" href="users?action=modify_form">Modify</a>
                    <a class="dropdown-item" href="users?action=delete_form">Delete</a>
                  </div>
                </li>
            </c:if>
            <a class="btn text-warning">${name} (${role})</a>
            <a class="btn btn-warning" href="users?action=logout">Logout</a>
        </c:otherwise>
    </c:choose>
  </nav>
</header>
