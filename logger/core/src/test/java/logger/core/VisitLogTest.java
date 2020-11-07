package logger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VisitLogTest {

  private VisitLog log;
  private Visit v1;
  private Visit v2;

  /**
   * Sets up sample Visit objects and a VisitLog object
   */
  @BeforeEach
  void setUp() {
    v1 =
        new Visit(
            "Ola Normann",
            "12345678",
            "Realfagsbygget",
            "A4-100",
            LocalDateTime.of(2020, 10, 1, 14, 15),
            LocalDateTime.of(2020, 10, 1, 16, 15));
    v2 =
        new Visit(
            "Kari Hansen",
            "487654321",
            "Stripa",
            "S4",
            LocalDateTime.of(2020, 10, 1, 16, 15),
            LocalDateTime.of(2020, 10, 1, 18, 15));

    log = new VisitLog();
  }
  
  @Test
  void testAddVisit() {
    assertEquals(0, log.getLog().size());

    log.addVisit(v1);
    assertEquals(1, log.getLog().size());

    log.addVisit(v2);
    assertEquals(2, log.getLog().size());
  }

  @Test
  void testVisitLogConstructors() {
    assertNotNull(log);
  }

  @Test
  void testRemoveVisit() {
    log.addVisit(v1);
    log.addVisit(v2);
    log.removeVisit(v1);
    assertEquals(log.getLog().size(), 1);
    try {
      log.removeVisit(v1);
      fail("Expected IllegalArgumentException thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Didn't find Visit in VisitLog", e.getMessage());
    }
  }

  @Test
  void testToString() {
    assertEquals("VisitLog{" + "log=" + log.getLog() + '}', log.toString());
  }
}
