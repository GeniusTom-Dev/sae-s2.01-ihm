package fr.iut.sae;

import fr.iut.sae.utils.Earthquakes;
import fr.iut.sae.utils.FileReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Cette classe contient les tests unitaires pour la classe FileReader.
 */
public class FileReaderTest {

    /**
     * Teste la lecture d'un fichier CSV.
     */
    @Test
    public void readCSVTest(){
        URL resourcesURL = getClass().getClassLoader().getResource("data-sisfrance.csv");
        assertNotNull(resourcesURL);
        File file = new File(resourcesURL.getFile());
        assertNotNull(new FileReader().readCSV(file));
    }
}
