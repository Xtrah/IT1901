package logger.restapi;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logger.core.Visit;
import logger.core.VisitLog;

@Path(LoggerService.SERVICE_PATH)
public class LoggerService {

  public static final String SERVICE_PATH = "/logger";

  @Inject
  private VisitLog log;

  /**
   * Root VisitLog
   *
   * @return the VisitLog
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public VisitLog getVisitLog() {
    return log;
  }

  /**
   * Adds a new visit to the log
   *
   * @param visit Visit to add
   * @return true
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public boolean addVisit(Visit visit) {
    log.addVisit(visit);
    return true;
  }

  /**
   * Removes a visit from the log
   *
   * @param id id of visit to delete
   * @return true if the visit is deleted successfully, false otherwise
   */
  @Path("/{id}")
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public boolean removeVisit(@QueryParam("id") String id) {
    try {
      log.removeVisit(id);
      return true;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
}
