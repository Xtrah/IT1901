package logger.core;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter {

  /**
   * Filters visits based on predicate.
   *
   * @param pred      which filter
   * @param allVisits visits to filter
   * @return a list of visits that matches the filter
   */
  private static List<Visit> filterVisits(Predicate<Visit> pred, List<Visit> allVisits) {
    return allVisits.stream().filter(pred).collect(Collectors.toList());
  }

  /**
   * Filters visits based on name.
   *
   * @param searchInput name to search for
   * @param allVisits   visits to filter
   * @return a list of visits that matches the filter
   */
  public static List<Visit> filterByName(String searchInput, List<Visit> allVisits) {
    return filterVisits(visit -> visit.getName().toLowerCase().contains(searchInput.toLowerCase()),
        allVisits);
  }

  /**
   * Filters visits by phone number.
   *
   * @param searchInput phone to search for
   * @param allVisits   visits to filter
   * @return a list of visits that matches the filter
   */
  public static List<Visit> filterByPhone(String searchInput, List<Visit> allVisits) {
    return filterVisits(visit -> visit.getPhone().contains(searchInput.toLowerCase()), allVisits);
  }

  /**
   * Filters visits by Building name.
   *
   * @param searchInput building to search for
   * @param allVisits   visits to filter
   * @return a list of visits that matches the filter
   */
  public static List<Visit> filterByBuilding(String searchInput, List<Visit> allVisits) {
    return filterVisits(
        visit -> visit.getBuilding().toLowerCase().contains(searchInput.toLowerCase()), allVisits);
  }

  /**
   * Filters visit by room.
   *
   * @param searchInput room to search for
   * @param allVisits   visits to filter
   * @return a list of visits that matches the filter
   */
  public static List<Visit> filterByRoom(String searchInput, List<Visit> allVisits) {
    return filterVisits(visit -> visit.getRoom().toLowerCase().contains(searchInput.toLowerCase()),
        allVisits);
  }

  /**
   * Filter visits by date.
   *
   * @param allVisits   visits to filter
   * @param logFromDate start date
   * @param logToDate   end date
   * @return a list of visits that matches the filter
   */
  public static List<Visit> filterByDate(
      List<Visit> allVisits, LocalDate logFromDate, LocalDate logToDate) {
    if (logFromDate == null || logToDate == null) {
      return allVisits;
    }
    return filterVisits(
        visit ->
            (logFromDate.isBefore(visit.getFrom().toLocalDate())
                || logFromDate.isEqual(visit.getFrom().toLocalDate()))
                && logToDate.isAfter(visit.getTo().toLocalDate())
                || logToDate.isEqual(visit.getTo().toLocalDate()),
        allVisits);
  }
}
