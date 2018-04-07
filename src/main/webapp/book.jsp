<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="book" scope="request" type="ru.bogdanium.books.model.Book"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${book.title}</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>


<div class="container">
    <h1>${book.title}</h1>

    <form action="books" method="post">

        <input type="hidden" name="action" value="UPDATE">
        <input type="hidden" name="book-id" value="${book.id}">

        <table>
            <tr>
                <td>
                    <label for="title">Title:</label>
                </td>
                <td>
                    <input type="text" name="title" id="title" value="${book.title}">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="author">Author:</label>
                </td>
                <td>
                    <select name="author-id" id="author">
                        <option value="${book.author.id}">${book.author.fullName}</option>

                        <c:forEach var="author" items="${authors}">

                            <c:if test="${author.id != book.author.id}">
                                <option value="${author.id}">${author.fullName}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                </td>
                <td><a href="authors">Add author</a></td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="Update">
                </td>
            </tr>

        </table>

    </form>

</div>

</body>
</html>
