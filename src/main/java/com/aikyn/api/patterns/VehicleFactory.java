package com.aikyn.api.patterns;

import com.aikyn.api.model.vehicle.Car;
import com.aikyn.api.model.vehicle.Suv;
import com.aikyn.api.model.vehicle.Vehicle;

import java.math.BigDecimal;

public final class VehicleFactory {
  private VehicleFactory() {}

  public static Vehicle create(String type, String brand, String model, BigDecimal pricePerDay) {
    if (type == null) throw new IllegalArgumentException("type is required");
    return switch (type.trim().toUpperCase()) {
      case "CAR" -> new Car(null, brand, model, pricePerDay, 4);
      case "SUV" -> new Suv(null, brand, model, pricePerDay, true);
      default -> throw new IllegalArgumentException("Unsupported vehicle type: " + type);
    };
  }
}
