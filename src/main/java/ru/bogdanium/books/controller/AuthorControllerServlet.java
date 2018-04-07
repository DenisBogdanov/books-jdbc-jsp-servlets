package ru.bogdanium.books.controller;

import ru.bogdanium.books.dao.AuthorDao;
import ru.bogdanium.books.dao.AuthorDaoImpl;
import ru.bogdanium.books.dao.BookDao;
import ru.bogdanium.books.model.Author;

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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Author> authors = authorDao.getAll();

        request.setAttribute("authors", authors);

        request.getRequestDispatcher("authors.jsp")
                .forward(request, response);
    }
}
