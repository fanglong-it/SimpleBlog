<%-- 
    Document   : articleDetails
    Created on : Oct 3, 2021, 4:28:17 PM
    Author     : cunpl
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <h1>Post Article</h1>

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

        <form class="row g-3" action="postArticle">

            <div class="col-md-6">
                <label for="inputEmail4" class="form-label">Title Name</label>
                <input type="text" name="titleName" class="form-control" value="" required="">
            </div>

            <div class="col-md-6">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control"   value="${sessionScope.ACC.email}" readonly="">
            </div>
            <div class="col-12">
                <label class="form-label">Content :</label>
                <select name="contentId" class="select">

                    <c:forEach var="com" items="${sessionScope.LIST_CONTENT}">
                        <option class="form-label" style="width: 80%" value="${com.contentId}">${com.contentName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-12">
                <textarea class="form-control" id="id" name="description" rows="7" cols="150" required=""></textarea>
            </div>
            <button class="col-12 btn btn-primary">Post</button>
        </form>





    </body>
</html>
