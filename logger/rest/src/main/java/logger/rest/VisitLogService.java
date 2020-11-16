package logger.rest;

import java.io.File;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogPersistence;
import org.springframework.stereotype.Service;

@Service
public class VisitLogService {

  private static final String LOG_PATH =
      System.getProperty("user.dir") + File.separator + "log.json";

  private final VisitLogPersistence persistence;
  private final VisitLog visitLog;

  public VisitLogService() {
    persistence = new VisitLogPersistence(new File(LOG_PATH));
    visitLog = persistence.readVisitLog();
  }

  /**
   * Generates a sample VisitLog. Usually used for testing.
   *
   * @return a sample VisitLog
   */
  static VisitLog sampleVisitLog() {
    return new VisitLogPersistence(
        new File(System.getProperty("user.dir") + File.separator + "sampleLog.json"))
        .readVisitLog();
  }

  /**
   * Gets the VisitLog.
   *
   * @return the VisitLog
   */
  VisitLog getVisitLog() {
    return persistence.readVisitLog();
  }

  /**
   * Adds a visit to the log.
   *
   * @param visit visit to add
   */
  boolean addVisit(Visit visit) {
    visitLog.addVisit(visit);
    persistence.writeVisitLog(visitLog);
    return true;
  }

  /**
   * Removes a visit from the log with given id.
   *
   * @param id id of visit to remove
   */
  boolean removeVisit(String id) {
    visitLog.removeVisit(id);
    persistence.writeVisitLog(visitLog);
    return true;
  }
}
