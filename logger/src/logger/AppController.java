package logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppController {
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

    private void forceNumberInput(TextField fxidName) {
        fxidName.textProperty().addListener((observable, oldValue, newValue) -> {
            // Only allow digits
            if (!newValue.matches("\\d*")) {
                fxidName.setText(newValue.replaceAll("[^\\d]", ""));
            }
            // Only allow 2 digits
            if (newValue.length() > 2) {
                fxidName.setText(oldValue);
            }
        });
    }


    @FXML
    void initialize() {
        System.out.println("Initialized!");
        // Adding listeners to time-inputs;
        forceNumberInput(inputHour1);
        forceNumberInput(inputHour2);
        forceNumberInput(inputMin1);
        forceNumberInput(inputMin2);
        
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

        TableColumn<String, Visit> fromTimeCol = new TableColumn<>("From date");
        fromTimeCol.setCellValueFactory(new PropertyValueFactory<>("fromTime"));
        fromTimeCol.setMaxWidth(65);

        TableColumn<String, Visit> toTimeCol = new TableColumn<>("To date");
        toTimeCol.setCellValueFactory(new PropertyValueFactory<>("toTime"));
        toTimeCol.setMaxWidth(60);

        // Add all columns to tableView
        tableView.getColumns().addAll(nameCol, phoneCol,
                buildingCol, roomCol,fromTimeCol, toTimeCol);

        // To add items to tableView. Mockup-data
        tableView.getItems().add(new Visit("John Doe",
                "99119911", "Bygg1", "A4", new Date(), new Date()));
        tableView.getItems().add(new Visit("Jane Doe",
                "12345678", "Bygg2", "A3", new Date(), new Date()));
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

        System.out.println(new Visit(name, phone, building, room, fromTime, toTime).toString());
        System.out.println("Hello?!");
    }

    @FXML
    void validateValues (){
        // Initiating values
        buttonRegister.setDisable(false);
        helperText.setText("");

        // Checking all values. If valid, setting helper text
        if (!isValidTime()) {
            buttonRegister.setDisable(true);
            helperText.setText("Invalid time input!");
        }
        if (lackingValues()) {
            buttonRegister.setDisable(true);
            helperText.setText("Write values in all boxes!");
        }
    }

    private boolean isEmptyString(String str) {
        return (str == null || str.trim().isEmpty());
    }

    private boolean lackingValues () {
        if (isEmptyString(inputName.getText())
                || isEmptyString(inputPhone.getText())
                || isEmptyString(inputPhone.getText())
                //|| isEmptyString(dropdownBuilding.getValue())
                //|| isEmptyString(dropdownRoom.getValue())
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

    public static void main(String[] args) {
        
    }

}
