package logger.fxui;

import logger.fxui.utils.RemoteVisitLogDataAccess;

public class RestAppController extends AbstractAppController {

  /**
   * Sets up remote storage to server via REST calls.
   */
  @Override
  protected void setUpStorage() {
    dataAccess = new RemoteVisitLogDataAccess(uriSetup());
  }
}