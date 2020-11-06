package logger.core;

import java.util.List;

public class Building {

  private String name;
  private List<String> rooms;

  public Building() {
  }

  public Building(String name, List<String> rooms) {
    this.name = name;
    this.rooms = rooms;
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getRooms() {
    return rooms;
  }

  public void setRooms(List<String> rooms) {
    this.rooms = rooms;
  }

  public void addRoom(String room){
    this.rooms.add(room);
  }
}
