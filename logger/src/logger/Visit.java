package logger;

import java.util.Date;

public class Visit {

    private String name;
    private String phone;
    private String building;
    private String room;
    private Date fromTime;
    private Date toTime;

    public Visit(String name, String phone, String building, String room, Date fromTime, Date toTime) {
        this.name = name;
        this.phone = phone;
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

    public Date getFromDate() {
        return fromTime;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToDate() {
        return toTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }
}
