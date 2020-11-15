package logger.rest;

import logger.core.Visit;
import logger.core.VisitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VisitLogController.CONTROLLER_PATH)
public class VisitLogController {

    public static final String CONTROLLER_PATH = "/logger";

    private final VisitLogService visitLogService;

    @Autowired
    VisitLogController(final VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    /**
     * Gets the visit log
     *
     * @return the visit log
     */
    @GetMapping
    VisitLog getVisitLog() {
        return visitLogService.getVisitLog();
    }

    /**
     * Adds a visit to the log
     *
     * @param visit visit to add
     * @return true after adding visit
     */
    @PostMapping
    boolean addVisit(@RequestBody Visit visit) {
        visitLogService.addVisit(visit);
        return true;
    }

    /**
     * @param id id of visit to delete
     * @return true
     */
    @DeleteMapping(path = "/{id}")
    boolean removeVisit(@PathVariable("id") String id) {
        visitLogService.removeVisit(id);
        return true;
    }
}
