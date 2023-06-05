package fr.iut.sae.utils;

import java.io.*;
import java.util.ArrayList;


public class CSVReader {
    private String fileName;
    private ArrayList<Earthquakes> data = new ArrayList<>();


    public CSVReader(String fileName) {
        this.fileName = "/" + fileName;
        this.readCSV();
    }

    public void readCSV(){
        try {
            InputStream file = getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null){
                String[] actualLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Earthquakes eq = new Earthquakes(actualLine);
                data.add(eq);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
