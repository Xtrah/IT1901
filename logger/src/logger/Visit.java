package logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Visit {

    private String name;
    private String phoneNumber;
    private String building;
    private String room;
    private LocalDateTime time;

    public Visit(String name, String phoneNumber, String building, String room, LocalDateTime time) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.building = building;
        this.room = room;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDate getDate() {
        return time.toLocalDate();
    }

    public LocalTime getTime() {
        return time.toLocalTime();
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
