package logger.fxui;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import logger.core.Visit;

public class VisitLogFilter {


    /**
     * @param pred which filter
     * @param allVisits visits to filter
     * @return a list of visits that matches the filter
     */
    private static List<Visit> filterVisits(Predicate<Visit> pred, List<Visit> allVisits) {
        return allVisits.stream()
        .filter(pred)
        .collect(Collectors.toList());
    }


    /**
     * @param searchInput name to search for
     * @param allVisits visits to filter
     * @return a list of visits that matches the filter
     */
    public static List<Visit> filterByName(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getName().toLowerCase().contains(searchInput), allVisits);
    }

    /**
     * @param searchInput phone to search for
     * @param allVisits visits to filter
     * @return a list of visits that matches the filter
     */
    public static List<Visit> filterByPhone(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getPhone().contains(searchInput), allVisits);
    }

    /**
     * @param searchInput building to search for
     * @param allVisits visits to filter
     * @return a list of visits that matches the filter
     */
    public static List<Visit> filterByBuilding(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getBuilding().toLowerCase().contains(searchInput), allVisits);
    }

    /**
     * @param searchInput room to search for
     * @param allVisits visits to filter
     * @return a list of visits that matches the filter
     */
    public static List<Visit> filterByRoom(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getRoom().toLowerCase().contains(searchInput), allVisits);
    }

    /**
     * @param allVisits visits to filter
     * @param logFromDate start date
     * @param logToDate end date
     * @return a list of visits that matches the filter
     */
    public static List<Visit> filterByDate(List<Visit> allVisits, LocalDate logFromDate, LocalDate logToDate) {
        if (logFromDate == null || logToDate == null) {
            return allVisits;
        }
        return filterVisits(visit -> 
                (logFromDate.isBefore(visit.getFrom().toLocalDate()) || logFromDate.isEqual(visit.getFrom().toLocalDate()))
                && logToDate.isAfter(visit.getTo().toLocalDate()) || logToDate.isEqual(visit.getTo().toLocalDate()), 
                allVisits);
    }

}
