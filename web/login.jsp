<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <!--VENDORS-->
    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
    <!--Own CSS-->
    <link rel="stylesheet" href="css/login.css">
  </head>
  <body class="text-center" data-gr-c-s-loaded="true">
    <form class="form-signin" method="post" action="users">
      <h3>Advanced Medical Access</h3>
      <label for="inputName" class="sr-only">Username</label>
      <input type="text" name="name" id="inputName" class="form-control" placeholder="Username" autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password">
      <c:if test="${messages.error != null}">
          <div class="alert alert-danger" role="alert">${messages.error}</div>
      </c:if>
      <button class="btn btn-lg btn-primary btn-block" type="submit" name="action" value="login">Log in</button>
      <button class="btn btn-lg btn-success btn-block" type="submit" name="action" value="register_form">Register</button>
      <p class="mt-5 mb-3 text-muted">Institut Provençana © 2019</p>
    </form>
  </body>
  <jsp:include page="html/scripts.html"/>
</html>
