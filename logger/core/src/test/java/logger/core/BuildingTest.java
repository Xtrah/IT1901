package logger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuildingTest {

  private Building b1;
  private Building b2;

  /**
   * Sets up sample Building objects
   */
  @BeforeEach
  void setUp() {
    b1 = new Building();
    b2 = new Building("Realfagbygget", new ArrayList<String>());
    b2.addRoom("R1");
    b2.addRoom("R7");
  }

  @Test
  void testName() {
    try {
      b1.setName("R€alfagbygg€t");
      fail("Expected IllegalArgumentException thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Building name is invalid!", e.getMessage());
    }
    assertEquals(null, b1.getName());
    assertEquals("Realfagbygget", b2.getName());
    b2.setName("Stripa");
    assertEquals("Stripa", b2.getName());
  }

  @Test
  void testRooms() {
    int sizeBefore = b2.getRooms().size();
    try {
      b1.addRoom("@4-124");
      fail("Expected IllegalArgumentException thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Room name is invalid!", e.getMessage());
    }
    b2.addRoom("A4-124");
    assertEquals(sizeBefore + 1, b2.getRooms().size());
    b2.setRooms(new ArrayList<String>());
    assertEquals(0, b2.getRooms().size());
  }

  @Test
  void testToString() {
    assertEquals("Realfagbygget", b2.toString());
  }
}
