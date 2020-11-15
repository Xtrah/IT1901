package logger.fxui.utils;

import logger.core.Visit;
import logger.core.VisitLog;

public interface VisitLogDataAccess {

  /**
   * Gets visitlog.
   *
   * @return the VisitLog
   */
  VisitLog getVisitLog();

  /**
   * Add visit to visitLog.
   *
   * @param visit Visit visit to be added
   */
  void addVisit(Visit visit);

  /**
   * Deletes visit based on given id.
   *
   * @param id visit id
   */
  void deleteVisit(String id);


}
