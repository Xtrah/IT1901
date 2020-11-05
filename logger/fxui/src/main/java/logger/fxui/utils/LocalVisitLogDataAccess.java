package logger.fxui.utils;

import logger.core.VisitLog;
import logger.json.VisitLogPersistence;

public class LocalVisitLogDataAccess implements VisitLogDataAccess {
  private final VisitLogPersistence visitLogPersistence = new VisitLogPersistence();

  @Override
  public VisitLog getVisitLog() {
    return visitLogPersistence.readVisitLog();
  }

  @Override
  public void storeVisitLog(VisitLog visitLog) {
    visitLogPersistence.writeVisitLog(visitLog);
  }


}
