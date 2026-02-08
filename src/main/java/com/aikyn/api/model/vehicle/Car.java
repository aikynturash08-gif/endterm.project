package com.aikyn.api.model.vehicle;

import java.math.BigDecimal;

public class Car extends Vehicle {
  private int seats;

  public Car(Integer id, String brand, String model, BigDecimal pricePerDay, int seats) {
    super(id, brand, model, pricePerDay);
    this.seats = seats;
  }

  public int getSeats() { return seats; }
  public void setSeats(int seats) { this.seats = seats; }

  @Override
  public String getType() { return "CAR"; }
}
