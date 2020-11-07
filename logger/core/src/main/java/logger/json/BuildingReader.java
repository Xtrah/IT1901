package logger.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import logger.core.Building;


public class BuildingReader {

  private static ObjectMapper mapper = new ObjectMapper();

  public static List<Building> readBuildings(URL url) throws IOException {
    return mapper.readValue(url, new TypeReference<>() {
    });
  }


};
