package logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VisitLogTest {

    private static final String FILEPATH = "log.json";

    private VisitLog log;

    @BeforeEach
    void setUp() {
        Visit v1 = new Visit("Ola Normann", "12345678", "Realfagsbygget", "A4-100",
                LocalDateTime.of(2020, 10, 1, 14, 15),
                LocalDateTime.of(2020, 10, 1, 16, 15));
        Visit v2 = new Visit("Kari Hansen", "487654321", "Stripa", "S4",
                LocalDateTime.of(2020, 10, 1, 16, 15),
                LocalDateTime.of(2020, 10, 1, 18, 15));

        log = new VisitLog();
        log.addVisit(v1);
        log.addVisit(v2);
    }

    @AfterEach
    void tearDown() {
        File file = new File(FILEPATH);
        if (file.delete())
            System.out.println("File deleted successfully");
        else {
            System.out.println("Couldn't delete file");
            fail();
        }
    }

    @Test
    void writeToFile() {
        log.writeToFile(FILEPATH);

        File file = new File(FILEPATH);
        assertTrue(file.exists());
    }

    @Test
    void readFromFile() {
        List<Visit> beforeReadLog = log.getLog();
        log.writeToFile(FILEPATH);
        log.readFromFile(FILEPATH);

        assertEquals(beforeReadLog.size(), log.getLog().size());
    }

    @Test
    void addVisit() {
        log.writeToFile(FILEPATH);
        assertEquals(2, log.getLog().size());
        log.addVisit(new Visit("Brora Hansen", "18273645", "Realfagsbygget", "R1",
                LocalDateTime.of(2020, 11, 1, 18, 15),
                LocalDateTime.of(2020, 11, 1, 19, 15)));
        log.writeToFile(FILEPATH);
        log.readFromFile(FILEPATH);
        assertEquals(3, log.getLog().size());
    }
}