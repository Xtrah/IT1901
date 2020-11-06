package logger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VisitTest {

  private Visit v1;
  private Visit v2;

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
    v2 = new Visit();
  }


  @Test
  void testPerson(){
    v2.setName("Kari Hansen");
    assertEquals("Kari Hansen", v2.getName());
    v2.setPhone("69696969");
    assertEquals("69696969", v2.getPhone());
  }
  @Test
  void testLocation(){
    v2.setBuilding("Stripa");
    assertEquals("Stripa", v2.getBuilding());
    v2.setRoom("S4");
    assertEquals("S4", v2.getRoom());
  }
  @Test
  void testTime(){
    v2.setFrom(LocalDateTime.of(2020, 10, 1, 16, 15));
    assertEquals(LocalDateTime.of(2020, 10, 1, 16, 15), v2.getFrom());
    v2.setTo(LocalDateTime.of(2020, 10, 1, 18, 15));
    assertEquals(LocalDateTime.of(2020, 10, 1, 18, 15), v2.getTo());
  }
  @Test
  void testToString(){
    assertEquals("Visit{"
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
        + '}', v1.toString());
  }
}