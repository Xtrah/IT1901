package logger.fxui;

import java.net.URI;
import java.net.URISyntaxException;
import logger.fxui.utils.RemoteVisitLogDataAccess;

public class RestAppController extends AbstractAppController {

  /**
   * Make uri for endpoint.
   *
   * @return a valid URI for the endpoint
   */
  private URI uriSetup() {
    URI newUri = null;
    try {
      newUri = new URI("http://localhost:8080/logger");
    } catch (URISyntaxException e) {
      System.out.println(e.getMessage());
    }
    return newUri;
  }

  /**
   * Sets up remote storage to server via REST calls.
   */
  @Override
  protected void setUpStorage() {
    dataAccess = new RemoteVisitLogDataAccess(uriSetup());
  }
}