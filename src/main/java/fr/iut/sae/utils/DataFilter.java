package fr.iut.sae.utils;

import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataFilter {

    private final ArrayList<Earthquakes> filteredData;

    public DataFilter() {
        this.filteredData = new ArrayList<>();
    }

    public ArrayList<Earthquakes> dataFilter (ArrayList<Earthquakes> data,String firstDate, String lastDate,String latitudeStr,
                                              String longitudeStr, String radiusStr, String country){
        filteredData.clear();
        filteredData.addAll(data);

        // filtre sur les dates
        if (!firstDate.isEmpty() || !lastDate.isEmpty()) {
            for (int i = data.size()-1; i >= 0; i--) {
                // récupère l'année de l'objet earthquake
                int earthquakeDateYear = Integer.parseInt(data.get(i).getDate().split("/")[0]);
                // si firsDate non renseigné
                if (firstDate.isEmpty()) {
                    int lastDateYear = Integer.parseInt(lastDate);
                    // si la date earthquake est avant la date demandé
                    if (lastDateYear < earthquakeDateYear) {
                        filteredData.remove(i);
                    }
                }
                // si lastDate non renseigné
                else if (lastDate.isEmpty()) {
                    int firstDateYear = Integer.parseInt(firstDate);
                    // si la date earthquake est après la date demandé
                    if (firstDateYear > earthquakeDateYear) {
                        filteredData.remove(i);
                    }
                }
                // si les deux sont ont des valeurs
                else {
                    int firstDateYear = Integer.parseInt(firstDate);
                    int lastDateYear = Integer.parseInt(lastDate);
                    // si la date earthquake est après la date de début et avant celle de fin
                    if (firstDateYear > earthquakeDateYear || lastDateYear < earthquakeDateYear) {
                        filteredData.remove(i);
                    }
                }
            }
        }
        if (!latitudeStr.isEmpty() && !longitudeStr.isEmpty() && !radiusStr.isEmpty()) {
            double latitudeDouble = Double.parseDouble(latitudeStr);
            double longitudeDouble = Double.parseDouble(longitudeStr);
            double radiusDouble = Double.parseDouble(radiusStr);

            for (int i = filteredData.size() - 1; i >= 0; i--) {
                Earthquakes earthquake = filteredData.get(i);
                // verifie que earthquake a bien un localisation en longitude et latitude
                if (!earthquake.getLatitude().isEmpty() && !earthquake.getLongitude().isEmpty()) {

                    double earthquakeLatitude = Double.parseDouble(earthquake.getLatitude());
                    double earthquakeLongitude = Double.parseDouble(earthquake.getLongitude());

                    double distance = calculateDistance(latitudeDouble, longitudeDouble, earthquakeLatitude, earthquakeLongitude);

                    // Vérifier si la distance est inférieure ou égale au rayon
                    if (distance > radiusDouble) {
                        filteredData.remove(i);
                    }
                }
                else {
                    filteredData.remove(i);
                }
            }

        }
        else if (!country.isEmpty()) {
            for (int i = filteredData.size() - 1; i >= 0; i--) {
                Earthquakes earthquake = filteredData.get(i);
                if (!earthquake.getRegion().equals(country)) {
                    filteredData.remove(i);
                }
            }
        }
        return filteredData;
    }

    private double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        // Rayon de la Terre en kilomètres
        double earthRadius = 6371;

        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLon = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c;

        return distance;
    }

}
