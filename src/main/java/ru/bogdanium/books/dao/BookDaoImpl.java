package ru.bogdanium.books.dao;

import ru.bogdanium.books.model.Author;
import ru.bogdanium.books.model.Book;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private static final String SQL_GET_ALL_BOOKS = "" +
            " SELECT b.id, b.title, a.id, a.first_name, a.last_name" +
            " FROM book b" +
            " JOIN author a ON b.author_id = a.id" +
            " ORDER BY b.title";

    private static final String SQL_ADD_BOOK = "INSERT INTO book (title, author_id) VALUES(?, ?)";

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

                Book book = new Book
                        .Builder(rs.getString(2))
                        .id(rs.getInt(1))
                        .author(new Author(
                                rs.getInt(3),       // author.id
                                rs.getString(4),    // author.first_name
                                rs.getString(5)     // author.last_name
                        ))
                        .build();

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

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_BOOK)) {

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthorId());
            System.out.println("OK");

            return statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
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
