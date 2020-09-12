module javafxapp {
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.datatype.jsr310;

	exports logger;

	opens logger;
}