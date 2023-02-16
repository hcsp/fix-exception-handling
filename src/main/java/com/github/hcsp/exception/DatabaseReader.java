package com.github.hcsp.exception;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseReader {
    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        String jdbcUrl = "jdbc:h2:file:" + new File(projectDir, "test").getAbsolutePath();
        System.out.println(jdbcUrl);
        PreparedStatement statement;
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            statement = connection.prepareStatement("select * from PULL_REQUESTS where number > ?");
            statement.setInt(1, 0);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1)
                                + " "
                                + resultSet.getString(2)
                                + " "
                                + resultSet.getString(2));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
