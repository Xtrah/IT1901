package logger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.time.LocalDateTime;
import logger.core.Visit;
import logger.core.VisitLog;
import org.junit.jupiter.api.Test;

class VisitLogPersistenceTest {

  private final VisitLogPersistence persistence = new VisitLogPersistence(
      new File(getClass().getResource("sampleLog.json").getFile()));

  @Test
  VisitLog readVisitLog() {
    VisitLog read = persistence.readVisitLog();
    assertNotNull(read);
    assertEquals(3, read.getLog().size());
    return read;
  }

  @Test
  void writeVisitLog() {
    VisitLog toWrite = readVisitLog();
    Visit toAdd = new Visit("Mats von Bausher",
        "87128734",
        "Realfagbygget",
        "R7",
        LocalDateTime.of(2020, 10, 31, 12, 15),
        LocalDateTime.of(2020, 10, 31, 14, 15));
    toWrite.addVisit(toAdd);
    persistence.writeVisitLog(toWrite);
    VisitLog afterAdd = persistence.readVisitLog();
    assertEquals(4, afterAdd.getLog().size());
  }
}