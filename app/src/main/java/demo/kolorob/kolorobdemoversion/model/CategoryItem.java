package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by touhid on 10/30/15.
 * @author touhid
 */
public class CategoryItem implements Serializable, Comparable<CategoryItem>{
    private int id;
    private String nameEn, nameBn;
    private boolean catActive;

    public CategoryItem(int id, String nameEn, String nameBn, boolean catActive) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameBn = nameBn;
        this.catActive = catActive;
    }

    public CategoryItem(int id, String nameEn, String nameBn) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameBn = nameBn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameBn() {
        return nameBn;
    }

    public void setNameBn(String nameBn) {
        this.nameBn = nameBn;
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
                ", catName='" + nameEn + '\'' +
                ", catActive=" + catActive +
                '}';
    }

    public static CategoryItem parseCategoryItem(JSONObject jo) throws JSONException {
        int id = jo.getInt("id");
        String name = jo.getString("name");
        String nameBn = jo.getString("nameBn");
        boolean isActive = jo.getBoolean("active");

        return new CategoryItem(id, name, nameBn, isActive);
    }

    @Override
    public int compareTo(CategoryItem another) {
        int compareQuantity = ((CategoryItem) another).getId();

        //ascending order
        return this.id - compareQuantity;
    }
}
