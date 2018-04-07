package ru.bogdanium.books.controller;

import ru.bogdanium.books.dao.AuthorDao;
import ru.bogdanium.books.dao.AuthorDaoImpl;
import ru.bogdanium.books.dao.BookDao;
import ru.bogdanium.books.dao.BookDaoImpl;
import ru.bogdanium.books.model.Author;
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

@WebServlet("/books")
public class BookControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 6247337045885715144L;

    private BookDao bookDao;
    private AuthorDao authorDao;

    @Resource(name = "jdbc/books-jdbc-jsp-servlets")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDao = new BookDaoImpl(dataSource);
        authorDao = new AuthorDaoImpl(dataSource);
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
                getAllBooks(request, response);
                break;
            case "ADD":
                addBook(request, response);
                break;
            case "LOAD":
                loadBook(request, response);
                break;
            default:
                getAllBooks(request, response);
                break;
        }
    }

    private void loadBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Book book = getBookByIdFromRequest(request);
        List<Author> authors = authorDao.getAll();

        request.setAttribute("book", book);
        request.setAttribute("authors", authors);

        request.getRequestDispatcher("book.jsp").forward(request, response);
    }

    private void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookDao.getAll();
        List<Author> authors = authorDao.getAll();

        request.setAttribute("books", books);
        request.setAttribute("authors", authors);

        RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
        dispatcher.forward(request, response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("author-id"));

        Book book = new Book.Builder(title)
                .authorId(authorId)
                .build();

        bookDao.add(book);
        getAllBooks(request, response);
    }

    private Book getBookByIdFromRequest(HttpServletRequest request) throws ServletException {
        Book book;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            book = bookDao.findById(id);
            if (book == null) {
                throw new ServletException("There is no book with such id.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Could not find book with id=" + request.getParameter("id"));
        }
        return book;
    }

}
