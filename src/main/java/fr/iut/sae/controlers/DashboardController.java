package fr.iut.sae.controlers;

import fr.iut.sae.utils.Earthquakes;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @FXML
    private LineChart<Number, Number> lineChart;

    private ArrayList<Earthquakes> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Title");


//        series.getData().add(new XYChart.Data<>(1, 10));

//        lineChart.getData().add(series);
    }

    public void setData(ArrayList<Earthquakes> data){
        this.data = data;
        System.out.println(data);
    }
}
