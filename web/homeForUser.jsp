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
    </head>
    <body>
        <h1>Home For User</h1>
        <c:if test="${sessionScope.ACC != null}">
            <h2>Welcome, ${sessionScope.ACC.email}</h2>
        </c:if>
    </body>
</html>
