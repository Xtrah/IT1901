package logger.rest;

import logger.core.Visit;
import logger.core.VisitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VisitLogController.CONTROLLER_PATH)
public class VisitLogController {

  public static final String CONTROLLER_PATH = "/logger";

  private final VisitLogService visitLogService;

  @Autowired
  public VisitLogController(final VisitLogService visitLogService) {
    this.visitLogService = visitLogService;
  }

  /**
   * Gets the servers' VisitLog.
   *
   * @return the visit log
   */
  @GetMapping
  public VisitLog getVisitLog() {
    return visitLogService.getVisitLog();
  }

  /**
   * Adds a visit to the servers' VisitLog.
   *
   * @param visit visit to add
   * @return true after adding visit
   */
  @PostMapping
  public boolean addVisit(@RequestBody Visit visit) {
    visitLogService.addVisit(visit);
    return true;
  }

  /**
   * Removes a Visit from the servers' VisitLog.
   *
   * @param id id of visit to delete
   * @return true
   */
  @DeleteMapping(path = "/{id}")
  public boolean removeVisit(@PathVariable("id") String id) {
    visitLogService.removeVisit(id);
    return true;
  }
}
