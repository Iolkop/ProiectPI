package com.example.selecareoptionalandlogin;
import java.sql.*;

public class DataBase {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/selectareoptional";
    private static String user = "root";
    private static String password = "toor";
    public static Connection connection() {
        Connection cn = null;

        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);

            return cn;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
