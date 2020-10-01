package logger.core;

import java.time.LocalDateTime;

public class Visit {

    private String name;
    private String phone;
    private String building;
    private String room;
    private LocalDateTime from;
    private LocalDateTime to;

    public Visit() {
    }

    public Visit(String name, String phone, String building, String room, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.phone = phone;
        this.building = building;
        this.room = room;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getFrom() {
        return from.toString();
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public String getTo() {
        return to.toString();
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
