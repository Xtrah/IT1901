package logger.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    final Parent parent = FXMLLoader.load(getClass().getResource("App.fxml"));
    primaryStage.setTitle("Logger");
    primaryStage.setScene(new Scene(parent));
    primaryStage.show();
  }
}
