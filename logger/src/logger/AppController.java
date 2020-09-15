package logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppController {

    private VisitLog log;

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


    @FXML
    void initialize() {
        // Adding listeners to time-inputs;
        forceNumberInput(inputHour1, 2);
        forceNumberInput(inputHour2, 2);
        forceNumberInput(inputMin1, 2);
        forceNumberInput(inputMin2, 2);
        forceNumberInput(inputPhone, 8);

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

        if (new File("logger/src/logger/log.json").exists()) {
            log = new VisitLog(VisitLog.readFromFile("logger/src/logger/log.json"));
            updateTable();
        } else {
            log = new VisitLog();
        }

        System.out.println("Initialized!");
    }

    @FXML
    void registerVisit(){
        String name = inputName.getText();
        String phone = inputPhone.getText();
        String building = dropdownBuilding.getValue();
        String room = dropdownRoom.getValue();

        int year = getDate().getYear();
        int month = getDate().getMonthValue();
        int day = getDate().getDayOfMonth();
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
        if (!inputName.getText().matches("^[a-zA-Z ]*$")){
            buttonRegister.setDisable(true);
            helperText.setText("Names can only contain characters!");
        }

        // Validate phone
        if (!inputPhone.getText().matches("^[0-9]{8}$")){
            buttonRegister.setDisable(true);
            helperText.setText("Number must be eight digits!");
        }

        // Checking all values. If valid, setting helper text
        if (!isValidTime()) {
            buttonRegister.setDisable(true);
            helperText.setText("Invalid time input!");
        }
        if (lackingValues()) {
            buttonRegister.setDisable(true);
            helperText.setText("Write values in all boxes!");
        }

        // Validate date
        if (LocalDate.now().isBefore(inputDate.getValue())){
            buttonRegister.setDisable(true);
            helperText.setText("Can't set future visits!");
        }

    }

    private boolean isEmptyString(String str) {
        return (str == null || str.trim().isEmpty());
    }

    private boolean lackingValues () {
        return isEmptyString(inputName.getText())
                || isEmptyString(inputPhone.getText())
                || isEmptyString(inputPhone.getText())
                || isEmptyString(dropdownBuilding.getValue())
                || isEmptyString(dropdownRoom.getValue())
                || getDate() == null
        ) return true;
        return false;
    }

    LocalDate getDate (){
        return inputDate.getValue();
    }

    LocalTime getFromTime() {
        if (isTimeString(inputHour1.getText(), inputMin1.getText())){
            int hour1 = Integer.parseInt(inputHour1.getText());
            int min1 = Integer.parseInt(inputMin1.getText());
            return LocalTime.of(hour1, min1);
        }
        return null;
    }
    LocalTime getToTime() {
        if (isTimeString(inputHour2.getText(), inputMin2.getText())){
            int hour2 = Integer.parseInt(inputHour2.getText());
            int min2 = Integer.parseInt(inputMin2.getText());
            return LocalTime.of(hour2, min2);
        }
        return null;

    }

    private boolean isTimeString (String hours, String minutes) {
        String timeString = hours + ':' + minutes;
        // Check if hours are between 0-23 and minutes between 0-59
        return timeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }

    private boolean isValidTime () {
        if (getFromTime() != null && getToTime() != null){
            return getFromTime().isBefore(getToTime());
        }
        return false;
    }

    private void updateTable() {
        tableView.getItems().clear();
        tableView.getItems().addAll(log.getLog());
    }
}
