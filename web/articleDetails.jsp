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
        <h1>Article Detail</h1>
        <c:if test="${sessionScope.ACC != null}">
            <h2>Welcome, ${sessionScope.ACC.email}</h2>
            <a href="logout" class="btn btn-danger" >Logout</a>
        </c:if>
        
        <c:if test="${sessionScope.ACC.roleId eq 'AD'}">
            <c:redirect url="homeForAdmin.jsp"/>
        </c:if>
        <hr>
        <a class="btn btn-primary" href="search?searchValue=" >Back Home</a>
        <hr>

        <form class="row g-3" action="viewArticleDetail">

            <div class="col-md-6">
                <label for="inputEmail4" class="form-label">Title Name</label>

                <input type="email" class="form-control" value="${sessionScope.ARTICLE_DETAIL.titleName}"readonly="">
            </div>
            <div class="col-md-6">
                <label class="form-label">Email</label>
                <input type="text" class="form-control"   value="${sessionScope.ARTICLE_DETAIL.email}"readonly="">
            </div>
            <div class="col-md-6">
                <label class="form-label">PostDate</label>
                <input type="text" class="form-control" value="${sessionScope.ARTICLE_DETAIL.postDate}"readonly="">
            </div>
            <div class="col-md-6">
                <label class="form-label">Content</label>
                <input type="text" class="form-control" value=" ${sessionScope.ARTICLE_DETAIL.contentName}" readonly="">
            </div>
            <div class="col-12">
                <textarea class="form-control" id="id" name="name" rows="7" cols="150" readonly="">${sessionScope.ARTICLE_DETAIL.description}</textarea>
            </div>
        </form>


        <!-- This is begin of comment -->




        <div class="container mt-5">
            <c:forEach var="com" items="${sessionScope.LIST_COMMENT}">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-8">

                        <div class="card p-3">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="user d-flex flex-row align-items-center">  <span><small class="font-weight-bold text-primary">${com.email}</small> <small class="font-weight-bold">${com.commentDes}</small></span> </div> 
                            </div>
                            <div class="action d-flex justify-content-between mt-2 align-items-center">
                                <div class="icons align-items-center"> <i class="fa fa-star text-warning"></i> <i class="fa fa-check-circle-o check-icon"></i> </div>
                            </div>
                        </div>

                    </div>
                </div>
            </c:forEach>


            <form action="comment">
                <div class="row d-flex justify-content-center">

                    <div class="col-md-8">

                        <div class="card p-3">
                            <div class="d-flex justify-content-between align-items-center">
                                <input type="hidden" name="titleId" value="${sessionScope.ARTICLE_DETAIL.titleId}">
                                <div class="user d-flex flex-row align-items-center">  <span> Leave Comment Here:  <input  size="90%" type="text" name="CommentDes"> <button class="btn btn-primary">Comment</button> </span> </div> 
                            </div>
                            <div class="action d-flex justify-content-between mt-2 align-items-center">
                                <div class="icons align-items-center"> <i class="fa fa-star text-warning"></i> <i class="fa fa-check-circle-o check-icon"></i> </div>
                            </div>
                        </div>

                    </div>
                </div>
            </form>

        </div>






    </body>
</html>
