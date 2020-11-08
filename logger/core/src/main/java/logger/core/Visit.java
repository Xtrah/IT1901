package logger.core;

import java.time.LocalDateTime;
import java.util.UUID;

public class Visit {

  private final String id = UUID.randomUUID().toString();
  private String name;
  private String phone;
  private String building;
  private String room;
  private LocalDateTime from;
  private LocalDateTime to;

  public Visit() {
  }

  public Visit(
      String name,
      String phone,
      String building,
      String room,
      LocalDateTime from,
      LocalDateTime to) {
    setName(name);
    setPhone(phone);
    setBuilding(building);
    setRoom(room);
    setFrom(from);
    setTo(to);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (Validation.isValidName(name)) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Person name is invalid!");
    }
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    if (Validation.isValidPhone(phone)) {
      this.phone = phone;
    } else {
      throw new IllegalArgumentException("Phone number is invalid!");
    }
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    if (Validation.isValidStructureName(building)) {
      this.building = building;
    } else {
      throw new IllegalArgumentException("Building name is invalid!");
    }
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    if (Validation.isValidStructureName(room)) {
      this.room = room;
    } else {
      throw new IllegalArgumentException("Room name is invalid!");
    }
  }

  public LocalDateTime getFrom() {
    return from;
  }

  public void setFrom(LocalDateTime from) {
      this.from = from;
  }

  public LocalDateTime getTo() {
    return to;
  }

  public void setTo(LocalDateTime to) {
    this.to = to;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Visit{"
        + "name='"
        + name
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", building='"
        + building
        + '\''
        + ", room='"
        + room
        + '\''
        + ", from="
        + from
        + ", to="
        + to
        + ", id='"
        + id
        + '\''
        + '}';
  }
}
