package logger.restserver;

import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogPersistence;
import org.springframework.stereotype.Service;

@Service
public class VisitLogService {

  private VisitLog visitLog;

  public VisitLogService() {
  }

  public VisitLogService(VisitLog visitLog) {
    this.visitLog = visitLog;
  }

  /**
   * Generates a sample VisitLog. Usually used for testing
   *
   * @return a sample VisitLog
   */
  private static VisitLog defaultVisitLog() {
    VisitLogPersistence persister = new VisitLogPersistence();
    return persister.readVisitLog();
  }

  /**
   * Gets the VisitLog
   *
   * @return the VisitLog
   */
  public VisitLog getVisitLog() {
    return visitLog;
  }

  /**
   * Adds a visit to the log
   *
   * @param visit visit to add
   */
  public void addVisit(Visit visit) {
    visitLog.addVisit(visit);
  }

  /**
   * Removes a visit from the log with given id
   *
   * @param id id of visit to remove
   */
  public void removeVisit(String id) {
    visitLog.removeVisit(id);
  }
}
