package fr.iut.sae;

import fr.iut.sae.utils.Earthquakes;

import org.junit.jupiter.api.Test;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Cette classe contient les tests unitaires pour la classe Earthquakes.
 */
public class EarthquakesTest {

    /**
     * Génère un exemple de données de tremblements de terre.
     *
     * @return un tableau de chaînes de caractères représentant les données de tremblements de terre
     */
    public String[] generateEarthquakesDataExample(){
        return new String[]{"260002","1549/01/16","22 h","BASSIN DE MONTELIMAR (MONTELIMAR)","DAUPHINE","GROUPE DE SECOUSSES D UN ESSAIM","839077.32","6381274.63","44.52","4.75","5.5","INCERTAINE"};
    }

    /**
     * Teste s'il y a un identifiant.
     */
    @Test
    public void hasIdTest(){
        assertNotEquals(new Earthquakes(generateEarthquakesDataExample()).getId(), "");
    }

    /**
     * Teste si la taille des données est correcte.
     */
    @Test
    public void correctDataSizeTest(){
        assertEquals(generateEarthquakesDataExample().length, 12);
    }

    /**
     * Teste si le format de la date est correct.
     */
    @Test
    public void correctFormatDateTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getDate().matches("\\d{4}/") ||
                new Earthquakes(generateEarthquakesDataExample()).getDate().matches("\\d{4}/\\d{2}/\\d{2}") ||
                new Earthquakes(generateEarthquakesDataExample()).getDate().isEmpty());
    }

    /**
     * Teste si le format de la latitude est correct.
     */
    @Test
    public void correctFormatLatTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getLatitude().matches("[0-9.]+"));
    }

    /**
     * Teste si le format de la longitude est correct.
     */
    @Test
    public void correctFormatLongTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getLongitude().matches("[0-9.]+"));
    }

    /**
     * Teste si le format de l'intensité est correct.
     */
    @Test
    public void correctFormatIntensityTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getIntensity().matches("[0-9.]+"));
    }

    /**
     * Teste si le format de la représentation sous forme de chaîne de caractères est correct.
     */
    @Test
    public void correctFormatToStringTest(){
        assertEquals(new Earthquakes(generateEarthquakesDataExample()).toString(),
                "id: " + generateEarthquakesDataExample()[0] + " date: " + generateEarthquakesDataExample()[1] + " hour: " + generateEarthquakesDataExample()[2] + " name: " + generateEarthquakesDataExample()[3] + " region: " + generateEarthquakesDataExample()[4] + " shock: " + generateEarthquakesDataExample()[5] + " xrgf: " + generateEarthquakesDataExample()[6] + " yrgf: " + generateEarthquakesDataExample()[7] + " latitude: " + generateEarthquakesDataExample()[8] + " longitude: " + generateEarthquakesDataExample()[9] + " intensity: " + generateEarthquakesDataExample()[10] + " quality: " + generateEarthquakesDataExample()[11]);
    }

}
