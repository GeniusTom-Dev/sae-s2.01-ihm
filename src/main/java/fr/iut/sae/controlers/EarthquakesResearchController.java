package fr.iut.sae.controlers;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import fr.iut.sae.utils.CustomCircleMarkerLayer;
import fr.iut.sae.utils.Earthquakes;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class EarthquakesResearchController {

    private ListProperty<Earthquakes> data = new SimpleListProperty<>();
    private ArrayList<MapLayer> mapLayersList = new ArrayList<>();

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
    GridPane chart;
    @FXML
    TextField searchTextField;

    @FXML
    public void initialize() {
        initializeMap();
        initializeLegend();
        // regarde lorsque l'utilisateur édite le texte dans le TextField
        searchTextField.selectionProperty().addListener(observable -> searchEarthquake());
        // regarde lorsque les datas sont chargés dans l'application
        data.addListener((observableValue, earthquakes, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                addMapPoints(data);
                addGridPaneData(data);
                // execute une recherche quand ENTER est pressé
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

    private void addMapPoints (ListProperty<Earthquakes> earthquakes) {
        for (int i = 0; i < earthquakes.size(); i++) {
            if (!earthquakes.get(i).getLatitude().isEmpty() && !earthquakes.get(i).getLongitude().isEmpty()) {

                MapPoint mapPoint = new MapPoint(Double.parseDouble(earthquakes.get(i).getLatitude()), Double.parseDouble(earthquakes.get(i).getLongitude()));

                // attribue la couleur du point en fonction de l'intensité du séisme
                if(!earthquakes.get(i).getIntensity().isEmpty()) {
                    if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 2.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,0,255));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 3.5 && Double.parseDouble(earthquakes.get(i).getIntensity()) > 2.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(125,125,125));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 4.5 && Double.parseDouble(earthquakes.get(i).getIntensity()) > 3.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,255,255));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 5.5 && Double.parseDouble(earthquakes.get(i).getIntensity()) > 4.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,255,0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 6.5 && Double.parseDouble(earthquakes.get(i).getIntensity()) > 5.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,255,0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 7.5 && Double.parseDouble(earthquakes.get(i).getIntensity()) > 6.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,100,0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 8.5 && Double.parseDouble(earthquakes.get(i).getIntensity()) > 7.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,0,0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else if (Double.parseDouble(earthquakes.get(i).getIntensity()) <= 9.5 && Double.parseDouble(earthquakes.get(i).getIntensity()) > 8.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255,0,255));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                    else {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,0,0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                }
                else {
                    MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0,0,0));
                    mapLayersList.add(mapLayer);
                    map.addLayer(mapLayer);
                }
            }
        }
    }

    private void clearMapPoints () {
        for (MapLayer mapLayer: mapLayersList) {
            map.removeLayer(mapLayer);
        }
    }
    private void addGridPaneData (ListProperty<Earthquakes> earthquakes) {
        for (int i = 0; i < earthquakes.size(); i++) {
            Label id = new Label(earthquakes.get(i).getId());
            Label date = new Label(earthquakes.get(i).getDate());
            Label hour = new Label(earthquakes.get(i).getHour());
            Label name = new Label(earthquakes.get(i).getName());
            Label intensity = new Label(earthquakes.get(i).getIntensity());
            Label quality = new Label(earthquakes.get(i).getQuality());
            Label region = new Label(earthquakes.get(i).getRegion());

            chart.addRow(i+2,id,date,hour,name,intensity,quality,region);
        }
    }

    private void searchEarthquake() {

        String searchString = searchTextField.getText();

        // on supprime toute du tableau et de la carte
        chart.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
        clearMapPoints();

        // recherche des données correspondantes
        ListProperty<Earthquakes> matchingData = findMatchingData(searchString);
        // met à jour le tableau et la carte
        addGridPaneData(matchingData);
        addMapPoints(matchingData);
    }

    private ListProperty<Earthquakes> findMatchingData(String searchString) {
        ListProperty<Earthquakes> matchingData = new SimpleListProperty<>();
        for (Earthquakes earthquake : data) {
            if (earthquake.getId().contains(searchString)) {
                matchingData.add(earthquake);
            }
        }
        return matchingData;
    }
}
