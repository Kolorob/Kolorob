package demo.kolorob.kolorobdemoversion.model;

/**
 * Created by Mazharul.Islam1 on 12/8/2016.
 */
public class AreaItem {

    private String name = "";
    private String bname = "";

    public AreaItem(String name,String bname) {
        super();
        this.name = name;
        this.bname=bname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}

