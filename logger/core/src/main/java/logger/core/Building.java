package logger.core;

import java.util.ArrayList;
import java.util.List;

public class Building {
    String name;



    List<String> rooms;

    public Building(String name, List<String> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public List<String> getRooms() {
        return rooms;
    }
}
