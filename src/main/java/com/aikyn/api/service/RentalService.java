package com.aikyn.api.service;

import com.aikyn.api.dto.CreateRentalRequest;
import com.aikyn.api.exception.BadRequestException;
import com.aikyn.api.exception.NotFoundException;
import com.aikyn.api.model.Rental;
import com.aikyn.api.model.RentalStatus;
import com.aikyn.api.patterns.RentalBuilder;
import com.aikyn.api.repository.RentalRepository;
import com.aikyn.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
  private final RentalRepository repo;
  private final VehicleRepository vehicleRepo;

  public RentalService(RentalRepository repo, VehicleRepository vehicleRepo) {
    this.repo = repo;
    this.vehicleRepo = vehicleRepo;
  }

  public List<Rental> getAll() {
    return repo.findAll();
  }

  public Rental getById(int id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Rental not found: " + id));
  }

  public int rent(CreateRentalRequest req) {
    // basic business rule: vehicle must exist
    vehicleRepo.findById(req.getVehicleId())
        .orElseThrow(() -> new NotFoundException("Vehicle not found: " + req.getVehicleId()));

    Rental r;
    try {
      r = new RentalBuilder()
          .vehicleId(req.getVehicleId())
          .customerId(req.getCustomerId())
          .days(req.getDays())
          .status(RentalStatus.ACTIVE)
          .build();
    } catch (IllegalStateException e) {
      throw new BadRequestException(e.getMessage());
    }

    return repo.create(r);
  }

  public void complete(int id) {
    Rental r = getById(id);
    if (r.getStatus() != RentalStatus.ACTIVE) {
      throw new BadRequestException("Only ACTIVE rentals can be completed");
    }
    repo.updateStatus(id, RentalStatus.COMPLETED);
  }

  public void cancel(int id) {
    Rental r = getById(id);
    if (r.getStatus() == RentalStatus.COMPLETED) {
      throw new BadRequestException("COMPLETED rentals cannot be canceled");
    }
    repo.updateStatus(id, RentalStatus.CANCELED);
  }

  public void delete(int id) {
    getById(id);
    repo.delete(id);
  }
}
