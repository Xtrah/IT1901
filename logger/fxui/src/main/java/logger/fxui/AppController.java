package logger.fxui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logger.core.Building;
import logger.core.Validation;
import logger.core.Visit;
import logger.fxui.utils.LocalVisitLogDataAccess;
import logger.fxui.utils.RemoteVisitLogDataAccess;
import logger.fxui.utils.VisitLogDataAccess;
import logger.fxui.utils.VisitLogFilter;
import logger.json.BuildingReader;

public class AppController {

  // true for remote storage, false for local storage
  private final VisitLogDataAccess visitLogDataAccess = isRemoteStorage(false);

  // Registration
  @FXML
  private TextField inputName;
  @FXML
  private TextField inputPhone;
  @FXML
  private ChoiceBox<Building> dropdownBuilding;
  @FXML
  private ChoiceBox<String> dropdownRoom;
  @FXML
  private DatePicker inputDate;
  @FXML
  private TextField inputHour1;
  @FXML
  private TextField inputMin1;
  @FXML
  private TextField inputHour2;
  @FXML
  private TextField inputMin2;
  @FXML
  private Button buttonRegister;
  @FXML
  private Label helperText;

  // Log
  @FXML
  private TableView<Visit> tableView;
  @FXML
  private TextField searchField;
  @FXML
  private ChoiceBox<String> chooseSearch;
  @FXML
  private Label logFromDateLabel;
  @FXML
  private DatePicker logFromDate;
  @FXML
  private Label logToDateLabel;
  @FXML
  private DatePicker logToDate;

  // Log Columns
  @FXML
  private TableColumn<String, Visit> nameCol;
  @FXML
  private TableColumn<String, Visit> phoneCol;
  @FXML
  private TableColumn<String, Visit> buildingCol;
  @FXML
  private TableColumn<String, Visit> roomCol;
  @FXML
  private TableColumn<String, Visit> fromTimeCol;
  @FXML
  private TableColumn<String, Visit> toTimeCol;


  /**
   * Sets up the UI.
   */
  @FXML
  private void initialize() {
    buttonRegister.setDisable(true);
    setUpColumnListeners();
    updateTable();
    setUpFiltering();
    setUpBuildings();
    activateInputRules();
    inputDate.setValue(LocalDate.now());
    System.out.println("Initialized!");
  }

  /**
   * Register a visit to the log.
   */
  @FXML
  private void registerVisit() {
    String name = inputName.getText();
    String phone = inputPhone.getText();
    String building = dropdownBuilding.getValue().getName();
    String room = dropdownRoom.getValue();

    LocalDate pickedDate = inputDate.getValue();
    int year = pickedDate.getYear();
    int month = pickedDate.getMonthValue();
    int day = pickedDate.getDayOfMonth();
    int hour1 = Integer.parseInt(inputHour1.getText());
    int min1 = Integer.parseInt(inputMin1.getText());
    int hour2 = Integer.parseInt(inputHour2.getText());
    int min2 = Integer.parseInt(inputMin2.getText());

    LocalDateTime fromTime = LocalDateTime.of(year, month, day, hour1, min1);
    LocalDateTime toTime = LocalDateTime.of(year, month, day, hour2, min2);

    // Handling VisitLog
    Visit newVisit = new Visit(name, phone, building, room, fromTime, toTime);
    visitLogDataAccess.addVisit(newVisit);
    updateTable();

    resetInputs();
    helperText.setText("Successfully registered!");
  }

  /**
   * Deletes a visit from the log.
   */
  @FXML
  private void deleteVisit() {
    ObservableList<Visit> deleteList = tableView.getSelectionModel().getSelectedItems();
    Visit deleteVisit = deleteList.get(0);
    visitLogDataAccess.deleteVisit(deleteVisit.getId());
    updateTable();
  }

  /**
   * Validates the user input.
   */
  @FXML
  private void validateValues() {
    // Initiating values
    buttonRegister.setDisable(false);
    helperText.setText("");

    // Validate name
    if (!Validation.isValidName(inputName.getText())) {
      buttonRegister.setDisable(true);
      helperText.setText("Names can only contain characters!");
    }

    // Validate phone
    if (!Validation.isValidPhone(inputPhone.getText())) {
      buttonRegister.setDisable(true);
      helperText.setText("Number must be eight digits!");
    }

    // Validate time
    // Format from text to LocalTime, and check if LocalTime is valid
    if (!Validation.isValidTime(
        Validation.formatToLocalTime(inputHour1.getText(), inputMin1.getText()),
        Validation.formatToLocalTime(inputHour2.getText(), inputMin2.getText())
    )
    ) {
      buttonRegister.setDisable(true);
      helperText.setText("Invalid time input!");
    }
    // Check if values are empty
    if (lackingValues()) {
      buttonRegister.setDisable(true);
      helperText.setText("Write values in all boxes!");
    }

    // Validate date
    if (!Validation.isValidDate(inputDate.getValue())) {
      buttonRegister.setDisable(true);
      helperText.setText("Can't set future visits!");
    }
  }

  /**
   * Filters the log according to what the user has chosen.
   */
  @FXML
  private void filterVisitLog() {
    final String searchInput = searchField.getText().toLowerCase(); // User input. Case insensitive
    String searchKey = chooseSearch.getValue(); // DropDown choice
    final List<Visit> allVisits = visitLogDataAccess.getVisitLog().getLog();
    final List<Visit> result;

    // Hide unused widgets
    searchField.setVisible(!searchKey.equals("Date"));
    logFromDateLabel.setVisible(searchKey.equals("Date"));
    logFromDate.setVisible(searchKey.equals("Date"));
    logToDateLabel.setVisible(searchKey.equals("Date"));
    logToDate.setVisible(searchKey.equals("Date"));

    result = switch (searchKey) {
      case "Name" -> VisitLogFilter.filterByName(searchInput, allVisits);
      case "Phone" -> VisitLogFilter.filterByPhone(searchInput, allVisits);
      case "Building" -> VisitLogFilter.filterByBuilding(searchInput, allVisits);
      case "Room" -> VisitLogFilter.filterByRoom(searchInput, allVisits);
      case "Date" -> VisitLogFilter
          .filterByDate(allVisits, logFromDate.getValue(), logToDate.getValue());
      default -> allVisits;
    };

    tableView.getItems().clear();
    tableView.getItems().addAll(result);
  }

