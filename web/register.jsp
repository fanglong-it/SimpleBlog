<%-- 
    Document   : register
    Created on : Oct 2, 2021, 5:06:51 PM
    Author     : cunpl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Create Account</title>

        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"><!-- JavaScript Bundle with Popper -->
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script> <title>Admin</title>

    </head>
    <body>
        <div class="position-absolute top-50 start-50 translate-middle w-75 p-3" style="">
            <form class="row g-3" action="createAccount">
                <div class="col-12">
                    Email
                    <input type="email" name="txtEmail" class="form-control" value="" required>
                </div>
                
                <div class="col-12">
                    Password
                    <input type="password" name="txtPassword" class="form-control"  value="" required>
                </div>
                <div class="col-12">
                    Re Password
                    <input type="password" name="txtRePassword" class="form-control"  value="" required>
                </div>
                <div class="col-12">
                    Name
                    <input type="text" name="txtName" class="form-control" id="" value="" required>
                </div>
                
                <div class="col-md-4">
                    <label for="inputPhone" class="form-label">Phone Number</label>
                    <input type="tel" pattern="[0-9]{9,10}" class="form-control" name="txtPhoneNumber" value="" required>
                </div>
               
                <c:if test="${not empty requestScope.CREATEACCOUNT_MSG}">
                    <p style="color: red">${requestScope.CREATEACCOUNT_MSG}</p>
                </c:if>
                <div class="col-12">
                    <button type="submit" class="btn btn-warning form-control" >Create Account</button>
                </div>
            </form>
    </body>
</html>
