package com.aikyn.api.repository;

import com.aikyn.api.model.Rental;
import com.aikyn.api.model.RentalStatus;
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
public class RentalRepository {
  private final JdbcTemplate jdbc;

  public RentalRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  private static final RowMapper<Rental> MAPPER = (rs, rowNum) ->
      new Rental(
          rs.getInt("id"),
          rs.getInt("vehicle_id"),
          rs.getInt("customer_id"),
          rs.getInt("days"),
          RentalStatus.valueOf(rs.getString("status"))
      );

  public List<Rental> findAll() {
    return jdbc.query("SELECT * FROM rentals ORDER BY id", MAPPER);
  }

  public Optional<Rental> findById(int id) {
    var list = jdbc.query("SELECT * FROM rentals WHERE id=?", MAPPER, id);
    return list.stream().findFirst();
  }

  public int create(Rental r) {

    return jdbc.queryForObject(
            "INSERT INTO rentals(vehicle_id, customer_id, days, status) " +
                    "VALUES (?,?,?,?) RETURNING id",
            Integer.class,
            r.getVehicleId(),
            r.getCustomerId(),
            r.getDays(),
            r.getStatus().name()
    );
  }

  public void updateStatus(int id, RentalStatus status) {
    jdbc.update("UPDATE rentals SET status=? WHERE id=?", status.name(), id);
  }

  public void delete(int id) {
    jdbc.update("DELETE FROM rentals WHERE id=?", id);
  }
}
