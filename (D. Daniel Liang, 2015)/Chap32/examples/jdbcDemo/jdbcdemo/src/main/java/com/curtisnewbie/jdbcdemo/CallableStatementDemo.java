package com.curtisnewbie.jdbcdemo;

import java.sql.*;

public class CallableStatementDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(Config.dburl, Config.username, Config.password);) {

            // "INSERT INTO student (studentId, firstName, lastName) VALUES (2, 'Curtis',
            // 'Newbie')";

            String query = "CALL findStudent(?, ?, ?)";
            String fName = "curtis";
            String lName = "newbie";
            CallableStatement cstmt = conn.prepareCall(query);
            cstmt.setString(1, fName);
            cstmt.setString(2, lName);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();
            int id = cstmt.getInt(3);
            System.out.printf("%s %s id:%d", fName, lName, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}