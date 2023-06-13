package fr.iut.sae;

import fr.iut.sae.utils.Earthquakes;
import fr.iut.sae.utils.FileReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {

    @Test
    public void readCSVTest(){
        URL resourcesURL = getClass().getClassLoader().getResource("data-sisfrance.csv");
        assertNotNull(resourcesURL);
        File file = new File(resourcesURL.getFile());
        assertNotNull(new FileReader().readCSV(file));
    }
}
