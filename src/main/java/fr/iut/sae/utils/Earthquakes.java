package fr.iut.sae.utils;

import java.util.Arrays;

/**
 * Représente une classe pour les séismes.
 * Cette classe contient des informations sur les séismes tels que l'identifiant, la date, l'heure, le nom, la région, le choc,
 * les coordonnées, l'intensité et la qualité.
 *
 * @author Even Tom
 * @version 1.0
 */
public class Earthquakes {
    private String id;
    private String date;
    private String hour;
    private String name;
    private String region;
    private String shock;
    private String xrgf;
    private String yrgf;
    private String latitude;
    private String longitude;
    private String intensity;
    private String quality;

    /**
     * Constructeur de la classe Earthquakes.
     *
     * @param data tableau de données contenant les informations sur le séisme.
     */
    public Earthquakes(String[] data) {
        this.id = data[0].replace("\"", "");
        this.date = data[1].replace("\"", "");
        this.hour = data[2].replace("\"", "");
        this.name = data[3].replace("\"", "");
        this.region = data[4].replace("\"", "");
        this.shock = data[5].replace("\"", "");
        this.xrgf = data[6].replace("\"", "");
        this.yrgf = data[7].replace("\"", "");
        this.latitude = data[8].replace("\"", "");
        this.longitude = data[9].replace("\"", "");
        this.intensity = data[10].replace("\"", "");
        this.quality = data[11].replace("\"", "");
    }

    /**
     * Renvoie l'identifiant du séisme.
     *
     * @return l'identifiant du séisme.
     */
    public String getId() {
        return id;
    }

    /**
     * Renvoie la date du séisme.
     *
     * @return la date du séisme.
     */
    public String getDate() {
        return date;
    }

    /**
     * Renvoie le nom du séisme.
     *
     * @return le nom du séisme.
     */
    public String getName() {
        return name;
    }

    /**
     * Renvoie la région du séisme.
     *
     * @return la région du séisme.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Renvoie le choc du séisme.
     *
     * @return le choc du séisme.
     */
    public String getShock() {
        return shock;
    }

    /**
     * Renvoie la latitude du séisme.
     *
     * @return la latitude du séisme.
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Renvoie la longitude du séisme.
     *
     * @return la longitude du séisme.
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Renvoie l'intensité du séisme.
     *
     * @return l'intensité du séisme.
     */
    public String getIntensity() {
        return intensity;
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de l'objet Earthquakes.
     *
     * @return une représentation sous forme de chaîne de caractères de l'objet Earthquakes.
     */
    public String toString() {
        return "id: " + id + " date: " + date + " hour: " + hour + " name: " + name + " region: " + region + " shock: " + shock + " xrgf: " + xrgf + " yrgf: " + yrgf + " latitude: " + latitude + " longitude: " + longitude + " intensity: " + intensity + " quality: " + quality;
    }
}
