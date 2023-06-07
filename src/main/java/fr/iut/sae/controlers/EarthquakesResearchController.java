package fr.iut.sae.controlers;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import fr.iut.sae.utils.CustomCircleMarkerLayer;
import fr.iut.sae.utils.Earthquakes;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class EarthquakesResearchController {

    //    private ArrayList<Earthquakes> data = new ArrayList<>();
    private ListProperty<Earthquakes> data = new SimpleListProperty<>();

    public void setData(ArrayList<Earthquakes> data) {
        this.data.set(FXCollections.observableList(data));
    }

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
        initializeMap();
        initializeLegend();
        data.addListener((observableValue, earthquakes, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                initializeMapPoints();
            }
        });
    }

    private void initializeMap() {
        // Définit la plate-forme pour éviter "javafx.platform is not defined"
        System.setProperty("javafx.platform", "desktop");

        //Définit l'user agent pour éviter l'exception "Server returned HTTP response code: 403"
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        // initialisation du centre de la carte de référence
        MapPoint mapCenter = new MapPoint(46.227638, 2.213749);

        // création des points correspondant au séisme

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

    private void initializeMapPoints () {
        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).getLatitude().isEmpty() && !data.get(i).getLongitude().isEmpty()) {

                MapPoint mapPoint = new MapPoint(Double.parseDouble(data.get(i).getLatitude()), Double.parseDouble(data.get(i).getLongitude()));

                // attribue la couleur du point en fonction de l'intensité du séisme
                if(!data.get(i).getIntensity().isEmpty()) {
                    if (Double.parseDouble(data.get(i).getIntensity()) <= 2.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,0,255));
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(data.get(i).getIntensity()) <= 3.5 && Double.parseDouble(data.get(i).getIntensity()) > 2.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(125,125,125));
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(data.get(i).getIntensity()) <= 4.5 && Double.parseDouble(data.get(i).getIntensity()) > 3.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,255,255));
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(data.get(i).getIntensity()) <= 5.5 && Double.parseDouble(data.get(i).getIntensity()) > 4.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,255,0));
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(data.get(i).getIntensity()) <= 6.5 && Double.parseDouble(data.get(i).getIntensity()) > 5.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,255,0));
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(data.get(i).getIntensity()) <= 7.5 && Double.parseDouble(data.get(i).getIntensity()) > 6.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,100,0));
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(data.get(i).getIntensity()) <= 8.5 && Double.parseDouble(data.get(i).getIntensity()) > 7.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,0,0));
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(data.get(i).getIntensity()) <= 9.5 && Double.parseDouble(data.get(i).getIntensity()) > 8.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,0,255));
                        map.addLayer(mapLayer);
                    }
                    else {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,0,0));
                        map.addLayer(mapLayer);
                    }
                }
                else {
                    MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,0,0));
                    map.addLayer(mapLayer);
                }
            }
        }
    }
}
