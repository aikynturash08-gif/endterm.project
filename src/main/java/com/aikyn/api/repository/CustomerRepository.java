package com.aikyn.api.repository;

import com.aikyn.api.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
  private final JdbcTemplate jdbc;

  public CustomerRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  private static final RowMapper<Customer> MAPPER = (rs, rowNum) ->
      new Customer(rs.getInt("id"), rs.getString("full_name"), rs.getString("phone"));

  public List<Customer> findAll() {
    return jdbc.query("SELECT * FROM customers ORDER BY id", MAPPER);
  }

  public Optional<Customer> findById(int id) {
    var list = jdbc.query("SELECT * FROM customers WHERE id=?", MAPPER, id);
    return list.stream().findFirst();
  }

  public int create(Customer c) {
    KeyHolder kh = new GeneratedKeyHolder();
    jdbc.update(con -> {
      PreparedStatement ps = con.prepareStatement(
          "INSERT INTO customers(full_name, phone) VALUES (?,?)",
          Statement.RETURN_GENERATED_KEYS
      );
      ps.setString(1, c.getFullName());
      ps.setString(2, c.getPhone());
      return ps;
    }, kh);
    Number id = kh.getKey();
    return id == null ? -1 : id.intValue();
  }

  public void update(int id, Customer c) {
    jdbc.update("UPDATE customers SET full_name=?, phone=? WHERE id=?",
        c.getFullName(), c.getPhone(), id);
  }

  public void delete(int id) {
    jdbc.update("DELETE FROM customers WHERE id=?", id);
  }
}
