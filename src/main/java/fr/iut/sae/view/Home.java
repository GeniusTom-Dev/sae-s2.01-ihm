package fr.iut.sae.view;

import fr.iut.sae.utils.CSVReader;
import fr.iut.sae.utils.Style;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Home extends Application {

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/home.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Sis-France");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
