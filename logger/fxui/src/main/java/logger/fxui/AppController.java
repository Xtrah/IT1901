package logger.fxui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.fxui.validation.VisitValidation;
import logger.json.VisitLogPersistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppController {
    private VisitLog log;
    private VisitLogPersistence persistence;

    // Registration
    @FXML private TextField inputName;
    @FXML private TextField inputPhone;
    @FXML private ChoiceBox<String> dropdownBuilding;
    @FXML private ChoiceBox<String> dropdownRoom;
    @FXML private DatePicker inputDate;
    @FXML private TextField inputHour1;
    @FXML private TextField inputMin1;
    @FXML private TextField inputHour2;
    @FXML private TextField inputMin2;
    @FXML private Button buttonRegister;
    @FXML private Label helperText;

    // Log
    @FXML private TableView<Visit> tableView;
    @FXML private TextField searchField;
    @FXML private ChoiceBox<String> chooseSearch;
    @FXML private Label logFromDateLabel;
    @FXML private DatePicker logFromDate;
    @FXML private Label logToDateLabel;
    @FXML private DatePicker logToDate;

    @FXML
    void initialize() {
        buttonRegister.setDisable(true);
        initTable();
        setUpPersistence();
        setUpFiltering();
        activateInputRules();

        // DUMMY-INFO
        dropdownBuilding.getItems().addAll(FXCollections.observableArrayList("Bygg1", "Bygg2"));
        dropdownRoom.getItems().addAll(FXCollections.observableArrayList("Rom1", "Rom2"));
        inputDate.setValue(LocalDate.now());

        System.out.println("Initialized!");
    }

    @FXML
    void registerVisit() {
        String name = inputName.getText();
        String phone = inputPhone.getText();
        String building = dropdownBuilding.getValue();
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
        if (inputDate.getValue() != null && inputDate.getValue().isAfter(LocalDate.now())){
            buttonRegister.setDisable(true);
            helperText.setText("Can't set future visits!");
        }

    }

    @FXML
    void filterVisitLog() {
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

    private void forceCharacterInput(TextField fxidName) {
        fxidName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-ZæøåÆØÅ ]*$")) {
                fxidName.setText(newValue.replaceAll("[^a-zA-ZæøåÆØÅ ]*$", ""));
            }
        });
    }

    private boolean isEmptyString(String str) {
        return (str == null || str.trim().isEmpty());
    }

    private boolean lackingValues () {
        return (
                isEmptyString(inputName.getText())
                || isEmptyString(inputPhone.getText())
                || isEmptyString(inputPhone.getText())
                || isEmptyString(dropdownBuilding.getValue())
                || isEmptyString(dropdownRoom.getValue())
                || inputDate.getValue() == null
        );
    }

    private void updateTable() {
        persistence.writeVisitLog(log);
        tableView.getItems().clear();
        tableView.getItems().addAll(log.getLog());
    }

    private void initTable() {
        // For Visit log
        // Make column
        TableColumn<Visit, String> nameCol = new TableColumn<>("Name");
        // Listen to value 'name' in class 'Visit'
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMaxWidth(120);

        TableColumn<Visit, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setMaxWidth(120);

        TableColumn<Visit, String> buildingCol = new TableColumn<>("Building");
        buildingCol.setCellValueFactory(new PropertyValueFactory<>("building"));
        buildingCol.setMaxWidth(120);

        TableColumn<Visit, String> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        roomCol.setMaxWidth(120);

        TableColumn<Visit, LocalDateTime> fromTimeCol = new TableColumn<>("From");
        fromTimeCol.setCellValueFactory(new PropertyValueFactory<>("from"));
        fromTimeCol.setMaxWidth(200);

        TableColumn<Visit, LocalDateTime> toTimeCol = new TableColumn<>("To");
        toTimeCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        toTimeCol.setMaxWidth(200);

        // Add all columns to tableView
        tableView.getColumns().addAll(nameCol, phoneCol,
                buildingCol, roomCol,fromTimeCol, toTimeCol);

    }

    private void activateInputRules() {
        forceNumberInput(inputHour1, 2);
        forceNumberInput(inputHour2, 2);
        forceNumberInput(inputMin1, 2);
        forceNumberInput(inputMin2, 2);
        forceNumberInput(inputPhone, 8);
        forceCharacterInput(inputName);
    }

    private void setUpPersistence() {
        persistence = new VisitLogPersistence();
        log = persistence.readVisitLog();
        updateTable();
    }

    private void setUpFiltering() {
        chooseSearch.getItems().addAll(FXCollections.observableArrayList("Name", "Phone", "Building", "Room", "Date"));
        chooseSearch.getSelectionModel().selectFirst();
    }
}
