package logger.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import logger.core.Building;

public class BuildingReader {

  private static ObjectMapper mapper = new ObjectMapper();
  private static File fileURL = new File("../core/src/main/resources/logger/json/buildings.json");

  public static List<Building> readBuildings() throws IOException {
    return mapper.readValue(fileURL, new TypeReference<>() {
    });
  }
}
