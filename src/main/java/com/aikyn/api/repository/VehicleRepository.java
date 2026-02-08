package com.aikyn.api.repository;

import com.aikyn.api.model.vehicle.Car;
import com.aikyn.api.model.vehicle.Suv;
import com.aikyn.api.model.vehicle.Vehicle;
import com.aikyn.api.utils.AppLogger;
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
public class VehicleRepository {

  private final JdbcTemplate jdbc;
  private final AppLogger log = AppLogger.getInstance(); // explicit Singleton usage

  public VehicleRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  private static final RowMapper<Vehicle> MAPPER = (rs, rowNum) -> {
    String type = rs.getString("type");
    return switch (type) {
      case "CAR" -> new Car(
          rs.getInt("id"),
          rs.getString("brand"),
          rs.getString("model"),
          rs.getBigDecimal("price_per_day"),
          rs.getInt("seats")
      );
      case "SUV" -> new Suv(
          rs.getInt("id"),
          rs.getString("brand"),
          rs.getString("model"),
          rs.getBigDecimal("price_per_day"),
          rs.getBoolean("awd")
      );
      default -> throw new IllegalStateException("Unknown vehicle type in DB: " + type);
    };
  };

  public List<Vehicle> findAll() {
    return jdbc.query("SELECT * FROM vehicles ORDER BY id", MAPPER);
  }

  public Optional<Vehicle> findById(int id) {
    var list = jdbc.query("SELECT * FROM vehicles WHERE id = ?", MAPPER, id);
    return list.stream().findFirst();
  }

  public int create(Vehicle v) {
    KeyHolder kh = new GeneratedKeyHolder();
    jdbc.update(con -> {
      PreparedStatement ps = con.prepareStatement(
          "INSERT INTO vehicles(type, brand, model, price_per_day, seats, awd) VALUES (?,?,?,?,?,?)",
          Statement.RETURN_GENERATED_KEYS
      );
      ps.setString(1, v.getType());
      ps.setString(2, v.getBrand());
      ps.setString(3, v.getModel());
      ps.setBigDecimal(4, v.getPricePerDay());

      if (v instanceof Car car) {
        ps.setInt(5, car.getSeats());
        ps.setNull(6, java.sql.Types.BOOLEAN);
      } else if (v instanceof Suv suv) {
        ps.setNull(5, java.sql.Types.INTEGER);
        ps.setBoolean(6, suv.isAwd());
      } else {
        ps.setNull(5, java.sql.Types.INTEGER);
        ps.setNull(6, java.sql.Types.BOOLEAN);
      }
      return ps;
    }, kh);

    Number id = kh.getKey();
    int newId = id == null ? -1 : id.intValue();
    log.info("Vehicle created id=" + newId);
    return newId;
  }

  public void update(int id, Vehicle v) {
    int updated = jdbc.update(
        "UPDATE vehicles SET brand=?, model=?, price_per_day=?, seats=?, awd=? WHERE id=?",
        v.getBrand(),
        v.getModel(),
        v.getPricePerDay(),
        (v instanceof Car car) ? car.getSeats() : null,
        (v instanceof Suv suv) ? suv.isAwd() : null,
        id
    );
    if (updated == 0) throw new IllegalStateException("Vehicle not found for update: " + id);
  }

  public void delete(int id) {
    jdbc.update("DELETE FROM vehicles WHERE id=?", id);
  }
}
