package fr.iut.sae.controlers;

import fr.iut.sae.utils.Earthquakes;
import javafx.beans.property.ListProperty;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController{

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private PieChart pieChart;

//    Import Labels from FXML
    @FXML
    private Label nbEarthQuackTotal;

    private ListProperty<Earthquakes> data;
//    private ArrayList<Earthquakes> data;


    public void setData(ListProperty<Earthquakes> data){
        this.data = data;
        this.initPieChart();
        this.initGeneralData();
    }

    public void initGeneralData(){
//        System.out.println(data.size());
//        nbEarthQuackTotal.setText("coucou");
//        nbEarthQuackTotal.setText(String.valueOf(data.size()));
    }

    public void initPieChart(){

    }
}
