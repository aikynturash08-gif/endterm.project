package com.aikyn.api.controller;

import com.aikyn.api.dto.CreateVehicleRequest;
import com.aikyn.api.model.vehicle.Vehicle;
import com.aikyn.api.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

  private final VehicleService service;

  public VehicleController(VehicleService service) {
    this.service = service;
  }

  @GetMapping
  public List<Vehicle> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Vehicle getById(@PathVariable("id") int id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Object> create(@Valid @RequestBody CreateVehicleRequest req) {
    int id = service.create(req);
    return Map.of("id", id);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable("id") int id, @Valid @RequestBody CreateVehicleRequest req) {
    service.update(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") int id) {
    service.delete(id);
  }
}
