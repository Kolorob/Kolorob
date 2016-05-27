package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by israt.jahan on 5/26/2016.
 */
public class AllHolder {
    String nodeid;
    int refnum;
    String nameen;
    String namebn;
    int catid;

    public AllHolder(String nodeid, int refnum, String nameen, String namebn, int catid) {
        this.nodeid = nodeid;
        this.refnum = refnum;
        this.nameen = nameen;
        this.namebn = namebn;
        this.catid = catid;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public int getRefnum() {
        return refnum;
    }

    public void setRefnum(int refnum) {
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

    public void setCatid(int catid) {
        this.catid = catid;
    }
}
