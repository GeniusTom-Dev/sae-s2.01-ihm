package fr.iut.sae.utils;

import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe utilitaire pour filtrer les données d'objets Earthquakes.
 * Cette classe fournit une méthode pour filtrer une liste d'objets Earthquakes en fonction de certains critères.
 *
 * @author Tamarin Maxime
 * @version 1.0
 */
public class DataFilter {

    private final ArrayList<Earthquakes> filteredData;

    /**
     * Constructeur de la classe DataFilter.
     * Initialise la liste des données filtrées.
     */
    public DataFilter() {
        this.filteredData = new ArrayList<>();
    }

    /**
     * Filtre les données d'objets Earthquakes en fonction des critères spécifiés.
     *
     * @param data la liste des objets Earthquakes à filtrer.
     * @param firstDate la date de début du filtre.
     * @param lastDate la date de fin du filtre.
     * @param latitudeStr la latitude pour le filtre de localisation.
     * @param longitudeStr la longitude pour le filtre de localisation.
     * @param radiusStr le rayon pour le filtre de localisation.
     * @param country le pays pour le filtre de région.
     * @return une liste d'objets Earthquakes filtrés en fonction des critères spécifiés.
     */
    public ArrayList<Earthquakes> dataFilter(ArrayList<Earthquakes> data, String firstDate, String lastDate, String latitudeStr,
                                             String longitudeStr, String radiusStr, String country){
        filteredData.clear();
        filteredData.addAll(data);

        // Filtre sur les dates
        if (!firstDate.isEmpty() || !lastDate.isEmpty()) {
            for (int i = data.size()-1; i >= 0; i--) {
                // Récupère l'année de l'objet Earthquakes
                int earthquakeDateYear = Integer.parseInt(data.get(i).getDate().split("/")[0]);
                // Si firstDate n'est pas renseigné
                if (firstDate.isEmpty()) {
                    int lastDateYear = Integer.parseInt(lastDate);
                    // Si la date de l'Earthquakes est avant la date demandée
                    if (lastDateYear < earthquakeDateYear) {
                        filteredData.remove(i);
                    }
                }
                // Si lastDate n'est pas renseigné
                else if (lastDate.isEmpty()) {
                    int firstDateYear = Integer.parseInt(firstDate);
                    // Si la date de l'Earthquakes est après la date demandée
                    if (firstDateYear > earthquakeDateYear) {
                        filteredData.remove(i);
                    }
                }
                // Si les deux sont ont des valeurs
                else {
                    int firstDateYear = Integer.parseInt(firstDate);
                    int lastDateYear = Integer.parseInt(lastDate);
                    // Si la date de l'Earthquakes est après la date de début et avant celle de fin
                    if (firstDateYear > earthquakeDateYear || lastDateYear < earthquakeDateYear) {
                        filteredData.remove(i);
                    }
                }
            }
        }

        // Filtre sur la localisation
        if (!latitudeStr.isEmpty() && !longitudeStr.isEmpty() && !radiusStr.isEmpty()) {
            double latitudeDouble = Double.parseDouble(latitudeStr);
            double longitudeDouble = Double.parseDouble(longitudeStr);
            double radiusDouble = Double.parseDouble(radiusStr);

            for (int i = filteredData.size() - 1; i >= 0; i--) {
                Earthquakes earthquake = filteredData.get(i);
                // Vérifie que l'Earthquakes a bien une localisation en latitude et longitude
                if (!earthquake.getLatitude().isEmpty() && !earthquake.getLongitude().isEmpty()) {

                    double earthquakeLatitude = Double.parseDouble(earthquake.getLatitude());
                    double earthquakeLongitude = Double.parseDouble(earthquake.getLongitude());

                    double distance = calculateDistance(latitudeDouble, longitudeDouble, earthquakeLatitude, earthquakeLongitude);

                    // Vérifie si la distance est inférieure ou égale au rayon
                    if (distance > radiusDouble) {
                        filteredData.remove(i);
                    }
                }
                else {
                    filteredData.remove(i);
                }
            }
        }
        // Filtre sur la région
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

    /**
     * Calcule la distance entre deux points géographiques en utilisant la formule de la distance entre deux points sur une sphère.
     *
     * @param latitude1 la latitude du premier point.
     * @param longitude1 la longitude du premier point.
     * @param latitude2 la latitude du deuxième point.
     * @param longitude2 la longitude du deuxième point.
     * @return la distance en kilomètres entre les deux points géographiques.
     */
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
