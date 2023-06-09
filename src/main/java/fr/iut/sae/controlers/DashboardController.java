package fr.iut.sae.controlers;

import fr.iut.sae.utils.Earthquakes;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController{

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private PieChart pieChart;

    private ArrayList<Earthquakes> data;


    public void setData(ArrayList<Earthquakes> data){
        this.data = data;
        this.initPieChart();
        this.initGeneralData();


    }

    public void initGeneralData(){

    }

    public void initPieChart(){

    }
}
