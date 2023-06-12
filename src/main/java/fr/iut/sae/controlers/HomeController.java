package fr.iut.sae.controlers;

import fr.iut.sae.utils.CSVReader;
import fr.iut.sae.utils.DataFilter;
import fr.iut.sae.utils.Earthquakes;
import fr.iut.sae.App;
import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

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
    private ComboBox<String> countryComboBox;

    @FXML
    private TextField latitudeTextField;

    @FXML
    private TextField longitudeTextField;

    @FXML
    private TextField radiusTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.disableProperty().bind(latitudeTextField.textProperty().isNotEmpty().or(longitudeTextField.textProperty().isNotEmpty()).or(radiusTextField.textProperty().isNotEmpty()));
        latitudeTextField.disableProperty().bind(countryComboBox.valueProperty().isNotNull().and(countryComboBox.valueProperty().isNotEqualTo("")));
        longitudeTextField.disableProperty().bind(countryComboBox.valueProperty().isNotNull().and(countryComboBox.valueProperty().isNotEqualTo("")));
        radiusTextField.disableProperty().bind(countryComboBox.valueProperty().isNotNull().and(countryComboBox.valueProperty().isNotEqualTo("")));
    }

    public void setData(ArrayList<Earthquakes> originalData) {
        this.data = originalData;
        validFiles();

    }

    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog((Stage) borderPane.getScene().getWindow());

        if (selectedFile != null) {
            // Appel de la méthode pour exploiter les données du fichier CSV
            this.data = new CSVReader().readCSV(selectedFile);

            if(data.size() > 0){
                validFiles();

            }
        }
    }

    public void validFiles(){
        findButton.setDisable(false);
        openFileButton.setDisable(true);

        fillComboxBox();
        Image newImage = new Image("assets/valid.png");
        isUploadImage.setImage(newImage);
        isUploadImage.setFitHeight(30);
        isUploadImage.setFitWidth(30);
    }

    @FXML
    public void findHandler(){
        // Charger la vue EarthquakesResearch.fxml
        EarthquakesResearchController earthquakesResearchController = (EarthquakesResearchController) App.setScene("layout/EarthquakesResearch.fxml");
        assert earthquakesResearchController != null;
        filtredData.addAll(new DataFilter().dataFilter(firstDate.getText(),lastDate.getText(),
                data,longitudeTextField.getText(),latitudeTextField.getText(),radiusTextField.getText(),countryComboBox.getValue()));

        earthquakesResearchController.setData(data, filtredData);
    }

    public void fillComboxBox(){
        ArrayList<String> regions = new ArrayList<>();

        regions.add("");

        for(Earthquakes e : data){
            if(!e.getRegion().isEmpty()){
                if(!regions.contains(e.getRegion())){
                    regions.add(e.getRegion());
                }
            }
        }

        // tri des régions par ordre alphabétique
        regions.sort(String::compareToIgnoreCase);

        for (String v : regions){
            countryComboBox.getItems().add(v);
        }

    }


}
