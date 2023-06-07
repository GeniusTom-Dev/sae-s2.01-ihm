package fr.iut.sae.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EarthquakesResearch extends Application {
    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/EarthquakesResearch.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Sis-France");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
