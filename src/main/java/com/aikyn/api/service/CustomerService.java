package com.aikyn.api.service;

import com.aikyn.api.exception.NotFoundException;
import com.aikyn.api.model.Customer;
import com.aikyn.api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  private final CustomerRepository repo;

  public CustomerService(CustomerRepository repo) {
    this.repo = repo;
  }

  public List<Customer> getAll() {
    return repo.findAll();
  }

  public Customer getById(int id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Customer not found: " + id));
  }

  public int create(Customer c) {
    return repo.create(c);
  }

  public void update(int id, Customer c) {
    getById(id);
    repo.update(id, c);
  }

  public void delete(int id) {
    getById(id);
    repo.delete(id);
  }
}
