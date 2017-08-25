package demo.kolorob.kolorobdemoversion.model;

/**
 * Created by HP on 2/13/2017.
 */

public class StoredArea {
    String wardid;
    String areaid;
    String areaBn;
    String Lat;
    String Lon;

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

    public String getWardid() {
        return wardid;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public StoredArea(String wardid, String areaid, String areaBn, String lat, String lon) {
        this.wardid = wardid;
        this.areaid = areaid;
        this.areaBn = areaBn;
        this.Lat = lat;
        this.Lon = lon;
    }
}
