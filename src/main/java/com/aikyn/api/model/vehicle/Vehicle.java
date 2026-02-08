package com.aikyn.api.model.vehicle;

import java.math.BigDecimal;

public abstract class Vehicle {
  protected Integer id;
  protected String brand;
  protected String model;
  protected BigDecimal pricePerDay;

  protected Vehicle(Integer id, String brand, String model, BigDecimal pricePerDay) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.pricePerDay = pricePerDay;
  }

  public Integer getId() { return id; }
  public String getBrand() { return brand; }
  public String getModel() { return model; }
  public BigDecimal getPricePerDay() { return pricePerDay; }

  public void setId(Integer id) { this.id = id; }
  public void setBrand(String brand) { this.brand = brand; }
  public void setModel(String model) { this.model = model; }
  public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }

  public abstract String getType();
}
