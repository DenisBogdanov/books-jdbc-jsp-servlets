package ru.bogdanium.books.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

    public static void cleanUp(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
