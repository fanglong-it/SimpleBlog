<%-- 
    Document   : homeForUser
    Created on : Oct 2, 2021, 4:42:27 PM
    Author     : cunpl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

    </head>
    <body>
        <h1>Home For User</h1>
        <c:if test="${sessionScope.ACC != null}">
            <h2>Welcome, ${sessionScope.ACC.email}</h2>
            <a href="logout" class="btn btn-danger" >Logout</a>
        </c:if>
        <c:if test="${sessionScope.ACC == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${sessionScope.ACC.roleId eq 'AD'}">
            <c:redirect url="homeForAdmin.jsp"/>
        </c:if>
        <hr>
        <a class="btn btn-primary" href="search?searchValue=" >Back Home</a>
        <hr>

        <c:if test="${requestScope.REQUEST_ARTICLE_MSG != null}">
            <p style="color: red">${requestScope.REQUEST_ARTICLE_MSG}</p>
        </c:if>
        <table border="" class="table table-striped">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Content Id</th>
                    <th>Author</th>
                    <th>PostDate</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${requestScope.LIST_REQUEST_ARTICLE}">
                    <tr>

                        <td>${a.titleName}</td>
                        <td>${a.description}</td>
                        <td>${a.contentId}</td>
                        <td>${a.email}</td>
                        <td>${a.postDate}</td>
                        <td>${a.status}</td>
                        <td>
                            <c:if test="${a.status eq 'New'}">
                                <p class="text-primary">Waiting for Admin Approve</p>
                            </c:if>
                            <c:if test="${a.status eq 'Active'}">
                                <p class="text-warning">Done</p>
                            </c:if>
                            <c:if test="${a.status eq 'Delete'}">
                                <p class="text-warning">This Article have been Delete from Admin</p>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



    </body>
</html>
