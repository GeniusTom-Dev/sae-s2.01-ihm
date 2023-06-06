package fr.iut.sae.utils;

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

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getShock() {
        return shock;
    }

    public String getXrgf() {
        return xrgf;
    }

    public String getYrgf() {
        return yrgf;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getQuality() {
        return quality;
    }

    public String toString() {
    	return "id: " + id + " date: " + date + " hour: " + hour + " name: " + name + " region: " + region + " shock: " + shock + " xrgf: " + xrgf + " yrgf: " + yrgf + " latitude: " + latitude + " longitude: " + longitude + " intensity: " + intensity + " quality: " + quality;
    }

}
