<%@ page import="ru.bogdanium.books.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>

<h1>Books</h1>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
%>

<%= books %>

</body>
</html>
