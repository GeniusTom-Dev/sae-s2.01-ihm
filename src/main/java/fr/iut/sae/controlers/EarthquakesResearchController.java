package fr.iut.sae.controlers;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import fr.iut.sae.utils.CustomCircleMarkerLayer;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class EarthquakesResearchController {

    @FXML
    Circle unknown;
    @FXML
    Circle twoToTwoPointFive;
    @FXML
    Circle threeToThreePointFive;
    @FXML
    Circle fourToFourPointFive;
    @FXML
    Circle fiveToFivePointFive;
    @FXML
    Circle sixToSixPointFive;
    @FXML
    Circle sevenToSevenPointFive;
    @FXML
    Circle eightToEightPointFive;
    @FXML
    Circle nineToNinePointFive;

    @FXML
    VBox mapZone;
    @FXML
    MapView map;

    @FXML
    CheckBox checkBoxId;
    @FXML
    CheckBox checkBoxDate;
    @FXML
    CheckBox checkBoxHour;
    @FXML
    CheckBox checkBoxName;
    @FXML
    CheckBox checkBoxIntensity;
    @FXML
    CheckBox checkBoxQuality;
    @FXML
    CheckBox checkBoxArea;

    @FXML
    public void initialize() {
        initializemMap();
        initializeLegend();
    }

    private void initializemMap() {
        // Définit la plate-forme pour éviter "javafx.platform is not defined"
        System.setProperty("javafx.platform", "desktop");

        //Définit l'user agent pour éviter l'exception "Server returned HTTP response code: 403"
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        /* Création du point avec latitude et longitude */
        MapPoint mapPointDepartInfo = new MapPoint(43.514640, 5.451056);
        MapPoint mapPointTeste2 = new MapPoint(45, 5.5);

        /* Création et ajoute une couche à la carte */
        MapLayer mapLayerDepartInfo = new CustomCircleMarkerLayer(mapPointDepartInfo, Color.RED);
        MapLayer mapLayerTeste2 = new CustomCircleMarkerLayer(mapPointTeste2, Color.BLUE);

        map.addLayer(mapLayerDepartInfo);
        map.addLayer(mapLayerTeste2);

        // création du centre
        MapPoint mapCenter = new MapPoint(46.227638, 2.213749);

        /* Zoom de 5 */
        map.setZoom(5);

        /* Centre la carte sur le point */
        map.flyTo(0, mapCenter, 0.1);
    }

    private void initializeLegend () {
        // quand on ne connais pas la magnitude du seisme
        unknown.setRadius(5);
        unknown.setFill(Color.BLACK);

        twoToTwoPointFive.setRadius(5);
        twoToTwoPointFive.setFill(Color.rgb(0,0,255));

        threeToThreePointFive.setRadius(5);
        threeToThreePointFive.setFill(Color.rgb(125,125,125));

        fourToFourPointFive.setRadius(5);
        fourToFourPointFive.setFill(Color.rgb(0,255,255));

        fiveToFivePointFive.setRadius(5);
        fiveToFivePointFive.setFill(Color.rgb(0,255,0));

        sixToSixPointFive.setRadius(5);
        sixToSixPointFive.setFill(Color.rgb(255,255,0));

        sevenToSevenPointFive.setRadius(5);
        sevenToSevenPointFive.setFill(Color.rgb(255,100,0));

        eightToEightPointFive.setRadius(5);
        eightToEightPointFive.setFill(Color.rgb(255,0,0));

        nineToNinePointFive.setRadius(5);
        nineToNinePointFive.setFill(Color.rgb(255,0,255));

    }

}
