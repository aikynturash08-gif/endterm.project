package com.aikyn.api.service;

import com.aikyn.api.dto.CreateVehicleRequest;
import com.aikyn.api.exception.BadRequestException;
import com.aikyn.api.exception.NotFoundException;
import com.aikyn.api.model.vehicle.Vehicle;
import com.aikyn.api.patterns.VehicleFactory;
import com.aikyn.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
  private final VehicleRepository repo;

  public VehicleService(VehicleRepository repo) {
    this.repo = repo;
  }

  public List<Vehicle> getAll() {
    return repo.findAll();
  }

  public Vehicle getById(int id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Vehicle not found: " + id));
  }

  public int create(CreateVehicleRequest req) {
    try {
      Vehicle v = VehicleFactory.create(req.getType(), req.getBrand(), req.getModel(), req.getPricePerDay());
      return repo.create(v);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  public void update(int id, CreateVehicleRequest req) {
    Vehicle current = getById(id); // ensures exists
    Vehicle updated;
    try {
      updated = VehicleFactory.create(current.getType(), req.getBrand(), req.getModel(), req.getPricePerDay());
    } catch (IllegalArgumentException e) {
      throw new BadRequestException(e.getMessage());
    }
    repo.update(id, updated);
  }

  public void delete(int id) {
    getById(id);
    repo.delete(id);
  }
}
