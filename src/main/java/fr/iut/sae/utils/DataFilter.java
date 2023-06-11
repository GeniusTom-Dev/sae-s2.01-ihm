package fr.iut.sae.utils;

import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataFilter {

    public ArrayList<Earthquakes> dataFilter (String firstDate, String lastDate,ArrayList<Earthquakes> data,String longitude,String latitude,String radius){

        ArrayList<Earthquakes> filtredData = new ArrayList<>();
        filtredData.addAll(data);

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
                        filtredData.remove(i);
                    }
                }
                // si lastDate non renseigné
                else if (lastDate.isEmpty()) {
                    int firstDateYear = Integer.parseInt(firstDate);
                    // si la date earthquake est après la date demandé
                    if (firstDateYear > earthquakeDateYear) {
                        filtredData.remove(i);
                    }
                }
                // si les deux sont ont des valeurs
                else {
                    int firstDateYear = Integer.parseInt(firstDate);
                    int lastDateYear = Integer.parseInt(lastDate);
                    // si la date earthquake est après la date de début et avant celle de fin
                    if (firstDateYear > earthquakeDateYear || lastDateYear < earthquakeDateYear) {
                        filtredData.remove(i);
                    }
                }
            }
        }
        return filtredData;
    }
}
