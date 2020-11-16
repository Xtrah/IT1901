package logger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import logger.core.VisitLog;
import org.junit.jupiter.api.Test;

class VisitLogPersistenceTest {

  private final VisitLogPersistence persistence = new VisitLogPersistence(
      new File(getClass().getResource("sampleLog.json").getPath()));

  @Test
  VisitLog readVisitLog() {
    VisitLog read = persistence.readVisitLog();
    assertNotNull(read);
    assertEquals(3, read.getLog().size());
    return read;
  }

  @Test
  void writeVisitLog() {
    VisitLog toWrite = persistence.readVisitLog();
    persistence.writeVisitLog(toWrite);
    assertEquals(3, persistence.readVisitLog().getLog().size());
  }
}