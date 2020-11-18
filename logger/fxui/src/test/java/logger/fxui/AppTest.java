package logger.fxui;

import static junit.framework.Assert.assertEquals;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class AppTest extends ApplicationTest {

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("TestApp.fxml"));
    loader.setController(new AppController(false));
    final Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testNameInput() {
    TextField inputName = lookup("#inputName").query();
    String text = "Ola50 Nordmann2020";
    clickOn("#inputName").write(text);
    Assertions.assertEquals("Ola Nordmann", inputName.getText());
  }

  @Test
  public void testPhoneInput() {
    TextField inputPhone = lookup("#inputPhone").query();
    String text = "99rgk99rgkrg99rgk99";
    clickOn("#inputPhone").write(text);
    Assertions.assertEquals("99999999", inputPhone.getText());
  }

  @Test
  public void testRegisterVisit() {
    // Add visit
    clickOn("#visitTab");
    TableView tableView = lookup("#tableView").queryTableView();
    int tableSizeBefore = tableView.getItems().size();
    clickOn("#registerTab");
    clickOn("#inputName").write("Kari Traa");
    String phoneNr = "69696969";
    clickOn("#inputPhone").write(phoneNr);
    clickOn("#dropdownBuilding").type(KeyCode.ENTER);
    clickOn("#dropdownRoom").type(KeyCode.ENTER);
    clickOn("#inputHour1").write("13");
    clickOn("#inputMin1").write("37");
    clickOn("#inputHour2").write("13");
    clickOn("#inputMin2").write("38");
    clickOn("#buttonRegister");
    clickOn("#visitTab");
    int tableSizeAfterAdd = tableView.getItems().size();
    assertEquals(tableSizeBefore, tableSizeAfterAdd - 1);
    // Delete visit after searching for visit with phone nr
    clickOn("#chooseSearch").type(KeyCode.DOWN).type(KeyCode.ENTER);
    clickOn("#searchField").write(phoneNr);
    clickOn("#tableView").type(KeyCode.ENTER);
    clickOn("#deleteButton");
    int tableSizeAfterDelete = tableView.getItems().size();
    assertEquals(tableSizeBefore, tableSizeAfterDelete);
  }
}
