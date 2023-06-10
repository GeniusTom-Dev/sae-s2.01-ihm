package fr.iut.sae.utils;

import java.io.*;
import java.util.ArrayList;


public class CSVReader {
    public CSVReader() {}

    public ArrayList<Earthquakes> readCSV(File file) {
        ArrayList<Earthquakes> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] actualLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println(actualLine.length);
                Earthquakes eq = new Earthquakes(actualLine);
                data.add(eq);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
