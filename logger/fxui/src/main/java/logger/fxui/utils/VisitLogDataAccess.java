package logger.fxui.utils;

import logger.core.Visit;
import logger.core.VisitLog;

public interface VisitLogDataAccess {

  /**
   * Gets visitlog
   *
   * @return the VisitLog
   */
  VisitLog getVisitLog();

  /**
   * visitLog is stored
   *
   * @param visitLog
   */
  void storeVisitLog(VisitLog visitLog);

  /**
   * Add visit to visitlog
   *
   * @param visit
   */
  void addVisit(Visit visit);

  /**
   * Deletes visit based on given id
   *
   * @param id visit id
   */
  void deleteVisit(String id);


}
