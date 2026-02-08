package com.aikyn.api.model;

public class Customer {
  private Integer id;
  private String fullName;
  private String phone;

  public Customer() {}

  public Customer(Integer id, String fullName, String phone) {
    this.id = id;
    this.fullName = fullName;
    this.phone = phone;
  }

  public Integer getId() { return id; }
  public String getFullName() { return fullName; }
  public String getPhone() { return phone; }

  public void setId(Integer id) { this.id = id; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public void setPhone(String phone) { this.phone = phone; }
}
