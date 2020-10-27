package logger.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VisitLogTest {

    private VisitLog log;
    private Visit v1;
    private Visit v2;

    @BeforeEach
    void setUp() {
        v1 = new Visit("Ola Normann", "12345678", "Realfagsbygget", "A4-100",
                LocalDateTime.of(2020, 10, 1, 14, 15),
                LocalDateTime.of(2020, 10, 1, 16, 15));
        v2 = new Visit("Kari Hansen", "487654321", "Stripa", "S4",
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

        log.addVisit(v1);
        log.addVisit(v2);
        VisitLog newLog = new VisitLog(log.getLog());
        assertEquals(log, newLog);
    }

    @Test
    void testRemoveVisit() {
        log.addVisit(v1);
        log.addVisit(v2);
        log.removeVisit(v1);
        assertEquals(log.getLog().size(), 1);
    }
}