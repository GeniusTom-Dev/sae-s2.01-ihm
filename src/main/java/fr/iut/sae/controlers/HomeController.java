package fr.iut.sae.controlers;

import fr.iut.sae.utils.CSVReader;
import fr.iut.sae.utils.Earthquakes;
import fr.iut.sae.view.DashBoard;
import fr.iut.sae.view.Home;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class HomeController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button openFileButton;

    @FXML
    private Button findButton;

    @FXML
    private ImageView isUploadImage;

    @FXML
    private Label isUploadImageGest;

    // Variables
    private ArrayList<Earthquakes> data;

    @FXML
    public void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog((Stage) borderPane.getScene().getWindow());

        if (selectedFile != null) {
            // Appel de la méthode pour exploiter les données du fichier CSV
            this.data = new CSVReader().readCSV(selectedFile);

            if(data.size() > 0){
                findButton.setDisable(false);
                openFileButton.setDisable(true);
                Image newImage = new Image("assets/valid.png");
                isUploadImage.setImage(newImage);
                isUploadImage.setFitHeight(30);
                isUploadImage.setFitWidth(30);

                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("layout/dashboard.fxml"));
                loader.load();
                DashboardController controller = loader.getController();
                controller.setData(data);

                // envoie les données dans la classe EarthquakesResearchController.java
                FXMLLoader loaderEarthquakesResearch = new FXMLLoader(getClass().getClassLoader().getResource("layout/EarthquakesResearch.fxml"));
                loaderEarthquakesResearch.load();
                EarthquakesResearchController earthquakesResearchController = loaderEarthquakesResearch.getController();
                earthquakesResearchController.setData(data);
            }
        }
    }

    @FXML
    public void findHandler(){
        System.out.println("ici");
        Stage currentStage = (Stage) borderPane.getScene().getWindow();
        currentStage.setScene(DashBoard.getScene());
    }
}
