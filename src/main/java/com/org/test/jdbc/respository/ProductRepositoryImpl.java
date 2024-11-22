package com.org.test.jdbc.respository;

import java.sql.Connection;
import java.sql.Date;
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

  @Override
  public void save(Product p) {
    String sql;
    if (p.getId() != null && p.getId() > 0) {
      sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";
    } else {
      sql = "INSERT INTO productos ( nombre, precio, fecha) VALUES (?, ?, ?)";
    }
    try (PreparedStatement ps = connection().prepareStatement(sql)) {
      ps.setString(1, p.getNombre());
      ps.setInt(2, p.getPrecio());
      if (p.getId() != null && p.getId() > 0) {
        ps.setLong(3, p.getId());
      } else {
        ps.setDate(3, new Date(p.getFecha().getTime()));
      }

      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Long id) {
    try (PreparedStatement ps = connection().prepareStatement("DELETE FROM productos WHERE id = ?")) {
      ps.setLong(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

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
