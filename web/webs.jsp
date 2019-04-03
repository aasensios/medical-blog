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
    <link rel="stylesheet" href="css/footer.css">
    <title>Webs - Advanced Medical</title>
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <br>
    <main class="container-fluid" role="main">
      <div class="starter-template">

        <!-- Messages: Success or Error -->
        <c:choose>
            <c:when test="${messages.success != null}">
                <span class="alert alert-success" role="alert">${messages.success}</span>
            </c:when>
            <c:when test="${messages.error != null}">
                <span class="alert alert-danger" role="alert">${messages.error}</span>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
        <br>
        <br>

        <!-- Webs table -->
        <c:if test="${webs != null}">
            <form action="webs" method="post">
              <table class="table table-striped">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">Code</th>
                    <th scope="col">Publication Date</th>
                    <th scope="col">Title</th>
                    <th scope="col">URL</th>
                      <c:if test="${role == 'admin'}">
                      <th scope="col"><a class="btn btn-info" href="webs?action=add_form">Add a Web</th>
                      </c:if>
                  </tr>
                </thead>
                <tbody>

                  <!--List of all webs-->
                  <c:forEach items="${webs}" var="web">
                      <tr>
                        <td scope="row">${web.code}</td>
                        <td scope="row">${web.publicationDate}</td>                        
                        <td scope="row">${web.title}</td>
                        <td scope="row"><a href="${web.url}">${web.url}</a></td>
                        <!--Modify and Delete buttons appear if the logged user has admin role-->
                        <c:if test="${role == 'admin'}">
                            <td scope="row">
                              <button class="btn btn-primary" type="submit" value="${web.code};${web.publicationDate};${web.title};${web.url}" name="web_to_modify">Update</button>
                              <a class="btn btn-danger" href="webs?action=delete&code=${web.code}" onclick="confirm('Are you sure you want to delete this entry?')">Delete</a>
                              <input type="hidden" name="action" value="prepare_web_to_modify"/>
                            </td>
                        </c:if>
                      </tr>
                  </c:forEach>
                </tbody>
              </table>
            </form>
        </c:if>

        <!-- Forms: Add and Modify -->
        <c:if test="${add || modify}" >
            <form action="webs" method="post">
              <div class="form-group row">
                <label for="inputCode" class="col-sm-3">Web Code:</label>
                <input type="text" class="form-control col-sm-6" id="inputCode" name="code" placeholder="P-1234"
                       value="<c:out value="${web.code}"/>"
                       <c:if test="${modify}">readonly</c:if>>
                <c:if test="${messages.code != null}">
                    <span class="text-danger col-sm-3">${messages.code}</span>
                </c:if>
              </div>
              <div class="form-group row">
                <label for="inputPublicationDate" class="col-sm-3">Publication Date:</label>
                <input type="text" class="form-control col-sm-6" id="inputPublicationDate" name="publication_date" placeholder="2019-12-31"
                       value="<c:out value="${web.publicationDate}"/>">
                <c:if test="${messages.publicationDate != null}">
                    <span class="text-danger col-sm-3">${messages.publicationDate}</span>
                </c:if>
              </div>
              <div class="form-group row">
                <label for="inputTitle" class="col-sm-3">Title:</label>
                <input type="text" class="form-control col-sm-6" id="inputTitle" name="title" placeholder="Web Title Example"
                       value="<c:out value="${web.title}"/>">
                <c:if test="${messages.title != null}">
                    <span class="text-danger col-sm-3">${messages.title}</span>
                </c:if>
              </div>
              <div class="form-group row">
                <label for="inputURL" class="col-sm-3">URL:</label>
                <input type="text" class="form-control col-sm-6" id="inputURL" name="url" placeholder="https://www.example.com"
                       value="<c:out value="${web.url}"/>">
                <c:if test="${messages.url != null}">
                    <span class="text-danger col-sm-3">${messages.url}</span>
                </c:if>
              </div>
              <c:if test="${add}">
                  <button class="btn btn-success" type="submit" name="action" value="add">Add</button>
              </c:if>
              <c:if test="${modify}">
                  <button class="btn btn-warning" type="submit" name="action" value="modify">Modify</button>
              </c:if>
              <button class="btn btn-secondary" type="reset">Reset</button>
            </form>
        </c:if>
      </div>
    </main>
    <jsp:include page="html/footer.html"/>
    <jsp:include page="html/scripts.html"/>
  </body>
</html>
