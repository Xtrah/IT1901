package logger.fxui;

import logger.fxui.utils.LocalVisitLogDataAccess;

public class AppController extends LocalAppController {

  @Override
  protected void setUpStorage() {
    dataAccess = new LocalVisitLogDataAccess(
        getClass().getResource("sampleLog.json").getPath()
    );
  }
}
