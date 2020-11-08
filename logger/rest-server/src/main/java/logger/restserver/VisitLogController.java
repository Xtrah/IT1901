package logger.restserver;

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
  private VisitLogController(VisitLogService visitLogService) {
    this.visitLogService = visitLogService;
  }

  /**
   * Gets the visit log
   *
   * @return the visit log
   */
  @GetMapping
  private VisitLog getVisitLog() {
    return visitLogService.getVisitLog();
  }

  /**
   * Adds a visit to the log
   *
   * @param visit visit to add
   * @return true after adding visit
   */
  @PostMapping
  private boolean addVisit(@RequestBody Visit visit) {
    visitLogService.addVisit(visit);
    return true;
  }

  /**
   * @param id id of visit to delete
   * @return true
   */
  @DeleteMapping(path = "/{id}")
  private boolean removeVisit(@PathVariable("id") String id) {
    visitLogService.removeVisit(id);
    return true;
  }
}
