package fr.iut.sae;

import fr.iut.sae.utils.Earthquakes;

import org.junit.jupiter.api.Test;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EarthquakesTest {

    public String[] generateEarthquakesDataExample(){
        return new String[]{"260002","1549/01/16","22 h","BASSIN DE MONTELIMAR (MONTELIMAR)","DAUPHINE","GROUPE DE SECOUSSES D UN ESSAIM","839077.32","6381274.63","44.52","4.75","5.5","INCERTAINE"};
    }

    @Test
    public void hasIdTest(){
        assertNotEquals(new Earthquakes(generateEarthquakesDataExample()).getId(), "");
    }

    @Test
    public void correctDataSizeTest(){
        assertEquals(generateEarthquakesDataExample().length, 12);
    }

    @Test
    public void correctFormatDateTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getDate().matches("\\d{4}/") ||
                new Earthquakes(generateEarthquakesDataExample()).getDate().matches("\\d{4}/\\d{2}/\\d{2}") ||
                new Earthquakes(generateEarthquakesDataExample()).getDate().isEmpty());
    }

    @Test
    public void correctFormatLatTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getLatitude().matches("[0-9.]+"));
    }

    @Test
    public void correctFormatLongTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getLongitude().matches("[0-9.]+"));
    }

    @Test
    public void correctFormatIntensityTest(){
        assertTrue(new Earthquakes(generateEarthquakesDataExample()).getIntensity().matches("[0-9.]+"));
    }

    @Test
    public void correctFormatToStringTest(){
        assertEquals(new Earthquakes(generateEarthquakesDataExample()).toString(),
        "id: " + generateEarthquakesDataExample()[0] + " date: " + generateEarthquakesDataExample()[1] + " hour: " + generateEarthquakesDataExample()[2] + " name: " + generateEarthquakesDataExample()[3] + " region: " + generateEarthquakesDataExample()[4] + " shock: " + generateEarthquakesDataExample()[5] + " xrgf: " + generateEarthquakesDataExample()[6] + " yrgf: " + generateEarthquakesDataExample()[7] + " latitude: " + generateEarthquakesDataExample()[8] + " longitude: " + generateEarthquakesDataExample()[9] + " intensity: " + generateEarthquakesDataExample()[10] + " quality: " + generateEarthquakesDataExample()[11]);
    }

}
