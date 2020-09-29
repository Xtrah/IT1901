package logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public static boolean isTimeString(String hours, String minutes) {
        String timeString = hours + ':' + minutes;
        // Check if hours are between 0-23 and minutes between 0-59
        return timeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }

    public static boolean isValidName(String text) {
        return text.matches("^[a-zA-ZæøåÆØÅ ]*$");
    }

    public static boolean isValidPhone(String text) {
        return text.matches("^[0-9]{8}$");
    }

    public static boolean isValidTime (LocalTime from, LocalTime to) {
        if (from != null && to != null){
            return from.isBefore(to);
        }
        return false;
    }

    public static LocalTime formatToLocalTime(String hour, String min) {
        if (Visit.isTimeString(hour, min)){
            return LocalTime.of(Integer.parseInt(hour), Integer.parseInt(min));
        }
        return null;
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
