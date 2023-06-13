package fr.iut.sae;

import fr.iut.sae.utils.DataFilter;
import fr.iut.sae.utils.Earthquakes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Cette classe contient les tests unitaires pour la classe DataFilter.
 */
public class DataFilterTest {

    /**
     * Génère un exemple de données pour les tests.
     *
     * @return Une ArrayList d'objets Earthquakes contenant les données de test.
     */
    public ArrayList<Earthquakes> generateDataExample(){
        ArrayList<Earthquakes> data = new ArrayList<>();
        data.add(new Earthquakes(new String[]{"260002","1549/01/16","22 h","BASSIN DE MONTELIMAR (MONTELIMAR)","DAUPHINE","GROUPE DE SECOUSSES D UN ESSAIM","839077.32","6381274.63","44.52","4.75","5","INCERTAINE"}));
        data.add(new Earthquakes(new String[]{"60010","1564/07/20","20 h","ALPES NICOISES (LA BOLLENE-VESUBIE)","ALPES MARITIMES","GROUPE DE SECOUSSES D UN ESSAIM","1046051.00","6331794.34","44.00","7.32","8","INCERTAINE"}));
        data.add(new Earthquakes(new String[]{"60028","1644/02/15","4 h 30 min","ALPES NICOISES (ROQUEBILLIERE)","ALPES MARITIMES","GROUPE DE SECOUSSES D UN ESSAIM","1038338.63","6325813.39","43.95","7.22","8","INCERTAINE"}));
        data.add(new Earthquakes(new String[]{"870007","1663/02/20","","BASSE-MARCHE (CHATEAUPONSAC)","LIMOUSIN","GROUPE DE SECOUSSES D UN ESSAIM","567474.42","6560721.20","46.13","1.28","4.5","INFORMATION ISOLÉE"}));
        data.add(new Earthquakes(new String[]{"670020","1669/10/10","12 h 45 min","PLAINE DE BASSE-ALSACE (STRASBOURG)","ALSACE","GROUPE DE SECOUSSES D UN ESSAIM","1050141.67","6841992.57","48.58","7.75","6","INCERTAINE"}));
        data.add(new Earthquakes(new String[]{"40024","1708/09/20","15 h 30 min","MOYENNE-DURANCE (MANOSQUE)","ALPES PROVENCALES","REPLIQUE","923846.98","6307759.07","43.83","5.78","2.5","INFORMATION ISOLÉE"}));
        data.add(new Earthquakes(new String[]{"640161","1714/07/06","14 h","PAYS BASQUE (BAYONNE)","PYRENEES OCCIDENTALES","GROUPE DE SECOUSSES D UN ESSAIM","337343.81","6275214.09","43.48","-1.48","5","INFORMATION ISOLÉE"}));
        data.add(new Earthquakes(new String[]{"130025","1730/10/11","15 h 45 min","CAMARGUE (ARLES)","PROVENCE","SECOUSSE INDIVIDUALISEE D UN ESSAIM","831721.91","6288506.58","43.68","4.63","3.5","INFORMATION ISOLÉE"}));
        data.add(new Earthquakes(new String[]{"820001","1743/03/07","21 h 15 min","BEARN ?","PYRENEES OCCIDENTALES","GROUPE DE SECOUSSES D UN ESSAIM","395382.74","6246213.43","43.25","-0.75","7","ARBITRAIRE"}));
        data.add(new Earthquakes(new String[]{"290002","1753/12/06","18 h","PAYS DE BREST (BREST)","BRETAGNE","GROUPE DE SECOUSSES D UN ESSAIM","146762.57","6835460.95","48.38","-4.48","4","INFORMATION ISOLÉE"}));
        return data;
    }

    /**
     * Teste le filtre sans aucun critère de recherche.
     */
    @Test
    public void testNoFilter(){
        ArrayList<Earthquakes> data = generateDataExample();
        assertEquals(data, new DataFilter().dataFilter(data, "", "", "", "", "", ""));
    }

    /**
     * Teste le filtre en spécifiant une première date.
     */
    @Test
    public void testFirstDate(){
        ArrayList<Earthquakes> originalData = generateDataExample();
        ArrayList<Earthquakes> testData = new ArrayList<>();
        testData.add(originalData.get(5));
        testData.add(originalData.get(6));
        testData.add(originalData.get(7));
        testData.add(originalData.get(8));
        testData.add(originalData.get(9));
        assertEquals(testData, new DataFilter().dataFilter(originalData, "1700", "", "", "", "", ""));
    }

    /**
     * Teste le filtre en spécifiant une dernière date.
     */
    @Test
    public void testLastDate(){
        ArrayList<Earthquakes> originalData = generateDataExample();
        ArrayList<Earthquakes> testData = new ArrayList<>();
        testData.add(originalData.get(0));
        testData.add(originalData.get(1));
        testData.add(originalData.get(2));
        testData.add(originalData.get(3));
        testData.add(originalData.get(4));
        assertEquals(testData, new DataFilter().dataFilter(originalData, "", "1700", "", "", "", ""));
    }

    /**
     * Teste le filtre en spécifiant à la fois une première et une dernière date.
     */
    @Test
    public void testFirstAndLastDate(){
        ArrayList<Earthquakes> originalData = generateDataExample();
        ArrayList<Earthquakes> testData = new ArrayList<>();
        testData.add(originalData.get(5));
        testData.add(originalData.get(6));
        testData.add(originalData.get(7));
        testData.add(originalData.get(8));
        assertEquals(testData, new DataFilter().dataFilter(originalData, "1700", "1743", "", "", "", ""));
    }

    /**
     * Teste le filtre en spécifiant des coordonnées géographiques.
     */
    @Test
    public void testCoordinates(){
        ArrayList<Earthquakes> originalData = generateDataExample();
        ArrayList<Earthquakes> testData = new ArrayList<>();
        testData.add(originalData.get(0));
        testData.add(originalData.get(5));
        assertEquals(testData,new DataFilter().dataFilter(originalData, "", "", "46","6", "245", ""));
    }

    /**
     * Teste le filtre en spécifiant un pays.
     */
    @Test
    public void testCountry(){
        ArrayList<Earthquakes> originalData = generateDataExample();
        ArrayList<Earthquakes> testData = new ArrayList<>();
        testData.add(originalData.get(1));
        testData.add(originalData.get(2));
        assertEquals(testData, new DataFilter().dataFilter(originalData, "", "", "", "", "", "ALPES MARITIMES"));
    }

    /**
     * Teste le filtre en spécifiant plusieurs critères de recherche.
     */
    @Test
    public void testMultipleFilters(){
        ArrayList<Earthquakes> originalData = generateDataExample();
        ArrayList<Earthquakes> testData = new ArrayList<>();
        testData.add(originalData.get(1));
        assertEquals(testData, new DataFilter().dataFilter(originalData, "1500", "1600", "","", "", "ALPES MARITIMES"));
    }
}
