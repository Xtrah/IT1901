package logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppController {
    @FXML private Button calculateButton;
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
    void initialize(){
        System.out.println("Initialized!");
        // Adding listeners to time-inputs;
        forceNumberInput(inputHour1);
        forceNumberInput(inputHour2);
        forceNumberInput(inputMin1);
        forceNumberInput(inputMin2);
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
    void disableButtonIfInvalidTime(){ // TODO - change name of this function!!!
        if (isValidTime(inputHour1.getText(), inputMin1.getText()) && isValidTime(inputHour1.getText(), inputMin1.getText())){
            buttonRegister.setDisable(!isValidTime(inputHour1.getText(), inputMin1.getText()));
            buttonRegister.setDisable(!isValidTime(inputHour2.getText(), inputMin2.getText()));

            if (getToTime() != null && getFromTime() != null){
                buttonRegister.setDisable(!getFromTime().isBefore(getToTime()));
            }
            else{
                buttonRegister.setDisable(true);
            }

        }
        else {
            buttonRegister.setDisable(true);
            buttonRegister.setText("Invalid time input!");
        }
    }





    LocalDate getDate (){
        return inputDate.getValue();
    }

    LocalTime getFromTime() {
        if (isValidTime(inputHour1.getText(), inputMin1.getText())){
            int hour1 = Integer.parseInt(inputHour1.getText());
            int min1 = Integer.parseInt(inputMin1.getText());
            return LocalTime.of(hour1, min1);
        }
        return null;
    }
    LocalTime getToTime() {
        //String hour2String= inputHour2.getText();
        //String min2String = inputMin2.getText();
        if (isValidTime(inputHour2.getText(), inputMin2.getText())){
            int hour2 = Integer.parseInt(inputHour2.getText());
            int min2 = Integer.parseInt(inputMin2.getText());
            return LocalTime.of(hour2, min2);
        }
        return null;

    }

    boolean isValidTime(String hours, String minutes) {
        String timeString = hours + ':' + minutes;
        // Check if hours are between 0-23 and minutes between 0-59
        return timeString.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }

    // Hvis minutter.length() != 2:
    //     Sett minutter = 00

    // Hvis timer.length == 1:
    // Sett en 0 foran sifret
    // if else timer.length > 2:
    //    Slice til de to første sifrene


    void compareTimes(LocalTime fromTime, LocalTime toTime){

    }


    public static void main(String[] args) {

    }

}
