package logger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VisitTest {

  private Visit v1;

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
  }

  @Test
  void testPerson() {
    v1.setName("Kari Hansen");
    assertEquals("Kari Hansen", v1.getName());
    v1.setPhone("69696969");
    assertEquals("69696969", v1.getPhone());
  }

  @Test
  void testLocation() {
    v1.setBuilding("Stripa");
    assertEquals("Stripa", v1.getBuilding());
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
