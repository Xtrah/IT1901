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
   * Removes a visit from the VisitLog
   *
   * @param visit Visit to remove
   */
  public void removeVisit(Visit visit) {
    if (!log.contains(visit)) {
      throw new IllegalArgumentException("Didn't find Visit in VisitLog");
    }
    log = log.stream().filter(v -> !visit.getId().equals(v.getId())).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "VisitLog{" + "log=" + log + '}';
  }

  /** @return Iterator over logs */
  @Override
  public Iterator<Visit> iterator() {
    return log.iterator();
  }
}
