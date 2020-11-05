package logger.fxui.utils;

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


}
