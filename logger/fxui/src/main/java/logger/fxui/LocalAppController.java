package logger.fxui;

import java.io.File;
import logger.fxui.utils.LocalVisitLogDataAccess;

public class LocalAppController extends AbstractAppController {

  /**
   * Sets up local storage with path at the root of the module.
   */
  @Override
  protected void setUpStorage() {
    dataAccess = new LocalVisitLogDataAccess(
        System.getProperty("user.dir") + File.separator + "log.json");
  }
}
