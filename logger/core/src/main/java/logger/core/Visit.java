package logger.core;

import java.time.LocalDateTime;
import java.util.UUID;

public class Visit {

  private String id;
  private String name;
  private String phone;
  private String building;
  private String room;
  private LocalDateTime from;
  private LocalDateTime to;

  /**
   * Constructs Visit.
   *
   * @param name String name of visitor
   * @param phone String phone of visitor
   * @param building String name of building that is visited
   * @param room String room name that was visited
   * @param from visit lasted String from ...
   * @param to visit lasted String to
   */
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
    this.id = UUID.randomUUID().toString();
  }

  public Visit(
      String id,
      String name,
      String phone,
      String building,
      String room,
      LocalDateTime from,
      LocalDateTime to) {
    this(name, phone, building, room, from, to);
    this.id = id;
  }

  public String getName() {
    return name;
  }

  /**
   * Sets name of Visit. Validates the name with a regex to check for illegal charagters.
   *
   * @param name String name to be set
   */
  public void setName(String name) {
    if (Validation.isValidName(name)) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Person name is invalid!");
    }
  }

  /**
   * Get phone number of visitor.
   *
   * @return phone phone number registered in Visit
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets phone number for visit.
   *
   * @param phone number to be set for Visit
   */
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

  /**
   * Sets Building that the Visit was registered for.
   *
   * @param building building that the Visit was registered for
   */
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

  /**
   * Sets room that the Visit was registered for.
   *
   * @param room room that the Visit was registered for
   */
  public void setRoom(String room) {
    if (Validation.isValidStructureName(room)) {
      this.room = room;
    } else {
      throw new IllegalArgumentException("Room name is invalid!");
    }
  }

  /**
   * Get the start time of the Visit.
   *
   * @return from returns the start time of the Visit
   */
  public LocalDateTime getFrom() {
    return from;
  }

  /**
   * Set tie of the start of the visit.
   *
   * @param from LocalDateTime start of Visit
   */
  public void setFrom(LocalDateTime from) {
    this.from = from;
  }

  /**
   * Get time of end of visit.
   *
   * @return to end of Visit
   */
  public LocalDateTime getTo() {
    return to;
  }

  /**
   *  Set time of start of Visit.
   *
   * @param to LocalDateTime time of the end of the Visit
   */
  public void setTo(LocalDateTime to) {
    this.to = to;
  }

  /**
   *  Get id of visit.
   *
   * @return id id of Visit
   */
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
