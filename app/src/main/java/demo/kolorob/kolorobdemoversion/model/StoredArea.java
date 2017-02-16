package demo.kolorob.kolorobdemoversion.model;

/**
 * Created by HP on 2/13/2017.
 */

public class StoredArea {
    String wardid;
    String areaid;
    String areaBn;
    String Loc;

    public String getAreaBn() {
        return areaBn;
    }

    public void setAreaBn(String areaBn) {
        this.areaBn = areaBn;
    }

    public String getLoc() {
        return Loc;
    }

    public void setLoc(String loc) {
        Loc = loc;
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

    public StoredArea(String wardid, String areaid, String areaBn, String loc) {
        this.wardid = wardid;
        this.areaid = areaid;
        this.areaBn = areaBn;
        this.Loc = loc;
    }
}
