package logger.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import logger.core.Building;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BuildingReaderTest {
    private static Building building1;
    private static List<String> roomList1;
    private static Building building2;
    private static List<String> roomList2;
    private static List<Building> buildingList;
    private static ObjectMapper mapper;
    private static JSONObject json;
    private static JSONArray jsonRoomArray;
    private static JSONArray jsonBuildingArray;
    private static File testFileURL;
    private static final String PATH = "testBuildings.json";



    @Test
    public void readerTest() throws JSONException {

        try {
            List<Building> readerResult = BuildingReader.readBuildings(getClass().getResource(PATH));
            System.out.println(readerResult);
            assertEquals(2, readerResult.size());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

};





