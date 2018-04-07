package ru.bogdanium.books.dao;

import ru.bogdanium.books.model.Author;
import ru.bogdanium.books.model.Book;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private static final String SQL_GET_ALL_BOOKS = "" +
            " SELECT b.id, b.title, a.id, a.first_name, a.last_name" +
            " FROM book b" +
            " JOIN author a ON b.author_id = a.id";

    private DataSource dataSource;

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> getAll() {

        List<Book> books = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_GET_ALL_BOOKS)) {

            while (rs.next()) {

                Book book = new Book(
                        rs.getInt(1),               // book.id
                        rs.getString(2),            // book.title
                        new Author(
                                rs.getInt(3),       // author.id
                                rs.getString(4),    // author.first_name
                                rs.getString(5)     // author.last_name
                        )
                );

                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return books;
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return null;
    }

    @Override
    public Book getById(Integer id) {
        return null;
    }

    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public boolean update(Book book) {
        return false;
    }

    @Override
    public boolean delete(Book book) {
        return false;
    }
}
