package demo.kolorob.kolorobdemoversion.model;

/**
 * Created by HP on 2/13/2017.
 */

public class StoredArea {
    String wardid;
    String areaid;

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

    public StoredArea(String wardid, String areaid) {
        this.wardid = wardid;
        this.areaid = areaid;
    }
}
