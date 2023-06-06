package fr.iut.sae.controlers;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import fr.iut.sae.utils.CustomCircleMarkerLayer;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EarthquakesResearchController {

    @FXML
    VBox mapZone;
    @FXML
    MapView map;

    @FXML
    public void initialize() {

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
    
}
