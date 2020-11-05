package logger.fxui.utils;

import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogPersistence;

public class LocalVisitLogDataAccess implements VisitLogDataAccess {
  private final VisitLogPersistence visitLogPersistence = new VisitLogPersistence();
  private VisitLog visitLog = getVisitLog();

  /**
   * Read visit log from local file and return it
   *
   * @return VisitLog
   */
  @Override
  public VisitLog getVisitLog() {
    return visitLogPersistence.readVisitLog();
  }

  /**
   * Stores Visit Log in local file
   *
   * @param visitLog to store
   */
  @Override
  public void storeVisitLog(VisitLog visitLog) {
    visitLogPersistence.writeVisitLog(visitLog);
  }


  /**
   * Deletes visit log with given id
   *
   * @param id visit id to delete
   */
  @Override
  public void deleteVisit(String id) {
    visitLog.removeVisit(id);
    storeVisitLog(visitLog);
  }

  /**
   * Adds visit to Visit log
   *
   * @param visit to add
   */
  @Override
  public void addVisit(Visit visit) {
    visitLog.addVisit(visit);
    storeVisitLog(visitLog);
  }


}
