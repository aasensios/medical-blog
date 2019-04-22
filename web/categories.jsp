<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
    <!--Own CSS-->
    <link rel="stylesheet" href="css/categories.css">
    <link rel="stylesheet" href="css/footer.css">
    <title>Categories - Advanced Medical</title>
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <br>
    <main class="container" role="main">
      <div class="starter-template">
        <c:if test="${categories != null}">
            <div class="row">
              <!--List of all categories -->
              <c:forEach items="${categories}" var="category">
                  <fig class="col">
                    <img src="images/${category.id}.jpg" class="category">
                    <c:choose>
                        <c:when test="${!logged_in}">
                            <figcaption>${category.name}</figcaption>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${category.name == 'webs'}">
                                    <figcaption><a href="webs?action=list">${category.name}</a></figcaption>
                                    </c:when>
                                    <c:otherwise>
                                    <figcaption><a href="https://www.google.es">${category.name}</a></figcaption>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                  </fig>
              </c:forEach>
            </div>
        </c:if>
      </div>
    </main>
    <jsp:include page="html/footer.html"/>
    <jsp:include page="html/scripts.html"/>
  </body>
</html>
