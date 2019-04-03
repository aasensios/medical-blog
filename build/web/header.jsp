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
            <a class="btn text-warning">${name} (${role})</a>
            <a class="btn btn-warning my-2 my-sm-0" href="users?action=logout">Logout</a>
        </c:otherwise>
    </c:choose>
  </nav>
</header>
