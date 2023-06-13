package fr.iut.sae.controlers;

import fr.iut.sae.App;
import fr.iut.sae.utils.Earthquakes;
import javafx.beans.property.ListProperty;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Contrôleur du tableau de bord.
 */
public class DashboardController {

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxisBarChart;

    @FXML
    private NumberAxis yAxisBarChart;

    @FXML
    private Label nbEarthQuackTotalLabel;

    @FXML
    private Label averageIntensityLabel;

    @FXML
    private Label maxIntensityLabel;

    @FXML
    private Label regionMostAffectedLabel;

    @FXML
    private NumberAxis xAxisLineChart;

    private ListProperty<Earthquakes> data;
    private ListProperty<Earthquakes> originalData;

    /**
     * Définit les données à afficher dans le tableau de bord et appelle les fonctions d'initialisation.
     *
     * @param data          Les données des séismes.
     * @param originalData  Les données d'origines des séismes.
     */
    public void setData(ListProperty<Earthquakes> data, ListProperty<Earthquakes> originalData) {
        this.data = data;
        this.originalData = originalData;
        this.initLineChart();
        this.initGeneralData();
        this.initPieChart();
        this.initBarChart();
    }

    /**
     * Initialise les données générales du tableau de bord.
     */
    public void initGeneralData() {
        double averageMagnitude = 0;
        double averageSize = 0;
        double maxIntensity = 0;
        HashMap<String, Integer> regionMostAffected = getEarthQuackByRegion();
        String regionMostAffectedString = "";

        nbEarthQuackTotalLabel.setText(String.valueOf(data.size()));

        for (Earthquakes earthQuack : data) {
            if (earthQuack.getIntensity() != null && !earthQuack.getIntensity().isEmpty()) {
                System.out.println(earthQuack.getIntensity());
                averageMagnitude += Double.parseDouble(earthQuack.getIntensity());
                averageSize++;

                if (Double.parseDouble(earthQuack.getIntensity()) > maxIntensity) {
                    maxIntensity = Double.parseDouble(earthQuack.getIntensity());
                }
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");

        averageIntensityLabel.setText(String.valueOf(df.format(averageMagnitude / averageSize)));
        maxIntensityLabel.setText(String.valueOf(maxIntensity));

        for (Map.Entry<String, Integer> entry : regionMostAffected.entrySet()) {
            if (regionMostAffectedString.isEmpty()) {
                regionMostAffectedString = entry.getKey();
            } else {
                if (entry.getValue() > regionMostAffected.get(regionMostAffectedString)) {
                    regionMostAffectedString = entry.getKey();
                }
            }
        }

        String formatRegionString = formatCaseString(regionMostAffectedString);

        regionMostAffectedLabel.setText(formatRegionString);
    }

    /**
     * Initialise le graphique en ligne.
     */
    public void initLineChart() {
        HashMap<Integer, Integer> earthquakesByYear = new HashMap<>();
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Year of Earthquake");
        int maxYear = 0;
        int minYear = 0;

        for (Earthquakes e : data) {
            if (!e.getDate().isEmpty()) {
                Integer year = (int) Math.round(Integer.parseInt(e.getDate().split("/")[0]) / 10.0) * 10;

                if (earthquakesByYear.containsKey(year)) {
                    earthquakesByYear.put(year, earthquakesByYear.get(year) + 1);
                } else {
                    earthquakesByYear.put(year, 1);
                }

                if (maxYear == 0 && minYear == 0) {
                    maxYear = year;
                    minYear = year;
                } else {
                    if (year < minYear) minYear = year;
                    if (year > maxYear) maxYear = year;
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : earthquakesByYear.entrySet()) {
            dataSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        xAxisLineChart.setLowerBound(minYear);
        xAxisLineChart.setUpperBound(maxYear);
        xAxisLineChart.setTickUnit(10);

        lineChart.getData().add(dataSeries);
    }

    /**
     * Initialise le graphique en secteurs.
     */
    public void initPieChart() {
        HashMap<String, Integer> earthquakesByShock = new HashMap<>();
        int countShock = 0;

        for (Earthquakes e : data) {
            if (!e.getShock().isEmpty()) {
                if (earthquakesByShock.containsKey(formatCaseString(e.getShock()))) {
                    earthquakesByShock.put(formatCaseString(e.getShock()), earthquakesByShock.get(formatCaseString(e.getShock())) + 1);
                    countShock++;
                } else {
                    earthquakesByShock.put(formatCaseString(e.getShock()), 1);
                    countShock++;
                }
            }
        }

        for (Map.Entry<String, Integer> entry : earthquakesByShock.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart.getData().add(data);
        }

        for (PieChart.Data data : pieChart.getData()) {
            data.setName(data.getName() + " (" + Math.round(data.getPieValue() / countShock * 100) + "%)");
        }
    }

    /**
     * Initialise le graphique en barres.
     */
    public void initBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Map.Entry<String, Integer> entry : getEarthQuackByRegion().entrySet()) {
            series.getData().add(new XYChart.Data<>(formatCaseString(entry.getKey()), entry.getValue()));
        }

        series.setName("Région");

        yAxisBarChart.setSide(Side.BOTTOM);
        xAxisBarChart.setSide(Side.LEFT);

        barChart.getData().add(series);
    }

    /**
     * Retourne le nombre de séismes par région.
     *
     * @return Le nombre de séismes par région.
     */
    public HashMap<String, Integer> getEarthQuackByRegion() {
        HashMap<String, Integer> earthquakesByRegion = new HashMap<>();

        for (Earthquakes earthQuack : data) {
            if (earthQuack.getRegion() != null) {
                if (earthquakesByRegion.containsKey(earthQuack.getRegion())) {
                    earthquakesByRegion.put(earthQuack.getRegion(), earthquakesByRegion.get(earthQuack.getRegion()) + 1);
                } else {
                    earthquakesByRegion.put(earthQuack.getRegion(), 1);
                }
            }
        }

        return earthquakesByRegion;
    }

    /**
     * Formate la chaîne de caractères en respectant la casse.
     *
     * @param value La chaîne de caractères à formater.
     * @return La chaîne de caractères formatée.
     */
    public String formatCaseString(String value) {
        String[] wordsString = value.split(" ");
        StringBuilder result = new StringBuilder();

        String firstLetter = wordsString[0].substring(0, 1).toUpperCase();
        String restOfWord = wordsString[0].substring(1).toLowerCase();
        String convertedWord = firstLetter + restOfWord;

        result.append(convertedWord);
        result.append(" ");

        for (int i = 1; i < wordsString.length; i++) {
            if (i % 3 == 0) {
                result.append(wordsString[i].toLowerCase());
                result.append("\n");
            } else {
                result.append(wordsString[i].toLowerCase());
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * Retourne à la page d'accueil.
     */
    public void toHome() {
        HomeController homeController = (HomeController) App.setScene("layout/home.fxml");
        assert homeController != null;
        homeController.setData(new ArrayList<>(originalData.get()));
    }

    /**
     * Accède à la page de recherche des séismes.
     */
    public void toResearch() {
        EarthquakesResearchController earthquakesResearchController = (EarthquakesResearchController) App.setScene("layout/EarthquakesResearch.fxml");
        assert earthquakesResearchController != null;
        earthquakesResearchController.setData(new ArrayList<>(originalData.get()), new ArrayList<>(data.get()));
    }
}
