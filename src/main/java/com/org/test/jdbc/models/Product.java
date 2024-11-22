package com.org.test.jdbc.models;

import java.util.Date;

public class Product {

  private Long id;
  private String nombre;
  private Integer precio;
  private Date fecha;

  public Product() {
  }

  public Product(Long id, String nombre, Integer precio, Date fecha) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.fecha = fecha;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getPrecio() {
    return precio;
  }

  public void setPrecio(Integer precio) {
    this.precio = precio;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", fecha=" + fecha + "]";
  }

}
