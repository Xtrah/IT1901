package logger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VisitTest {

  private Visit v1;
  private Visit v2;

  /**
   * Sets up sample Visit objects
   */
  @BeforeEach
  void setUp() {
    v1 =
        new Visit(
            "Ola Normann",
            "12345678",
            "Realfagbygget",
            "A4-100",
            LocalDateTime.of(2020, 10, 1, 14, 15),
            LocalDateTime.of(2020, 10, 1, 16, 15));
    v2 =
            new Visit(
                    "69",
                    "Ola Normann",
                    "12345678",
                    "Realfagbygget",
                    "A4-100",
                    LocalDateTime.of(2020, 10, 1, 14, 15),
                    LocalDateTime.of(2020, 10, 1, 16, 15));
  }

  @Test
  void testPerson() {
    try {
      v1.setName("Kar1 Hansen");
      fail("Expected IllegalArgumentException thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Person name is invalid!", e.getMessage());
    }
    try {
      v1.setPhone("+4747484950");
      fail("Expected IllegalArgumentException thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Phone number is invalid!", e.getMessage());
    }
    v1.setName("Kari Hansen");
    assertEquals("Kari Hansen", v1.getName());
    v1.setPhone("12345678");
    assertEquals("12345678", v1.getPhone());
  }

  @Test
  void testLocation() {
    try {
      v1.setBuilding("Strip@");
      fail("Expected IllegalArgumentException thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Building name is invalid!", e.getMessage());
    }
    v1.setBuilding("Stripa");
    assertEquals("Stripa", v1.getBuilding());

    try {
      v1.setRoom("$4");
      fail("Expected IllegalArgumentException thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Room name is invalid!", e.getMessage());
    }
    v1.setRoom("S4");
    assertEquals("S4", v1.getRoom());
  }

  @Test
  void testTime() {
    v1.setFrom(LocalDateTime.of(2020, 10, 1, 16, 15));
    assertEquals(LocalDateTime.of(2020, 10, 1, 16, 15), v1.getFrom());
    v1.setTo(LocalDateTime.of(2020, 10, 1, 18, 15));
    assertEquals(LocalDateTime.of(2020, 10, 1, 18, 15), v1.getTo());
  }

  @Test
  void testToString() {
    assertEquals(
        "Visit{"
            + "name='"
            + v1.getName()
            + '\''
            + ", phone='"
            + v1.getPhone()
            + '\''
            + ", building='"
            + v1.getBuilding()
            + '\''
            + ", room='"
            + v1.getRoom()
            + '\''
            + ", from="
            + v1.getFrom()
            + ", to="
            + v1.getTo()
            + ", id='"
            + v1.getId()
            + '\''
            + '}',
        v1.toString());
  }
}
