package com.aikyn.api.controller;

import com.aikyn.api.model.Customer;
import com.aikyn.api.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @GetMapping
  public List<Customer> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Customer getById(@PathVariable("id") int id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Object> create(@RequestBody Customer c) {
    int id = service.create(c);
    return Map.of("id", id);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable("id") int id, @RequestBody Customer c) {
    service.update(id, c);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") int id) {
    service.delete(id);
  }
}
