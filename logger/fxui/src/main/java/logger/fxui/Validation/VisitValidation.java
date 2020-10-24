package logger.fxui;

import java.time.LocalTime;

public class Validation {

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
        if (isTimeString(hour, min)){
            return LocalTime.of(Integer.parseInt(hour), Integer.parseInt(min));
        }
        return null;
    }

}
