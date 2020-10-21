package logger.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import logger.core.Building;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildingReader {
    private static ObjectMapper mapper = new ObjectMapper();
    private static File fileURL = new File("../resources/logger/json/buildings.json");

    public static List<Building> readBuildings() throws IOException {
        System.out.println(fileURL.getAbsolutePath());
        return new ArrayList<>(List.of(mapper.readValue(fileURL, Building[].class )));
    }
}
