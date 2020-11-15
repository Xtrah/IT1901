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

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(String name) {
        if (Validation.isValidStructureName(name)) {
            this.rooms.add(name);
        } else {
            throw new IllegalArgumentException("Room name is invalid!");
        }
    }
}
