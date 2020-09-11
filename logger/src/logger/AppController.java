package logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    @FXML
    void initialize(){
        System.out.println("Initialized!");
    }

    @FXML
    void registerVisit(){
        String name = inputName.getText();
        String phone = inputPhone.getText();
        String building = dropdownBuilding.getValue();
        String room = dropdownRoom.getValue();

        int year = inputDate.getValue().getYear();
        int month = inputDate.getValue().getMonthValue();
        int day = inputDate.getValue().getDayOfMonth();
        int hour1 = Integer.parseInt(inputHour1.getText());
        int min1 = Integer.parseInt(inputMin1.getText());
        int hour2 = Integer.parseInt(inputHour2.getText());
        int min2 = Integer.parseInt(inputMin2.getText());

        LocalDateTime fromTime = LocalDateTime.of(year, month, day, hour1, min1);
        LocalDateTime toTime = LocalDateTime.of(year, month, day, hour2, min2);

        System.out.println(new Visit(name, phone, building, room, fromTime, toTime).toString());
        System.out.println("Hello?!");
    }

    public static void main(String[] args) {

    }

}
