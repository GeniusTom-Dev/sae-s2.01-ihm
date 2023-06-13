package fr.iut.sae.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe utilitaire pour la lecture des fichiers.
 * Cette classe fournit des méthodes pour diviser une ligne de texte CSV en éléments,
 * et pour lire un fichier CSV et renvoyer les données sous forme d'une liste d'objets Earthquakes.
 *
 * @author Even Tom
 * @version 1.0
 */
public class FileReader {
    /**
     * Divise une ligne de texte CSV en éléments.
     *
     * @param line la ligne de texte CSV à diviser.
     * @return un tableau de chaînes de caractères contenant les éléments de la ligne.
     */
    public String[] lineSplitter(String line){
        List<String> newLine = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()){
            if(c == ',' && !inQuotes){
                newLine.add(word.toString().replace("\"", ""));
                word.setLength(0);
            }else{
                word.append(c);
                if(c == '\"'){
                    inQuotes = !inQuotes;
                }
            }
        }

        newLine.add(word.toString().replace("\"", ""));

        return newLine.toArray(new String[0]);
    }

    /**
     * Lit un fichier CSV et renvoie les données sous forme d'une liste d'objets Earthquakes.
     *
     * @param file le fichier CSV à lire.
     * @return une liste d'objets Earthquakes contenant les données du fichier.
     */
    public ArrayList<Earthquakes> readCSV(File file) {
        ArrayList<Earthquakes> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line;
            reader.readLine(); // Ignorer la première ligne d'en-tête
            while ((line = reader.readLine()) != null) {
                String[] actualLine = lineSplitter(line);
                Earthquakes eq = new Earthquakes(actualLine);
                data.add(eq);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
