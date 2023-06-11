package fr.iut.sae.controlers;

import fr.iut.sae.utils.CSVReader;
import fr.iut.sae.utils.DataFilter;
import fr.iut.sae.utils.Earthquakes;
import fr.iut.sae.view.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    private ArrayList<Earthquakes> filtredData = new ArrayList<>();

    @FXML
    private TextField firstDate;
    @FXML
    private TextField lastDate;
    @FXML
    private TextField longitudeTextField;
    @FXML
    private TextField latitudeTextField;
    @FXML
    private TextField radiusTextField;
    @FXML
    private ComboBox<String> country;

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

                filtredData.addAll(new DataFilter().dataFilter(firstDate.getText(),lastDate.getText(),
                        data,longitudeTextField.getText(),latitudeTextField.getText(),radiusTextField.getText()));
            }
        }
    }

    @FXML
    public void findHandler(){
        // Charger la vue EarthquakesResearch.fxml
        EarthquakesResearchController earthquakesResearchController = (EarthquakesResearchController) App.setScene("layout/EarthquakesResearch.fxml");
        earthquakesResearchController.setData(filtredData);
    }
}
