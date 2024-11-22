package com.org.test.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.org.test.jdbc.util.DBConnection;

public class App {
  public static void main(String[] args) {

    try (Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM productos");) {

      while (rs.next()) {
        System.out.print(rs.getInt("id"));
        System.out.print(" | ");
        System.out.print(rs.getString("nombre"));
        System.out.print(" | ");
        System.out.print(rs.getInt("precio"));
        System.out.print(" | ");
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
