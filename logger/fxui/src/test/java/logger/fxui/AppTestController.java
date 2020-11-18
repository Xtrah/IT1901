package logger.fxui;

import logger.fxui.utils.LocalVisitLogDataAccess;

public class AppTestController extends AppControllerLocal {

  @Override
  protected void setUpStorage() {
    dataAccess = new LocalVisitLogDataAccess(
        getClass().getResource("sampleLog.json").getPath()
    );
  }
}
