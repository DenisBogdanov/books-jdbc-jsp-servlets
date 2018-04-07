<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>


<div class="container">
    <h1>Authors</h1>

    <table class="author-list">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>

        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.firstName}</td>
                <td>${author.lastName}</td>
            </tr>
        </c:forEach>

    </table>

</div>

</body>
</html>
