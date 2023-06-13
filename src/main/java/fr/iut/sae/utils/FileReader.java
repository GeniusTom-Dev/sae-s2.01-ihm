package fr.iut.sae.utils;

import java.io.*;
import java.util.ArrayList;


public class FileReader {
    public FileReader() {}

    public ArrayList<Earthquakes> readCSV(File file) {
        ArrayList<Earthquakes> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] actualLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Earthquakes eq = new Earthquakes(actualLine);
                data.add(eq);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}