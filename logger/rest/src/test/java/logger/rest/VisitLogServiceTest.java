package logger.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import logger.core.Visit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class VisitLogServiceTest {

  @SuppressWarnings("SpringJavaAutowiredMembersInspection")
  @Autowired
  private VisitLogService service;

  /**
   * Tests the sample visit log. Expected result is a VisitLog with 3 Visits.
   */
  @Test
  void sampleVisitLog() {
    assertEquals(3, VisitLogService.sampleVisitLog().getLog().size());
  }

  /**
   * Tests the getVisitLog() method in service. Expected result is an initialized VisitLog.
   */
  @Test
  void getVisitLog() {
    assertNotNull(service.getVisitLog());
  }

  /**
   * Tests the addVisit() method in service. Expected result is true.
   */
  @Test
  void addVisit() {
    Visit v2 =
        new Visit(
            "a81a901e-be9c-4213-a900-4bca27d688a9",
            "Ola Normann",
            "12345678",
            "Realfagbygget",
            "A4-100",
            LocalDateTime.of(2020, 10, 1, 14, 15),
            LocalDateTime.of(2020, 10, 1, 16, 15));
    assertTrue(service.addVisit(v2));
  }

  /**
   * Tests the removeVisit(id) method in service. Add a Visit and remove it by its ID. Expected
   * result is true.
   */
  @Test
  void removeVisit() {
    String id = "ergwef-be9c-ergerg-a900-4bca27d688a9";

    Visit v =
        new Visit(
            id,
            "Ola Normann",
            "12345678",
            "Realfagbygget",
            "A4-100",
            LocalDateTime.of(2020, 10, 1, 14, 15),
            LocalDateTime.of(2020, 10, 1, 16, 15));
    service.addVisit(v);

    assertTrue(service.removeVisit(id));
  }

  /**
   * Class for a VisitLogServiceBean used for testing purposes.
   */
  @TestConfiguration
  static class TestContextConfiguration {

    @Bean
    public VisitLogService visitLogService() {
      return new VisitLogService();
    }
  }
}