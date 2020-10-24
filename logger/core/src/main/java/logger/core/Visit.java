package logger.core;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Visit {

    private String name;
    private String phone;
    private String building;
    private String room;
    private LocalDateTime from;
    private LocalDateTime to;
    private final String id = UUID.randomUUID().toString();

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

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Visit)) return false;
        Visit other = (Visit) obj;

        return this.getName().equals(other.getName())
                && this.getPhone().equals(other.getPhone())
                && this.getBuilding().equals(other.getBuilding())
                && this.getRoom().equals(other.getRoom())
                && this.getTo().equals(other.getTo())
                && this.getFrom().equals(other.getFrom());
    }

    @Override
    public int hashCode() {
        return 41;
    }
}
