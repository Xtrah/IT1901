package logger.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class VisitLog implements Iterable<Visit> {

  private List<Visit> log;

  public VisitLog() {
    this.log = new ArrayList<>();
  }

  public List<Visit> getLog() {
    return log;
  }

  /**
   * Adds a visit to the VisitLog
   *
   * @param visit Visit to add
   */
  public void addVisit(Visit visit) {
    log.add(visit);
  }

  /**
   * Gets a visit with a given ID.
   *
   * @param id id of visit
   * @return the visit with the given id if it exists, null if it doesn't exist
   */
  public Visit getVisit(String id) {
    return log.stream().filter((visit -> visit.getId().equals(id))).findAny().orElse(null);
  }

  /**
   * Removes a visit from the VisitLog
   *
   * @param id id of visit to remove
   */
  public void removeVisit(String id) {
    if (getVisit(id) == null) {
      throw new IllegalArgumentException("No visit with this ID.");
    }
    log = log.stream()
        .filter(visit -> !visit.getId().equals(id))
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "VisitLog{" + "log=" + log + '}';
  }

  /**
   * @return Iterator over logs
   */
  @Override
  public Iterator<Visit> iterator() {
    return log.iterator();
  }
}
