package com.curtisnewbie.jdbcdemo;

import java.sql.*;

public class PreparedStatementDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        // insert some random data
        insertData();

        // retrieve a row where its courseNumber is 4333
        retrieveTuple(4333);

        System.exit(0);
    }

    public static void insertData() {
        try (Connection conn = DriverManager.getConnection(Config.dburl, Config.username, Config.password);) {
            String insertQuery = "INSERT INTO course VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, "1237");
            stmt.setString(2, "DBA");
            stmt.setInt(3, 4352);
            stmt.setString(4, "Database Management");
            stmt.setInt(5, 4);
            stmt.executeUpdate();

            stmt.setString(1, "1238");
            stmt.setString(2, "DS");
            stmt.setInt(3, 4355);
            stmt.setString(4, "Distributed System");
            stmt.setInt(5, 3);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveTuple(int courseNumber) {
        try (Connection conn = DriverManager.getConnection(Config.dburl, Config.username, Config.password);) {

            // Parameterized Query
            String query = "SELECT * FROM course WHERE courseNumber = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, courseNumber);

            ResultSet resultSet = stmt.executeQuery();
            ResultSetMetaData setMeta = resultSet.getMetaData();
            for (int i = 1; i <= setMeta.getColumnCount(); i++) {
                System.out.printf("%-15s ", setMeta.getColumnName(i));
            }
            System.out.println();

            while (resultSet.next()) {
                System.out.printf("%-15s %-15s %-15d %-15s %-15d\n", resultSet.getString(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}