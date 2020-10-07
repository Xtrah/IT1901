package logger.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VisitLogModuleTest {

    private static Visit TEST_INPUT1;

    private static Visit TEST_INPUT2;

    private static ObjectMapper mapper;

    private VisitLog log;

    @BeforeAll
    static void initialize() {
        TEST_INPUT1 = new Visit(
                "Jørgen Reimers",
                "45807598",
                "Realfagsbygget",
                "A4-100",
                LocalDateTime.of(2020, 9, 11, 8, 15),
                LocalDateTime.of(2020, 9, 11, 10, 15)
        );

        TEST_INPUT2 = new Visit(
                "Barack Obama",
                "90129023",
                "Det hvite hus",
                "Kontoret",
                LocalDateTime.of(2020, 9, 11, 12, 0),
                LocalDateTime.of(2020, 9, 11, 16, 30)
        );

        mapper = new ObjectMapper();
        mapper.registerModule(new VisitLogModule());
    }

    @BeforeEach
    void setUp() {
        log = new VisitLog();
        log.addVisit(TEST_INPUT1);
        log.addVisit(TEST_INPUT2);
    }

    @Test
    String testSerializers() {
        String json = null;
        try {
            json = mapper.writeValueAsString(log);
            assertTrue(json.contains("name"));
            assertTrue(json.contains("phone"));
            assertTrue(json.contains("building"));
            assertTrue(json.contains("room"));
            assertTrue(json.contains("from"));
            assertTrue(json.contains("to"));
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        return json;
    }

    @Test
    void testDeserializers() {
        String json = testSerializers();
        try {
            assertEquals(log, mapper.readValue(json, VisitLog.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
