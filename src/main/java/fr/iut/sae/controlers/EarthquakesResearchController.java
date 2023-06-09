package fr.iut.sae.controlers;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import fr.iut.sae.utils.CustomCircleMarkerLayer;
import fr.iut.sae.utils.Earthquakes;
import fr.iut.sae.App;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * Contrôleur responsable de la recherche des séismes dans une application JavaFX.
 * Ce contrôleur gère les interactions avec l'interface utilisateur et la manipulation des données liées aux séismes.
 * Il affiche les séismes sur une carte, les filtre et les affiche dans un tableau.
 *
 * @author Maxime TAMARIN
 * @version 1.0
 */
public class EarthquakesResearchController {

    private ListProperty<Earthquakes> data = new SimpleListProperty<>();
    private ListProperty<Earthquakes> originalData = new SimpleListProperty<>();
    private ListProperty<Earthquakes> filteredData = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ArrayList<MapLayer> mapLayersList = new ArrayList<>();
    private ArrayList<MapPoint> mapPointsList = new ArrayList<>();

    public void setData(ArrayList<Earthquakes> originalData, ArrayList<Earthquakes> data) {
        this.data.set(FXCollections.observableList(data));
        this.originalData.set(FXCollections.observableList(originalData));
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
    TableView<Earthquakes> chart;
    @FXML
    TableColumn<Object,Object> idColumn;
    @FXML
    TableColumn<Object,Object> dateColumn;
    @FXML
    TableColumn<Object,Object> regionColumn;
    @FXML
    TableColumn<Object,Object> nameColumn;
    @FXML
    TableColumn<Object,Object> intensityColumn;

    /**
     * Initialise le contrôleur et les composants de l'interface utilisateur.
     * Cette méthode est appelée au chargement de la vue correspondante.
     */
    @FXML
    public void initialize() {
        initializeMap();
        initializeLegend();
        initializeTableView();
        // regarde lorsque les datas sont chargés dans l'application
        data.addListener((observableValue, earthquakes, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                // copie des données dans filteredData qui de base n'a pas de filtre
                filteredData.addAll(data);

                addMapPoints(filteredData);
            }
        });
    }

    /**
     * Initialise la carte affichée dans l'interface utilisateur.
     * Cette méthode configure les paramètres de la carte et centre la carte sur un point de référence au centre de la France.
     */
    private void initializeMap() {
        // Définit la plate-forme pour éviter "javafx.platform is not defined"
        System.setProperty("javafx.platform", "desktop");

        //Définit l'user agent pour éviter l'exception "Server returned HTTP response code: 403"
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        // initialisation du centre de la carte de référence
        MapPoint mapCenter = new MapPoint(46.227638, 2.213749);

        /* Zoom de 5 */
        map.setZoom(5);

        /* Centre la carte sur le point */
        map.flyTo(0, mapCenter, 0.08);
    }

    /**
     * Initialise la légende des intensités des séismes.
     * Cette méthode attribue des couleurs aux cercles représentant les différentes intensités de séismes.
     */
    private void initializeLegend () {
        // quand on ne connait pas la magnitude du seisme
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

    /**
     * Ajoute les points de séismes sur la carte.
     * Cette méthode récupère les données de séismes, crée des marqueurs de cercle personnalisés et les ajoute à la carte.
     *
     * @param earthquakes La liste des séismes à afficher sur la carte.
     */
    private void addMapPoints (ListProperty<Earthquakes> earthquakes) {
        clearMapPoints();
        for (Earthquakes earthquake : earthquakes) {
            if (!earthquake.getLatitude().isEmpty() && !earthquake.getLongitude().isEmpty()) {

                MapPoint mapPoint = new MapPoint(Double.parseDouble(earthquake.getLatitude()), Double.parseDouble(earthquake.getLongitude()));
                mapPointsList.add(mapPoint);

                // attribue la couleur du point en fonction de l'intensité du séisme
                if (!earthquake.getIntensity().isEmpty()) {
                    if (Double.parseDouble(earthquake.getIntensity()) <= 2.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0, 0, 255));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else if (Double.parseDouble(earthquake.getIntensity()) <= 3.5 && Double.parseDouble(earthquake.getIntensity()) > 2.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(125, 125, 125));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else if (Double.parseDouble(earthquake.getIntensity()) <= 4.5 && Double.parseDouble(earthquake.getIntensity()) > 3.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0, 255, 255));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else if (Double.parseDouble(earthquake.getIntensity()) <= 5.5 && Double.parseDouble(earthquake.getIntensity()) > 4.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0, 255, 0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else if (Double.parseDouble(earthquake.getIntensity()) <= 6.5 && Double.parseDouble(earthquake.getIntensity()) > 5.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255, 255, 0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else if (Double.parseDouble(earthquake.getIntensity()) <= 7.5 && Double.parseDouble(earthquake.getIntensity()) > 6.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255, 100, 0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else if (Double.parseDouble(earthquake.getIntensity()) <= 8.5 && Double.parseDouble(earthquake.getIntensity()) > 7.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255, 0, 0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else if (Double.parseDouble(earthquake.getIntensity()) <= 9.5 && Double.parseDouble(earthquake.getIntensity()) > 8.5) {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(255, 0, 255));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    } else {
                        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0, 0, 0));
                        mapLayersList.add(mapLayer);
                        map.addLayer(mapLayer);
                    }
                } else {
                    MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint, Color.rgb(0, 0, 0));
                    mapLayersList.add(mapLayer);
                    map.addLayer(mapLayer);
                }
            }
        }
        MapPoint replace = new MapPoint(46.227638+000000.1, 2.213749+000000.1);
        map.flyTo(0,replace,0.1);

    }

    /**
     * Supprime les points de séismes de la carte.
     * Cette méthode supprime tous les marqueurs de séismes de la carte.
     */
    private void clearMapPoints () {
        for (MapLayer mapLayer : mapLayersList) {
            map.removeLayer(mapLayer);
        }
        mapPointsList.clear();
        mapLayersList.clear();
    }

    /**
     * Initialise le tableau affichant les séismes.
     * Cette méthode configure les colonnes du tableau et lie les données filtrées au tableau chart.
     */
    private void initializeTableView () {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        intensityColumn.setCellValueFactory(new PropertyValueFactory<>("intensity"));

        chart.setItems(filteredData);
    }

    /**
     * Redirige vers le tableau de bord de l'application.
     * Cette méthode est appelée lorsqu'un bouton de navigation vers le tableau de bord est cliqué.
     */
    @FXML
    private void toDashboard() {
        DashboardController DashboardController = (DashboardController) App.setScene("layout/dashboard.fxml");
        assert DashboardController != null;
        DashboardController.setData(data, originalData);
    }

    /**
     * Redirige vers la page d'accueil de l'application.
     * Cette méthode est appelée lorsqu'un bouton de navigation vers la page d'accueil est cliqué.
     * @author Tom EVEN
     */
    public void toHome() {
        HomeController homeController = (HomeController) App.setScene("layout/home.fxml");
        assert homeController != null;
        homeController.setData(new ArrayList<>(originalData.get()));
    }
}
