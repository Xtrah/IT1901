package logger.fxui;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import logger.core.Visit;

public class VisitLogFilter {
    
    private static List<Visit> filterVisits(Predicate<Visit> pred, List<Visit> allVisits) {
        return allVisits.stream()
        .filter(pred)
        .collect(Collectors.toList());
    }

    public static List<Visit> filterByName(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getName().toLowerCase().contains(searchInput), allVisits);
    }

    public static List<Visit> filterByPhone(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getPhone().contains(searchInput), allVisits);
    }

    public static List<Visit> filterByBuilding(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getBuilding().toLowerCase().contains(searchInput), allVisits);
    }

    public static List<Visit> filterByRoom(String searchInput, List<Visit> allVisits) {
        return filterVisits(visit -> visit.getRoom().toLowerCase().contains(searchInput), allVisits);
    }

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
