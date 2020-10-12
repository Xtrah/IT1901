package logger.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class VisitLogTest {

    private VisitLog log;
    private Visit v1;

    @BeforeEach
    void setUp() {
        v1 = new Visit("Ola Normann", "12345678", "Realfagsbygget", "A4-100",
                LocalDateTime.of(2020, 10, 1, 14, 15),
                LocalDateTime.of(2020, 10, 1, 16, 15));

        log = new VisitLog();
    }

    @Test
    void addVisit() {
        assertEquals(0, log.getLog().size());

        log.addVisit(v1);

        assertEquals(1, log.getLog().size());
        assertSame(v1, log.getLog().iterator().next());
    }
}