package logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Visit {

    private String name;
    private String phoneNumber;
    private String building;
    private String room;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    public Visit(String name, String phoneNumber, String building, String room, LocalDateTime fromTime, LocalDateTime toTime) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.building = building;
        this.room = room;
        this.fromTime = fromTime;
        this.toTime = toTime;
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

    public LocalDate getFromDate() {
        return fromTime.toLocalDate();
    }

    public LocalTime getFromTime() {
        return fromTime.toLocalTime();
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDate getToDate() {
        return toTime.toLocalDate();
    }

    public LocalTime getToTime() {
        return toTime.toLocalTime();
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                '}';
    }
}
