<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>


<div class="container">
    <h1>Books</h1>

    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
        </tr>

        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author.fullName}</td>
            </tr>
        </c:forEach>

    </table>

</div>

</body>
</html>
