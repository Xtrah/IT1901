package logger.core;

import java.util.List;

public class Building {

  private String name;
  private List<String> rooms;

  public Building() {
  }

  public Building(String name, List<String> rooms) {
    setName(name);
    setRooms(rooms);
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getName() {
    return name;
  }

  /**
   * Sets name of Building if name is valid. Else, throw Exception.
   *
   * @param name String
   */
  public void setName(String name) {
    if (Validation.isValidStructureName(name)) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Building name is invalid!");
    }
  }


  public List<String> getRooms() {
    return rooms;
  }

  /**
   * Sets rooms to Building. Overwrites previous rooms.
   *
   * @param rooms List of rooms to set
   */
  public void setRooms(List<String> rooms) {
    this.rooms = rooms;
  }

  /**
   * Adds room to Building if room-name is valid. Else, throw Exception.
   *
   * @param name String name of room to add to Building
   */
  public void addRoom(String name) {
    if (Validation.isValidStructureName(name)) {
      this.rooms.add(name);
    } else {
      throw new IllegalArgumentException("Room name is invalid!");
    }
  }
}
