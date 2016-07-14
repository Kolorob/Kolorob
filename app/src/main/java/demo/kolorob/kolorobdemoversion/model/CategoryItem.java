package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by touhid on 10/30/15.
 * @author touhid
 */
public class CategoryItem implements Serializable,Comparable<CategoryItem>{
    private int id;
    private String catName;
    private boolean catActive;

    public CategoryItem(int id, String catName, boolean catActive) {
        this.id = id;
        this.catName = catName;
        this.catActive = catActive;
    }

    public CategoryItem(int id, String catName) {
        this.id = id;
        this.catName = catName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isCatActive() {
        return catActive;
    }

    public void setCatActive(boolean catActive) {
        this.catActive = catActive;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "id=" + id +
                ", catName='" + catName + '\'' +
                ", catActive=" + catActive +
                '}';
    }

    public static CategoryItem parseCategoryItem(JSONObject jo) throws JSONException {
        int id = jo.getInt("id");
        String name = jo.getString("name");
        boolean isActive = jo.getBoolean("active");

        return new CategoryItem(id, name, isActive);
    }

    @Override
    public int compareTo(CategoryItem another) {
        int compareQuantity = ((CategoryItem) another).getId();

        //ascending order
        return this.id - compareQuantity;
    }
}
