package demo.kolorob.kolorobdemoversion.model;

/**
 * Created by HP on 2/13/2017.
 */

public class StoredArea {
    int id;
    String ward, area, parentArea, areaBn;
    String Lat, Lon;

    public String getAreaBn() {
        return areaBn;
    }

    public void setAreaBn(String areaBn) {
        this.areaBn = areaBn;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public StoredArea(int id, String ward, String area, String areaBn, String parentArea, String lat, String lon) {
        this.id = id;
        this.ward = ward;
        this.area = area;
        this.parentArea = parentArea;
        this.areaBn = areaBn;
        Lat = lat;
        Lon = lon;
    }
}
