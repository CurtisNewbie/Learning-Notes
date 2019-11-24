package com.curtisnewbie.jdbcdemo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;

public class BasicConnection {

    public static Connection conn = null;

    public static void main(String[] args) {
        try {
            // 1. load drivers (implementation), throws ClassNotFoundException if no
            // implementation/jar is setup
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. DriverManager to connect to the database
            conn = DriverManager.getConnection(Config.dburl, Config.username, Config.password);

            // 3. Statement object to execute query
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM course";
            ResultSet resSet = stmt.executeQuery(query);

            // 4. Process result set
            ResultSetMetaData setMeta = resSet.getMetaData();

            // print column names
            for (int i = 1; i <= setMeta.getColumnCount(); i++) {
                System.out.printf("%-15s ", setMeta.getColumnName(i));
            }
            System.out.println();

            // print result
            while (resSet.next()) {
                System.out.printf("%-15s %-15s %-15d %-15s %-15d\n", resSet.getString(1), resSet.getString(2),
                        resSet.getInt(3), resSet.getString(4), resSet.getInt(5));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        System.exit(0);
    }
}
