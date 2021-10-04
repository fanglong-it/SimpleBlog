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
            <a href="login.jsp" class="btn btn-danger" >Login</a>
        </c:if>
        <c:if test="${sessionScope.ACC.roleId eq 'AD'}">
            <c:redirect url="homeForAdmin.jsp"/>
        </c:if>
            <hr>
            <a class="btn btn-primary" href="search?searchValue=" >Back Home</a>
            <hr>

        <form action="search">

            Search Value: <input  style="width: 20%" type="text" name="searchValue" value="${param.searchValue}">
            <button class="btn btn-primary">Search</button>
            <a class="btn btn-danger" href="postArticlePage?">Post Article</a>
            <a class="btn btn-danger" href="requestArticlePage?">View Request</a>
            <c:if test="${requestScope.SEARCH_MSG != null}">
                <p style="color: red">${requestScope.SEARCH_MSG}</p>
            </c:if>
            <table border="" class="table table-striped">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Author</th>
                        <th>PostDate</th>
                        <th>Content</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${requestScope.LIST_ARTICLE}" >
                        <tr>
                            <td>
                                <!-- Them o day -->
                                <input type="hidden" name="titleId" value="${a.titleId}">
                            </td>
                            <td>${a.titleName}</td>
                            <td>${a.description}</td>
                            <td>${a.email}</td>
                            <td>${a.postDate}</td>
                            <td>${a.contentName}</td>
                            <td>
                                <a class="btn btn-warning" href="viewArticleDetail?titleId=${a.titleId}">Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:forEach begin = "1" end="${requestScope.page}" var="i">
                <a href="search?page=${i}&searchValue=">${i} </a> 
            </c:forEach>

        </form>


    </body>
</html>
