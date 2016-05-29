package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by HP on 5/28/2016.
 */
public class Subcatholder {
    int subcatid;
    String subcatname;
    public Subcatholder(int subcatid, String subcatname) {
        this.subcatid = subcatid;
        this.subcatname = subcatname;
    }

    public int getSubcatid() {
        return subcatid;
    }

    public void setSubcatid(int subcatid) {
        this.subcatid = subcatid;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }
}
