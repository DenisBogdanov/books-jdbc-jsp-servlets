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

    <h2>Add New Author</h2>
    <form action="authors" method="post">

        <table>
            <input type="hidden" name="action" value="ADD">
            <tr>
                <td>
                    <label for="first-name">First name:</label>
                </td>
                <td>
                    <input type="text" name="first-name" id="first-name">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="last-name">Last name:</label>
                </td>
                <td>
                    <input type="text" name="last-name" id="last-name">
                </td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="Add">
                </td>
            </tr>

        </table>

    </form>

    <ul>
        <c:forEach var="author" items="${authors}">
            <li>
                <a href="authors?action=LOAD&id=${author.id}">${author.firstName} ${author.lastName}</a>
            </li>
        </c:forEach>
    </ul>

    <c:import url="includes/footer.jsp"/>
