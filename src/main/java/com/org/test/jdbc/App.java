package com.org.test.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.org.test.jdbc.models.Product;
import com.org.test.jdbc.respository.ProductRepositoryImpl;
import com.org.test.jdbc.respository.Repository;
import com.org.test.jdbc.util.DBConnection;

public class App {
  public static void main(String[] args) {

    try (Connection conn = DBConnection.getConnection();) {
      Repository<Product> repo = new ProductRepositoryImpl();
      repo.list().forEach(System.out::println);

      System.out.println("Find by id");
      System.out.println(repo.byId(1L));
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
