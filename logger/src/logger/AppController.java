package logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.Date;

public class AppController {
    @FXML private Button calculateButton;
    @FXML private TableView tableView;

    @FXML
    void calculate() {
        calculateButton.setText("Yes!");
    }

    @FXML
    void initialize() {
        System.out.println("Initialized!");
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

}
