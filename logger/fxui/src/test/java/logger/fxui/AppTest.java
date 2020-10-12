package logger.fxui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logger.fxui.AppController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class AppTest extends ApplicationTest {
    private AppController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
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
}