package fr.iut.sae.controlers;

import fr.iut.sae.utils.CSVReader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


public class HomeController {

    @FXML
    private Button openFileButton;

    @FXML
    private Button findButton;

    @FXML
    private Image isUploadImage;

    @FXML
    private Label isUploadImageGest;

    public void initialize() {
//        isUploadImageGest.visibleProperty().bind();
    }

}
