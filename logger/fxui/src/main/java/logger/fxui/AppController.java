package logger.fxui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogPersistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppController {
    private VisitLog log;
    private VisitLogPersistence persistence;

    @FXML private TableView tableView;
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

        // DUMMY-INFO for choice boxes
        dropdownBuilding.getItems().addAll(FXCollections.observableArrayList("Bygg1", "Bygg2"));
        dropdownRoom.getItems().addAll(FXCollections.observableArrayList("Rom1", "Rom2"));
        
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

        System.out.println("Initialized!");
    }

    @FXML
    void registerVisit(){
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

        log.addVisit(new Visit(name, phone, building, room, fromTime, toTime));
        updateTable();
    }

    @FXML
    void validateValues (){
        // Initiating values
        buttonRegister.setDisable(false);
        helperText.setText("");

        // Validate name
        if (!Visit.isValidName(inputName.getText())){
            buttonRegister.setDisable(true);
            helperText.setText("Names can only contain characters!");
        }

        // Validate phone
        if (!Visit.isValidPhone(inputPhone.getText())){
            buttonRegister.setDisable(true);
            helperText.setText("Number must be eight digits!");
        }

        // Validate time
        // Format from text to LocalTime, and check if LocalTime is valid
        if (!Visit.isValidTime(
                Visit.formatToLocalTime(inputHour1.getText(), inputMin1.getText()),
                Visit.formatToLocalTime(inputHour2.getText(), inputMin2.getText())
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
        tableView.getItems().clear();
        tableView.getItems().addAll(log.getLog());
        persistence.writeVisitLog(log);
    }
}
