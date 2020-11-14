package logger.fxui.utils;

import java.io.File;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogPersistence;

public class LocalVisitLogDataAccess implements VisitLogDataAccess {

  private static final String LOG_PATH =
      System.getProperty("user.dir") + File.separator + "log.json";

  private final VisitLogPersistence persistence;
  private final VisitLog visitLog;

  public LocalVisitLogDataAccess() {
    persistence = new VisitLogPersistence(new File(LOG_PATH));
    visitLog = getVisitLog();
  }

  /**
   * Read visit log from local file and return it.
   *
   * @return VisitLog
   */
  @Override
  public VisitLog getVisitLog() {
    return persistence.readVisitLog();
  }

  /**
   * Adds visit to Visit log.
   *
   * @param visit to add
   */
  @Override
  public void addVisit(Visit visit) {
    visitLog.addVisit(visit);
    persistence.writeVisitLog(visitLog);
  }

  /**
   * Deletes visit log with given id.
   *
   * @param id visit id to delete
   */
  @Override
  public void deleteVisit(String id) {
    visitLog.removeVisit(id);
    persistence.writeVisitLog(visitLog);
  }


}
