package logger.fxui.validation;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitValidation {

  /**
   * @param hours hours String to be validated
   * @param minutes minutes String to be validated
   * @return true if the String is a valid time, False otherwise
   */
  public static boolean isTimeString(String hours, String minutes) {
    String timeString = hours + ':' + minutes;
    // Check if hours are between 0-23 and minutes between 0-59
    return timeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
  }

  /**
   * @param text Name to be validated
   * @return true if name only contains legal characters, false otherwise
   */
  public static boolean isValidName(String text) {
    return text.matches("^[a-zA-ZæøåÆØÅ ]*$");
  }

  /**
   * @param text phone number String to be validated
   * @return true if phone number is in a legal format, false otherwise
   */
  public static boolean isValidPhone(String text) {
    return text.matches("^[0-9]{8}$");
  }

  /**
   * @param from start time
   * @param to end time
   * @return true if none of the parameters are null and from is chronologically behind to, false
   *     otherwise
   */
  public static boolean isValidTime(LocalTime from, LocalTime to) {
    if (from != null && to != null) {
      return from.isBefore(to);
    }
    return false;
  }

  /**
   * @param date date to validate
   * @return true if date is in the past or today, false otherwise
   */
  public static boolean isValidDate(LocalDate date) {
    return date != null && (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()));
  }

  /**
   * @param hour hour of day
   * @param min minute of day
   * @return a LocalTime of hour and min
   */
  public static LocalTime formatToLocalTime(String hour, String min) {
    if (isTimeString(hour, min)) {
      return LocalTime.parse(hour + ":" + min);
    }
    return null;
  }

  /**
   * @param str String to validate
   * @return true if the string is empty, false otherwise
   */
  public static boolean isEmptyString(String str) {
    return (str == null || str.trim().isEmpty());
  }
}
