package com.org.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/java_curso";
    try (Connection conn = DriverManager.getConnection(url);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM productos");) {

      while (rs.next()) {
        System.out.print(rs.getInt("id"));
        System.out.println(" | ");
        System.out.print(rs.getString("nombre"));
        System.out.println(" | ");
        System.out.print(rs.getInt("precio"));
        System.out.println(" | ");
        System.out.println(rs.getDate("fecha"));
      }

      rs.close();
      st.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
