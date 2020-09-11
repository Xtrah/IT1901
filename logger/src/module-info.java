module javafxapp {
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires com.fasterxml.jackson.databind;
	
	exports logger;

	opens logger to javafx.fxml;
}