package ru.bogdanium.books.controller;

import ru.bogdanium.books.dao.AuthorDao;
import ru.bogdanium.books.dao.AuthorDaoImpl;
import ru.bogdanium.books.dao.BookDao;
import ru.bogdanium.books.dao.BookDaoImpl;
import ru.bogdanium.books.model.Author;
import ru.bogdanium.books.model.Book;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

public class AuthorControllerServlet extends HttpServlet {
    private static final long serialVersionUID = -2846877417250665110L;

    private BookDao bookDao;
    private AuthorDao authorDao;

    @Resource(name = "jdbc/books-jdbc-jsp-servlets")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        authorDao = new AuthorDaoImpl(dataSource);
        bookDao = new BookDaoImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "LIST";
        }

        switch (action) {
            case "LIST":
                getAllAuthors(request, response);
                break;
            case "ADD":
                addAuthor(request, response);
                break;
            case "LOAD":
                loadAuthor(request, response);
                break;
//            case "DELETE":
//                deleteBook(request, response);
//                break;
            default:
                getAllAuthors(request, response);
                break;
        }
    }

    private void loadAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = getAuthorByIdFromRequest(request);
        System.out.println("author=" + author);
        List<Book> books = bookDao.findByAuthor(author);

        request.setAttribute("author", author);
        request.setAttribute("books", books);

        request.getRequestDispatcher("author.jsp").forward(request, response);
    }

    private void getAllAuthors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors = authorDao.getAll();

        request.setAttribute("authors", authors);

        request.getRequestDispatcher("authors.jsp")
                .forward(request, response);
    }

    private void addAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");

        authorDao.add(new Author(firstName, lastName));

        getAllAuthors(request, response);
    }

    private Author getAuthorByIdFromRequest(HttpServletRequest request) throws ServletException {
        Author author;
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            author = authorDao.findById(id);
            if (author == null) {
                throw new ServletException("There is no author with id=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Could not find author with id=" + request.getParameter("id"));
        }
        return author;
    }
}
