package logger.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import logger.core.Building;

public class BuildingReader {

  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * This reads from file and returns a list of buildings.
   *
   * @param url for file to be read
   * @return list of buildings
   * @throws IOException if reading value goes wrong
   */
  public static List<Building> readBuildings(URL url) throws IOException {
    return mapper.readValue(url, new TypeReference<>() {
    });
  }
}
