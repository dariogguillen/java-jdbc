package com.org.test.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private static String url = "jdbc:mysql://localhost:3306/java_curso";

  private static Connection conn;

  public static Connection getConnection() throws SQLException {
    if (conn == null) {
      conn = DriverManager.getConnection(url);
    }
    return conn;
  }
}
