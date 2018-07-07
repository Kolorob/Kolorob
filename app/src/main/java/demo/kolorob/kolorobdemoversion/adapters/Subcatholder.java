package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by HP on 5/28/2016.
 */
public class Subcatholder {

    int id;
    String name;
    String name_bn;

    public Subcatholder(int id, String name, String name_bn) {
        this.id = id;
        this.name = name;
        this.name_bn = name_bn;
    }

    public int getId() {
        return id;
    }

    public void setSubcatid(int subcatid) {
        this.id = subcatid;
    }

    public String getSubcatname() {
        return name;
    }

    public void setSubcatname(String subcatname) {
        this.name = subcatname;
    }

    public String getName_bn(){
        return name_bn;
    }
    public void setName_bn(String name_bn){
        this.name_bn = name_bn;
    }
}
