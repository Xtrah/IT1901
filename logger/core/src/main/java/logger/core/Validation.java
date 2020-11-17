package logger.core;

import java.time.LocalDate;
import java.time.LocalTime;

public class Validation {

  /**
   * Checks whether strings are numbers between 0-23 and 0-59 respectively.
   *
   * @param hours   hours String to be validated
   * @param minutes minutes String to be validated
   * @return true if the String is a valid time, False otherwise
   */
  public static boolean isTimeString(String hours, String minutes) {
    String timeString = hours + ':' + minutes;
    // HH:MM. Check if hours are between 0-23 and minutes between 0-59
    // Must have leading zero.
    return timeString.matches("^([0-1][0-9]|2[0-3]):[0-5][0-9]$");
  }

  /**
   * Checks whether string matches a valid person name.
   * <li>No special characters</li>
   * <li>Min 2 characters</li>
   * <li>Max 40 characters</li>
   * <li>No numbers</li>
   *
   * @param name Name to be validated
   * @return true if name only contains legal characters and length, false otherwise
   */
  public static boolean isValidName(String name) {
    return name.matches("^[a-zA-ZæøåÆØÅ -]{1,40}$");
  }

  /**
   * Checks whether string matches a valid building/room name.
   * <li>No special characters</li>
   * <li>Min 2 characters</li>
   * <li>Max 40 characters</li>
   *
   * @param name Name to be validated
   * @return true if name only contains legal characters and length, false otherwise
   */
  public static boolean isValidStructureName(String name) {
    return name.matches("^[a-zA-ZæøåÆØÅ0-9\\-\\s]{2,40}$");
  }

  /**
   * Checks whether string matches a valid Norwegian number (8 numbers).
   *
   * @param text phone number String to be validated
   * @return true if phone number is in a legal format, false otherwise
   */
  public static boolean isValidPhone(String text) {
    return text.matches("^[0-9]{8}$");
  }

  /**
   * Checks that from is before to, chronologically.
   *
   * @param from start time
   * @param to   end time
   * @return true if no params are null and from is chronologically behind to, false otherwise
   */
  public static boolean fromIsBeforeTo(LocalTime from, LocalTime to) {
    if (from != null && to != null) {
      return from.isBefore(to);
    }
    return false;
  }

  /**
   * Validates date by checking if the date precedes or proceeds now.
   *
   * @param date date to validate
   * @return true if date is in the past or today, false otherwise
   */
  public static boolean isValidDate(LocalDate date) {
    return date != null && (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()));
  }

  /**
   * Formats hour- and minute-string to LocalTime.
   *
   * @param hour hour of day
   * @param min  minute of day
   * @return a LocalTime of hour and min
   */
  public static LocalTime formatToLocalTime(String hour, String min) {
    if (isTimeString(hour, min)) {
      return LocalTime.parse(hour + ":" + min);
    }
    return null;
  }

  /**
   * Checks if string is empty.
   *
   * @param str String to validate
   * @return true if the string is null or empty, false otherwise
   */
  public static boolean isEmptyString(String str) {
    return (str == null || str.trim().isEmpty());
  }
}
