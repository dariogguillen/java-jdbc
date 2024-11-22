package com.org.test.jdbc.respository;

import java.util.List;

public interface Repository<T> {

  List<T> list();

  T byId(Long id);

  void save(T t); // To save and update

  void delete(Long id);

}
