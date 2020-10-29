package logger.fxui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logger.core.Building;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.BuildingReader;
import logger.fxui.validation.VisitValidation;
import logger.json.VisitLogPersistence;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppController {
    private VisitLog log;
    private VisitLogPersistence persistence;


    @FXML private TextField inputName;
    @FXML private TextField inputPhone;
    @FXML private ChoiceBox<Building> dropdownBuilding;
    @FXML private ChoiceBox<String> dropdownRoom;
    @FXML private DatePicker inputDate;
    @FXML private TextField inputHour1;
    @FXML private TextField inputMin1;
    @FXML private TextField inputHour2;
    @FXML private TextField inputMin2;
    @FXML private Button buttonRegister;
    @FXML private Label helperText;

    // Visit Log
    @FXML private TableView tableView;
    @FXML private TextField searchField;
    @FXML private ChoiceBox<String> chooseSearch;
    @FXML private Label logFromDateLabel;
    @FXML private DatePicker logFromDate;
    @FXML private Label logToDateLabel;
    @FXML private DatePicker logToDate;

    /**
     * Resets all inputs to empty fields, except the DatePicker which is set to todays date.
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
     * Disallows a user to input nothing but numbers in the given TextField
     * @param fxidName fxid of the TextField to enforce
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
     * Disallows a user to input nothing but letters (including norwegian letters) and spaces in the given TextField
     * @param fxidName fxid of the TextField to enforce
     */
    private void forceCharacterInput(TextField fxidName) {
        fxidName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-ZæøåÆØÅ ]*$")) {
                fxidName.setText(newValue.replaceAll("[^a-zA-ZæøåÆØÅ ]*$", ""));
            }
        });
    }

    @FXML
    void fillDropdownRoom(){
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

    @FXML
    void initialize() {

        buttonRegister.setDisable(true);

        // Adding listeners to time-inputs;
        forceNumberInput(inputHour1, 2);
        forceNumberInput(inputHour2, 2);
        forceNumberInput(inputMin1, 2);
        forceNumberInput(inputMin2, 2);
        forceNumberInput(inputPhone, 8);
        forceCharacterInput(inputName);

        try {
            List<Building> buildings = BuildingReader.readBuildings();
            dropdownBuilding.getItems().addAll(buildings);
        }
        catch (IOException e) {
            System.out.println("Couldn't fetch any buildings");
            dropdownBuilding.getItems().addAll(FXCollections.observableArrayList(new ArrayList<>()));
        }

        // Set date to today by default
        inputDate.setValue(LocalDate.now());
        
        // For Visit log
        // Make column
        TableColumn<String, Visit> nameCol = new TableColumn<>("Name");
        // Listen to value 'name' in class 'Visit'
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMaxWidth(40);

        TableColumn<String, Visit> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setMaxWidth(40);

        TableColumn<String, Visit> buildingCol = new TableColumn<>("Building");
        buildingCol.setCellValueFactory(new PropertyValueFactory<>("building"));
        buildingCol.setMaxWidth(60);

        TableColumn<String, Visit> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        roomCol.setMaxWidth(40);

        TableColumn<LocalDateTime, Visit> fromTimeCol = new TableColumn<>("From date");
        fromTimeCol.setCellValueFactory(new PropertyValueFactory<>("from"));
        fromTimeCol.setMaxWidth(65);

        TableColumn<LocalDateTime, Visit> toTimeCol = new TableColumn<>("To date");
        toTimeCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        toTimeCol.setMaxWidth(60);

        // Add all columns to tableView
        tableView.getColumns().addAll(nameCol, phoneCol,
                buildingCol, roomCol,fromTimeCol, toTimeCol);

        persistence = new VisitLogPersistence();
        log = persistence.readVisitLog();
        updateTable();

        // Add dropdown alternatives to filter
        chooseSearch.getItems().addAll(FXCollections.observableArrayList("Name", "Phone", "Building", "Room", "Date"));
        chooseSearch.getSelectionModel().selectFirst();

        System.out.println("Initialized!");
    }

    /**
     * Register a visit to the log
     */
    @FXML
    void registerVisit() {
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
        log.addVisit(new Visit(name, phone, building, room, fromTime, toTime));
        updateTable();

        resetInputs();
        helperText.setText("Successfully registered!");
    }

    /**
     * Deletes a visit
     */
    @FXML
    private void deleteVisit() {
        ObservableList deleteList = tableView.getSelectionModel().getSelectedItems();
        Visit deleteVisit = (Visit) deleteList.get(0);

        log.removeVisit(deleteVisit);
        tableView.getItems().removeAll(
                deleteList
        );
        updateTable();
    }

    @FXML
    void validateValues (){
        // Initiating values
        buttonRegister.setDisable(false);
        helperText.setText("");

        // Validate name
        if (!VisitValidation.isValidName(inputName.getText())){
            buttonRegister.setDisable(true);
            helperText.setText("Names can only contain characters!");
        }

        // Validate phone
        if (!VisitValidation.isValidPhone(inputPhone.getText())){
            buttonRegister.setDisable(true);
            helperText.setText("Number must be eight digits!");
        }

        // Validate time
        // Format from text to LocalTime, and check if LocalTime is valid
        if (!VisitValidation.isValidTime(
                VisitValidation.formatToLocalTime(inputHour1.getText(), inputMin1.getText()),
                VisitValidation.formatToLocalTime(inputHour2.getText(), inputMin2.getText())
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
        if (!VisitValidation.isValidDate(inputDate.getValue())){
            buttonRegister.setDisable(true);
            helperText.setText("Can't set future visits!");
        }

    }

    private boolean isEmptyString(String str) {
        return (str == null || str.trim().isEmpty());
    }

    /**
     * @return true if any of the required fields are empty
     */
    private boolean lackingValues () {
        return (
                isEmptyString(inputName.getText())
                || isEmptyString(inputPhone.getText())
                || isEmptyString(inputPhone.getText())
                || dropdownBuilding.getValue() == null
                || isEmptyString(dropdownRoom.getValue())
                || inputDate.getValue() == null
        );
    }

    /**
     * Filters the log based on name, phone, building, room or date
     */
    @FXML private void filterVisitLog() {
        String searchInput = searchField.getText().toLowerCase(); // User input. Case insensitive
        String searchKey = chooseSearch.getValue(); // DropDown choice
        List<Visit> allVisits = log.getLog();
        List<Visit> result;

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
            case "Date" -> VisitLogFilter.filterByDate(allVisits, logFromDate.getValue(), logToDate.getValue());
            default -> allVisits;
        };

        tableView.getItems().clear();
        tableView.getItems().addAll(result);
    }


    /**
     * Updates the log
     */
    private void updateTable() {
        persistence.writeVisitLog(log);
        tableView.getItems().clear();
        tableView.getItems().addAll(log.getLog());
    }
}
