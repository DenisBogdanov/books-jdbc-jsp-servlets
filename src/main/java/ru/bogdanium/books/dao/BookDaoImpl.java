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

    private static final String SQL_GET_BOOK_BY_ID = "" +
            " SELECT b.title, a.id, a.first_name, a.last_name" +
            " FROM book b" +
            " JOIN author a ON b.author_id = a.id" +
            " WHERE b.id = ?";

    private static final String SQL_UPDATE_BOOK = "UPDATE book SET title = ?, author_id = ? WHERE id = ?";

    private static final String SQL_DELETE_BOOK = "DELETE FROM book WHERE id = ?";

    private static final String SQL_SEARCH_BOOKS = "" +
            " SELECT b.id, b.title, a.id, a.first_name, a.last_name" +
            " FROM book b" +
            " JOIN author a ON b.author_id = a.id" +
            " WHERE b.title LIKE ?";

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
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> searchByPattern(String pattern) {
        List<Book> books = new ArrayList<>();
        ResultSet rs = null;

        if (pattern == null || pattern.trim().length() == 0) {
            return getAll();
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_BOOKS)) {

            ps.setString(1, "%" + pattern + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book.Builder(rs.getString(2))
                        .id(1)
                        .author(new Author(
                                rs.getInt(3),       // author.id
                                rs.getString(4),    // author.first_name
                                rs.getString(5)     // author.last_name
                        ))
                        .build();

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp(rs);
        }

        return books;
    }

    @Override
    public Book findById(Integer id) {

        Book book = null;
        ResultSet rs = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_BOOK_BY_ID)) {

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                book = new Book.Builder(rs.getString(1))
                        .id(id)
                        .author(new Author(
                                rs.getInt(2),       // author.id
                                rs.getString(3),    // author.first_name
                                rs.getString(4)     // author.last_name
                        ))
                        .build();
            }
            return book;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp(rs);
        }
        return null;
    }

    @Override
    public boolean add(Book book) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_ADD_BOOK)) {

            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getAuthorId());

            return ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean update(Book book) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BOOK)) {

            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getAuthorId());
            ps.setInt(3, book.getId());

            return ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BOOK)) {

            ps.setInt(1, book.getId());

            return ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void cleanUp(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
