<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="author" scope="request" type="ru.bogdanium.books.model.Author"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${author.fullName}</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>


<div class="container">
    <h1>${author.fullName}</h1>

    <ul>
        <c:forEach var="book" items="${books}">
            <li>
                <a href="books?${book.slug}&action=LOAD&id=${book.id}">${book.title}</a>
            </li>
        </c:forEach>
    </ul>

    <c:import url="includes/footer.jsp"/>
