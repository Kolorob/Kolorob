package demo.kolorob.kolorobdemoversion.model;

/**
 * Created by Mazharul.Islam1 on 12/8/2016.
 */

import java.util.ArrayList;


import java.util.ArrayList;

public class WardItem {

    private String name;
    private ArrayList<AreaItem> arealist = new ArrayList<AreaItem>();

    public WardItem(String name, ArrayList<AreaItem> arealist) {
        super();
        this.name = name;
        this.arealist = arealist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AreaItem> getArealist() {
        return arealist;
    }

    public void setArealist(ArrayList<AreaItem> arealist) {
        this.arealist = arealist;
    }

    ;
}

