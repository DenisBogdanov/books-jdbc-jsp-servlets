package ru.bogdanium.books.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/test")
public class TestConnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1728110029435133169L;

    private static final String SQL_GET_AUTHORS = "SELECT * FROM author";

    // Define datasource/connection pool for resource injection
    @Resource(name = "jdbc/books-jdbc-jsp-servlets")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_GET_AUTHORS)) {

            while (rs.next()) {
                out.println(rs.getString("last_name"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
