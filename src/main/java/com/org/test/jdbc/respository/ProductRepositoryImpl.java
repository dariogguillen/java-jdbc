package com.org.test.jdbc.respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.org.test.jdbc.models.Product;
import com.org.test.jdbc.util.DBConnection;

public class ProductRepositoryImpl implements Repository<Product> {

  private Connection connection() throws SQLException {
    return DBConnection.getConnection();
  }

  @Override
  public List<Product> list() {
    List<Product> products = new ArrayList<>();
    try (Statement st = connection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM productos")) {
      while (rs.next()) {
        Product p = new Product();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFecha(rs.getDate("fecha"));
        products.add(p);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }
}
