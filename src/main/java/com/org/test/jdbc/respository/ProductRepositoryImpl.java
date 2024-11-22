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
        Product p = getProduct(rs);
        products.add(p);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }

  @Override
  public Product byId(Long id) {
    Product p = null;
    try (PreparedStatement ps = connection()
        .prepareStatement("SELECT * FROM productos WHERE id = ?");) {
      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery();) {
        if (rs.next()) {
          p = getProduct(rs);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return p;
  }
  private Product getProduct(ResultSet rs) throws SQLException {
    Product p = new Product();
    p.setId(rs.getLong("id"));
    p.setNombre(rs.getString("nombre"));
    p.setPrecio(rs.getInt("precio"));
    p.setFecha(rs.getDate("fecha"));
    return p;
  }
}
