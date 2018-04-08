<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css"
          integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1" crossorigin="anonymous">

</head>
<body>


<div class="container">
    <h1>Books</h1>

    <div class="search">
        <form action="books" method="get">
            <input type="hidden" name="action" value="SEARCH">
            <input type="text" name="search-pattern" id="search" placeholder="Search...">
        </form>
    </div>

    <h2>Add New Book</h2>
    <form action="books" method="post">

        <input type="hidden" name="action" value="ADD">
        <table>
            <tr>
                <td>
                    <label for="title">Title:</label>
                </td>
                <td>
                    <input type="text" name="title" id="title">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="author">Author:</label>
                </td>
                <td>
                    <select name="author-id" id="author">
                        <c:forEach var="author" items="${authors}">
                            <option value="${author.id}">${author.fullName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><a href="authors">Add author</a></td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="Add">
                </td>
            </tr>

        </table>

    </form>

    <table class="book-list">
        <tr>
            <th>Title</th>
            <th>Author</th>
        </tr>

        <c:forEach var="book" items="${books}">
            <tr>
                <td>
                    <a href="books?${book.slug}&action=LOAD&id=${book.id}">${book.title}</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="books?${book.slug}&action=DELETE&id=${book.id}"><i class="far fa-trash-alt"></i></a>
                </td>
                <td>
                    <a href="authors?action=LOAD&id=${book.author.id}">${book.author.fullName}</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <a href="authors">Authors</a>

</div>

</body>
</html>
