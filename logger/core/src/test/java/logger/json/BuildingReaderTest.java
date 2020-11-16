package logger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;
import logger.core.Building;
import org.junit.jupiter.api.Test;

public class BuildingReaderTest {

  private static final String PATH = "testBuildings.json";

  @Test
  void readerTest() {
    try {
      List<Building> readerResult = BuildingReader.readBuildings(getClass().getResource(PATH));
      System.out.println(readerResult);
      assertEquals(2, readerResult.size());
    } catch (IOException e) {
      System.out.println(e.getMessage());
      fail();
    }
  }
}





