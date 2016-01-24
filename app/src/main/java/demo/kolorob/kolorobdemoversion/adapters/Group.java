package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by mity on 1/17/16.
 */
import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;


public class Group {

    public String string;
    public  List<EducationServiceProviderItem> children = new ArrayList<EducationServiceProviderItem>();


    public List<EducationServiceProviderItem> getchildren() {
        return children;
    }

    public Group(String string) {
        this.string = string;
    }
    public void setchildren(List<EducationServiceProviderItem> itemList) {
        this.children = children;
    }
}