package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by israt.jahan on 2/1/2017.
 */

public class AreaHolder {
    int wardid;
    String wardname;
  String areaname;
    String areanamebn;

    public AreaHolder(int wardid, String wardname, String areaname, String areanamebn) {
        this.wardid = wardid;
        this.wardname = wardname;
        this.areaname = areaname;
        this.areanamebn = areanamebn;
    }


    public String getAreanamebn() {
        return areanamebn;
    }

    public int getWardid() {
        return wardid;
    }

    public void setWardid(int wardid) {
        this.wardid = wardid;
    }

    public String getWardname() {
        return wardname;
    }

    public void setWardname(String wardname) {
        this.wardname = wardname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreanamebn(String areanamebn) {
        this.areanamebn = areanamebn;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
}
