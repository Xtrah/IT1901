package logger.rest;

import logger.core.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VisitLogServiceTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private VisitLogService service;

    @BeforeEach
    void setUp() {
        Visit v1 =
                new Visit(
                        "ergwef-be9c-ergerg-a900-4bca27d688a9",
                        "Ola Normann",
                        "12345678",
                        "Realfagbygget",
                        "A4-100",
                        LocalDateTime.of(2020, 10, 1, 14, 15),
                        LocalDateTime.of(2020, 10, 1, 16, 15));
        service.addVisit(v1);
    }

    @Test
    void sampleVisitLog() {
        assertEquals(3, VisitLogService.sampleVisitLog().getLog().size());
    }

    @Test
    void getVisitLog() {
        assertNotNull(service.getVisitLog());
    }

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

    @Test
    void removeVisit() {
        String id = "ergwef-be9c-ergerg-a900-4bca27d688a9";
        assertTrue(service.removeVisit(id));
    }

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public VisitLogService visitLogService() {
            return new VisitLogService();
        }
    }
}