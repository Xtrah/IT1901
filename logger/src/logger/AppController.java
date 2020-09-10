package logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AppController {
    @FXML private Button calculateButton;

    @FXML
    void calculate() {
        calculateButton.setText("Yes!");
    }

    @FXML
    void initialize(){
        System.out.println("Initialized!");
    }

    public static void main(String[] args) {

    }

}
