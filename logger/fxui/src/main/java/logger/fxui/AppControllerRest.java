package logger.fxui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import logger.core.Building;
import logger.fxui.utils.RemoteVisitLogDataAccess;
import logger.json.BuildingReader;

public class AppControllerRest extends AbstractAppController {

  @Override
  protected void setUpStorage() {
    dataAccess = new RemoteVisitLogDataAccess(uriSetup());
  }

  @Override
  protected void setUpBuildings() {
    // Disclaimer: this is duplicate code due to usage of getresource may be unsafe if class is extended (ui_inheritance_unsafe)
    try {
      List<Building> buildings = BuildingReader
          .readBuildings(getClass().getResource("buildings.json"));
      dropdownBuilding.getItems().addAll(buildings);
    } catch (IOException e) {
      System.out.println("Couldn't fetch any buildings");
      dropdownBuilding.getItems().addAll(FXCollections.observableArrayList(new ArrayList<>()));
    }
  }
}