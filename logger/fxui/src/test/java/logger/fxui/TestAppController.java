package logger.fxui;

import logger.fxui.utils.LocalVisitLogDataAccess;

public class TestAppController extends LocalAppController {

  @Override
  protected void setUpStorage() {
    dataAccess = new LocalVisitLogDataAccess(
        getClass().getResource("sampleLog.json").getPath()
    );
  }
}
