package com.aikyn.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateRentalRequest {
  @NotNull
  private Integer vehicleId;

  @NotNull
  private Integer customerId;

  @NotNull
  @Positive
  private Integer days;

  public Integer getVehicleId() { return vehicleId; }
  public Integer getCustomerId() { return customerId; }
  public Integer getDays() { return days; }

  public void setVehicleId(Integer vehicleId) { this.vehicleId = vehicleId; }
  public void setCustomerId(Integer customerId) { this.customerId = customerId; }
  public void setDays(Integer days) { this.days = days; }
}
