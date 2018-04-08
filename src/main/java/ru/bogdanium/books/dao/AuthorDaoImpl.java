package ru.bogdanium.books.dao;

import ru.bogdanium.books.model.Author;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.bogdanium.books.dao.DbUtil.cleanUp;

public class AuthorDaoImpl implements AuthorDao {

    private static final String GET_ALL_AUTHORS = "SELECT * FROM author ORDER BY last_name";
    private static final String SQL_ADD_AUTHOR = "INSERT INTO author (first_name, last_name) VALUES (?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT first_name, last_name FROM author WHERE id = ?";

    private DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Author> getAll() {

        List<Author> authors = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(GET_ALL_AUTHORS)) {

            while (rs.next()) {

                Author author = new Author(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                authors.add(author);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return authors;
    }

    @Override
    public Author findById(Integer id) {

        ResultSet rs = null;
        Author author = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                author = new Author(
                        id,
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }
            return author;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp(rs);
        }
        return null;
    }

    @Override
    public boolean add(Author author) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_ADD_AUTHOR)) {

            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());

            return ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
