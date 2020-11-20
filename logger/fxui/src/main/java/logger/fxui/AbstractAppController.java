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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import logger.core.Building;
import logger.core.Filter;
import logger.core.Validation;
import logger.core.Visit;
import logger.fxui.utils.VisitLogDataAccess;
import logger.json.BuildingReader;

public abstract class AbstractAppController {

  protected VisitLogDataAccess dataAccess;
  @FXML
  protected ChoiceBox<Building> dropdownBuilding;
  // Registration
  @FXML
  private TextField inputName;
  @FXML
  private TextField inputPhone;
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
   * Fetches buildings and puts them in the buildings dropdown menu.
   */
  private void setUpBuildings() {
    try {
      List<Building> buildings = BuildingReader
          .readBuildings(AbstractAppController.class.getResource("buildings.json"));
      dropdownBuilding.getItems().addAll(buildings);
    } catch (IOException e) {
      System.out.println("Couldn't fetch any buildings");
      dropdownBuilding.getItems().addAll(FXCollections.observableArrayList(new ArrayList<>()));
    }
  }

  /**
   * Sets up the UI.
   */
  @FXML
  private void initialize() {
    setUpStorage();
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
    if (validateValues()) {
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
      dataAccess.addVisit(newVisit);
      updateTable();

      resetInputs();
      helperText.setText("Successfully registered!");
      helperText.setTextFill(Color.GREEN);
    }
  }

  /**
   * Deletes a visit from the log.
   */
  @FXML
  private void deleteVisit() {
    ObservableList<Visit> deleteList = tableView.getSelectionModel().getSelectedItems();
    if (deleteList.isEmpty()) {
      return;
    }
    Visit deleteVisit = deleteList.get(0);
    dataAccess.deleteVisit(deleteVisit.getId());
    updateTable();
  }

  /**
   * Validates the user input.
   */
  @FXML
  private boolean validateValues() {
    // Initiating value
    helperText.setText("");

    // Validate name
    if (!Validation.isValidName(inputName.getText())) {
      setErrorMessage("Name cannot be empty and can only contain characters!");
      return false;
    }

    // Validate phone
    if (!Validation.isValidPhone(inputPhone.getText())) {
      setErrorMessage("Phone number must be eight digits!");
      return false;
    }

    // Validate building selection
    if (dropdownBuilding.getSelectionModel().isEmpty()) {
      setErrorMessage("A building must be chosen!");
      return false;
    }

    // Validate room selection
    if (dropdownRoom.getSelectionModel().isEmpty()) {
      setErrorMessage("A room must be chosen!");
      return false;
    }

    // Validate time
    if (!Validation.isTimeString(inputHour1.getText(), inputMin1.getText())
        || !Validation.isTimeString(inputHour2.getText(), inputMin2.getText())) {
      setErrorMessage("Invalid time input! Must be on format hh:mm");
      return false;
    }

    // Format from text to LocalTime, and check if LocalTime is valid
    if (!Validation.fromIsBeforeTo(
        Validation.formatToLocalTime(inputHour1.getText(), inputMin1.getText()),
        Validation.formatToLocalTime(inputHour2.getText(), inputMin2.getText())
    )
    ) {
      setErrorMessage("Start of visit must be before end of visit!");
      return false;
    }
    // Check if values are empty

    // Validate date
    if (!Validation.isValidDate(inputDate.getValue())) {
      setErrorMessage("Can't set future visits!");
      return false;
    }

    if (lackingValues()) {
      setErrorMessage("Write values in all boxes!");
      return false;
    }

    return true;
  }

  /**
   * Filters the log according to what the user has chosen.
   */
  @FXML
  private void filterVisitLog() {
    final String searchInput = searchField.getText().toLowerCase(); // User input. Case insensitive
    String searchKey = chooseSearch.getValue(); // DropDown choice
    final List<Visit> allVisits = dataAccess.getVisitLog().getLog();
    final List<Visit> result;

    // Hide unused widgets
    searchField.setVisible(!searchKey.equals("Date"));
    logFromDateLabel.setVisible(searchKey.equals("Date"));
    logFromDate.setVisible(searchKey.equals("Date"));
    logToDateLabel.setVisible(searchKey.equals("Date"));
    logToDate.setVisible(searchKey.equals("Date"));

    result = switch (searchKey) {
      case "Name" -> Filter.filterByName(searchInput, allVisits);
      case "Phone" -> Filter.filterByPhone(searchInput, allVisits);
      case "Building" -> Filter.filterByBuilding(searchInput, allVisits);
      case "Room" -> Filter.filterByRoom(searchInput, allVisits);
      case "Date" -> Filter
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
   * Initializes storing and reading from file.
   */
  protected abstract void setUpStorage();

  /**
   * This method sets helper text to msg and sets the text as red.
   *
   * @param msg to set helper text
   */
  private void setErrorMessage(String msg) {
    helperText.setText(msg);
    helperText.setTextFill(Color.RED);
  }

  /**
   * Make uri for endpoint.
   *
   * @return a valid URI for the endpoint
   */
  protected URI uriSetup() {
    URI newUri = null;
    try {
      newUri = new URI("http://localhost:8080/logger");
    } catch (URISyntaxException e) {
      System.out.println(e.getMessage());
    }
    return newUri;
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
    tableView.getItems().addAll(dataAccess.getVisitLog().getLog());
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
