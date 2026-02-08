package com.aikyn.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateVehicleRequest {
  @NotBlank
  private String type; // CAR or SUV
  @NotBlank
  private String brand;
  @NotBlank
  private String model;

  @NotNull
  @Positive
  private BigDecimal pricePerDay;

  public String getType() { return type; }
  public String getBrand() { return brand; }
  public String getModel() { return model; }
  public BigDecimal getPricePerDay() { return pricePerDay; }

  public void setType(String type) { this.type = type; }
  public void setBrand(String brand) { this.brand = brand; }
  public void setModel(String model) { this.model = model; }
  public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }
}
