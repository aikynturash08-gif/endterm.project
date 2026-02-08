package com.aikyn.api.patterns;

import com.aikyn.api.model.Rental;
import com.aikyn.api.model.RentalStatus;

public final class RentalBuilder {
  private Integer vehicleId;
  private Integer customerId;
  private Integer days;
  private RentalStatus status = RentalStatus.ACTIVE;

  public RentalBuilder vehicleId(int vehicleId) {
    this.vehicleId = vehicleId;
    return this;
  }

  public RentalBuilder customerId(int customerId) {
    this.customerId = customerId;
    return this;
  }

  public RentalBuilder days(int days) {
    this.days = days;
    return this;
  }

  public RentalBuilder status(RentalStatus status) {
    this.status = status;
    return this;
  }

  public Rental build() {
    if (vehicleId == null) throw new IllegalStateException("vehicleId is required");
    if (customerId == null) throw new IllegalStateException("customerId is required");
    if (days == null || days <= 0) throw new IllegalStateException("days must be > 0");
    return new Rental(null, vehicleId, customerId, days, status);
  }
}
