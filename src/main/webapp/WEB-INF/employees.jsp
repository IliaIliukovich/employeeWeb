<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Project Manager</title>

    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>

</head>
<body>


<div class="container">

    <h2>Employees</h2>
    <table class="table table-hover">
        <tbody>
        <tr>
            <th>Name</th><th>Surname</th><th>Id</th><th>Age</th>
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.id}</td>
                <td>${employee.age}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>