package fr.iut.sae.utils;

import java.io.*;
import java.util.ArrayList;


public class CSVReader {
    private File file;
    private ArrayList<Earthquakes> data = new ArrayList<>();


    public CSVReader(File file) {
        this.file = file;
        this.readCSV();
    }

    public void readCSV(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
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

        for (Earthquakes eq : data){
            System.out.println(eq.toString());
        }
    }

}
