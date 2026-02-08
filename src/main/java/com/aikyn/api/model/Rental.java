package com.aikyn.api.model;

public class Rental {
  private Integer id;
  private Integer vehicleId;
  private Integer customerId;
  private Integer days;
  private RentalStatus status;

  public Rental() {}

  public Rental(Integer id, Integer vehicleId, Integer customerId, Integer days, RentalStatus status) {
    this.id = id;
    this.vehicleId = vehicleId;
    this.customerId = customerId;
    this.days = days;
    this.status = status;
  }

  public Integer getId() { return id; }
  public Integer getVehicleId() { return vehicleId; }
  public Integer getCustomerId() { return customerId; }
  public Integer getDays() { return days; }
  public RentalStatus getStatus() { return status; }

  public void setId(Integer id) { this.id = id; }
  public void setVehicleId(Integer vehicleId) { this.vehicleId = vehicleId; }
  public void setCustomerId(Integer customerId) { this.customerId = customerId; }
  public void setDays(Integer days) { this.days = days; }
  public void setStatus(RentalStatus status) { this.status = status; }
}
