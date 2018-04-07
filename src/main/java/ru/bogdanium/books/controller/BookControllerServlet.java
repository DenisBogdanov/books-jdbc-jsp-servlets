package ru.bogdanium.books.controller;

import ru.bogdanium.books.dao.BookDao;
import ru.bogdanium.books.dao.BookDaoImpl;
import ru.bogdanium.books.model.Book;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class BookControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 6247337045885715144L;

    private BookDao bookDao;

    @Resource(name = "jdbc/books-jdbc-jsp-servlets")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDao = new BookDaoImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        List<Book> books = bookDao.getAll();

        request.setAttribute("books", books);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/books.jsp");
        dispatcher.forward(request, response);

    }
}
