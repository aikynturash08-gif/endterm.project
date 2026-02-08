package com.aikyn.api.controller;

import com.aikyn.api.dto.CreateRentalRequest;
import com.aikyn.api.model.Rental;
import com.aikyn.api.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  private final RentalService service;

  public RentalController(RentalService service) {
    this.service = service;
  }

  @GetMapping
  public List<Rental> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Rental getById(@PathVariable("id") int id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Object> rent(@Valid @RequestBody CreateRentalRequest req) {
    int id = service.rent(req);
    return Map.of("id", id);
  }

  @PostMapping("/{id}/complete")
  public void complete(@PathVariable("id") int id) {
    service.complete(id);
  }

  @PostMapping("/{id}/cancel")
  public void cancel(@PathVariable("id") int id) {
    service.cancel(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable int id) {
    service.delete(id);
  }
}
