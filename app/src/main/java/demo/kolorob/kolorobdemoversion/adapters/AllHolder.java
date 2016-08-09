package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by israt.jahan on 5/26/2016.
 */
public class AllHolder {
    int nodeid;
    String refnum;
    String nameen;
    String namebn;
    int catid;
    String lat;
    String lon;
    String contact;
    String contact2;
    String subref;

    public AllHolder(int nodeid, String refnum, String nameen, String namebn, int catid,
            String subref,String contact, String contact2,String lat,String lon) {
        this.nodeid = nodeid;
        this.refnum = refnum;
        this.nameen = nameen;
        this.namebn = namebn;
        this.catid = catid;
        this.subref=subref;
        this.contact = contact;
        this.contact2=contact2;
        this.lat = lat;
        this.lon = lon;
    }

    public int getNodeid() {
        return nodeid;
    }

    public void setNodeid(int nodeid) {
        this.nodeid = nodeid;
    }

    public String getRefnum() {
        return refnum;
    }

    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    public String getNamebn() {
        return namebn;
    }

    public void setNamebn(String namebn) {
        this.namebn = namebn;
    }

    public int getCatid() {
        return catid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getSubref() {
        return subref;
    }

    public void setSubref(String subref) {
        this.subref = subref;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }
}
