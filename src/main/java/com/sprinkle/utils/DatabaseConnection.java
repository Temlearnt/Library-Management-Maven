package com.sprinkle.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library_management";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Pastikan driver ter-load
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi database berhasil!");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver MySQL tidak ditemukan!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Koneksi database gagal!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Reset instance
                System.out.println("Koneksi database ditutup.");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database!");
                e.printStackTrace();
            }
        }
    }
}