  /**
   * Fills the room dropdown with rooms according to which building is chosen.
   */
  @FXML
  private void fillDropdownRoom() {
    Building selectedBuilding = dropdownBuilding.getSelectionModel().getSelectedItem();
    ObservableList<String> rooms;
    dropdownRoom.getItems().clear();
    if (selectedBuilding == null) {
      rooms = FXCollections.observableArrayList(new ArrayList<>());
    } else {
      rooms = FXCollections.observableArrayList(selectedBuilding.getRooms());
    }
    dropdownRoom.getItems().addAll(rooms);
  }

  /**
   * Make uri for endpoint.
   *
   * @param uri for endpoint
   * @return
   */
  private URI uriSetup(String uri) {
    URI newUri = null;
    try {
      newUri = new URI(uri);
    } catch (URISyntaxException e) {
      System.out.println(e.getMessage());
    }
    return newUri;
  }

  /**
   * Sets local or remote storage.
   *
   * @param isRemote either true or false
   * @return
   */
  private VisitLogDataAccess isRemoteStorage(boolean isRemote) {
    if (isRemote) {
      return new RemoteVisitLogDataAccess(
          uriSetup("http://localhost:8080/logger"));
    }
    return new LocalVisitLogDataAccess();
  }

  /**
   * Resets all inputs to empty fields, except the DatePicker which is set to today's  date.
   */
  private void resetInputs() {
    inputName.setText("");
    inputPhone.setText("");
    dropdownBuilding.getSelectionModel().clearSelection();
    dropdownRoom.getSelectionModel().clearSelection();
    inputDate.setValue(LocalDate.now());
    inputHour1.setText("");
    inputMin1.setText("");
    inputHour2.setText("");
    inputMin2.setText("");
    buttonRegister.setDisable(true);
  }

  /**
   * Checks if the input fields are empty.
   *
   * @return true if the user has not yet filled in all required fields, false otherwise
   */
  private boolean lackingValues() {
    return (
        Validation.isEmptyString(inputName.getText())
            || Validation.isEmptyString(inputPhone.getText())
            || dropdownBuilding.getValue() == null
            || Validation.isEmptyString(dropdownRoom.getValue())
            || inputDate.getValue() == null);
  }

  /**
   * Updates the log.
   */
  private void updateTable() {
    tableView.getItems().clear();
    tableView.getItems().addAll(visitLogDataAccess.getVisitLog().getLog());
  }

  /**
   * Make columns listen to values in Visit, e.g. 'name' in class 'Visit'.
   */
  private void setUpColumnListeners() {
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    buildingCol.setCellValueFactory(new PropertyValueFactory<>("building"));
    roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
    fromTimeCol.setCellValueFactory(new PropertyValueFactory<>("from"));
    toTimeCol.setCellValueFactory(new PropertyValueFactory<>("to"));
  }

  /**
   * Applies input rules to input fields.
   */
  private void activateInputRules() {
    forceNumberInput(inputHour1, 2);
    forceNumberInput(inputHour2, 2);
    forceNumberInput(inputMin1, 2);
    forceNumberInput(inputMin2, 2);
    forceNumberInput(inputPhone, 8);
    forceCharacterInput(inputName);
  }

  /**
   * Sets up filter options and filter input fields.
   */
  private void setUpFiltering() {
    chooseSearch.getItems()
        .addAll(FXCollections.observableArrayList("Name", "Phone", "Building", "Room", "Date"));
    chooseSearch.getSelectionModel().selectFirst();
  }

  /**
   * Fetches buildings and puts them in the buildings dropdown menu.
   */
  private void setUpBuildings() {
    try {
      List<Building> buildings = BuildingReader
          .readBuildings(getClass().getResource("buildings.json"));
      dropdownBuilding.getItems().addAll(buildings);
    } catch (IOException e) {
      System.out.println("Couldn't fetch any buildings");
      dropdownBuilding.getItems().addAll(FXCollections.observableArrayList(new ArrayList<>()));
    }
  }

  /**
   * Disallows a user to input nothing but numbers in the given TextField.
   *
   * @param fxidName  fxid of the TextField to enforce
   * @param maxLength maximum length of input
   */
  private void forceNumberInput(TextField fxidName, int maxLength) {
    fxidName.textProperty().addListener((observable, oldValue, newValue) -> {
      // Only allow digits
      if (!newValue.matches("\\d*")) {
        fxidName.setText(newValue.replaceAll("[^\\d]", ""));
      }
      // Only allow 2 digits
      if (newValue.length() > maxLength) {
        fxidName.setText(oldValue);
      }
    });
  }

  /**
   * Disallows a user to input nothing but letters (including norwegian letters) and spaces in the
   * given TextField.
   *
   * @param fxidName fxid of the TextField to enforce
   */
  private void forceCharacterInput(TextField fxidName) {
    fxidName.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("^[a-zA-ZæøåÆØÅ ]*$")) {
        fxidName.setText(newValue.replaceAll("[^a-zA-ZæøåÆØÅ ]*$", ""));
      }
    });
  }
}
