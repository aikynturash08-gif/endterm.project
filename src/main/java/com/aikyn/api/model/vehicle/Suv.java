package com.aikyn.api.model.vehicle;

import java.math.BigDecimal;

public class Suv extends Vehicle {
  private boolean awd;

  public Suv(Integer id, String brand, String model, BigDecimal pricePerDay, boolean awd) {
    super(id, brand, model, pricePerDay);
    this.awd = awd;
  }

  public boolean isAwd() { return awd; }
  public void setAwd(boolean awd) { this.awd = awd; }

  @Override
  public String getType() { return "SUV"; }
}
