package com.aikyn.api.service;

import com.aikyn.api.dto.CreateVehicleRequest;
import com.aikyn.api.exception.BadRequestException;
import com.aikyn.api.exception.NotFoundException;
import com.aikyn.api.model.vehicle.Vehicle;
import com.aikyn.api.patterns.VehicleFactory;
import com.aikyn.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import com.aikyn.api.cache.VehicleCache;
import java.util.List;

@Service
public class VehicleService {
  private final VehicleRepository repo;

  public VehicleService(VehicleRepository repo) {
    this.repo = repo;
  }

  public List<Vehicle> getAll() {

    VehicleCache cache = VehicleCache.getInstance();

    if (!cache.isEmpty()) {
      return cache.get();
    }

    List<Vehicle> vehicles = repo.findAll();
    cache.put(vehicles);

    return vehicles;
  }


  public Vehicle getById(int id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Vehicle not found: " + id));
  }

  public int create(CreateVehicleRequest req) {

    Vehicle v = VehicleFactory.create(
            req.getType(),
            req.getBrand(),
            req.getModel(),
            req.getPricePerDay()
    );

    int id = repo.create(v);

    VehicleCache.getInstance().clear();

    return id;
  }


  public void update(int id, CreateVehicleRequest req) {

    Vehicle current = getById(id);

    Vehicle updated = VehicleFactory.create(
            current.getType(),
            req.getBrand(),
            req.getModel(),
            req.getPricePerDay()
    );

    repo.update(id, updated);

    VehicleCache.getInstance().clear();
  }


  public void delete(int id) {

    getById(id);
    repo.delete(id);

    VehicleCache.getInstance().clear();
  }
}

