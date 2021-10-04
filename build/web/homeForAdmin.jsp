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
        <c:if test="${sessionScope.ACC.roleId eq 'US'}">
            <c:redirect url="homeForUser.jsp"/>
        </c:if>
        <hr>
        <a class="btn btn-primary" href="search?searchValue=" >Back Home</a>
        <hr>
        <hr>
        <form action="setPage">
            <select name="numberOfPage">
                <option value="1">1</option>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="15">15</option>
                <option value="20">20</option>
                <option value="25">25</option>
                <option value="30">30</option>
                
            </select>
            <button name="btnAction" class="btn btn-danger">Set</button>
            
             <c:if test="${requestScope.SETPAGE_MSG != null}">
                <p style="color: red">${requestScope.SETPAGE_MSG}</p>
            </c:if>
            
        </form>

        <hr>

        <form action="search">

            Search Value: <input  style="width: 20%" type="text" name="searchValue" value="${param.searchValue}">
            <select name="option">
                <option value="" selected=""></option>
                <option value="New">New</option>
                <option value="Active">Active</option>
                <option value="Delete">Delete</option>
            </select>
            <button class="btn btn-primary">Search</button>
            <c:if test="${requestScope.UPDATE_MSG != null}">
                <p style="color: red">${requestScope.UPDATE_MSG}</p>
            </c:if>


            <c:if test="${requestScope.SEARCH_MSG != null}">
                <p style="color: red">${requestScope.SEARCH_MSG}</p>
            </c:if>
            <table border="" class="table table-striped">
                <thead>
                    <tr>
                        <th>Title Id</th>
                        <th>Title</th>
                        <th style="width: 30%">Description</th>
                        <th>Author</th>
                        <th>PostDate</th>
                        <th>Content</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${requestScope.LIST_ARTICLE}">
                        <tr>
                            <td>${a.titleId}
                                <input type="hidden" name="titleId" value="${a.titleId}">
                            </td>
                            <td>${a.titleName}</td>
                            <td>${a.description}</td>
                            <td>${a.email}</td>
                            <td>${a.postDate}</td>
                            <td>${a.contentId}</td>
                            <td>${a.status}</td>
                            <td>
                                <c:if test="${a.status eq 'Active'}">

                                </c:if>
                                <c:if test="${a.status eq 'New'}">
                                    <a href="deleteRequest?titleId=${a.titleId}" class="btn btn-danger">Delete</a>
                                    <a href="acceptRequest?titleId=${a.titleId}" class="btn btn-success">Accept</a>
                                </c:if>

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
