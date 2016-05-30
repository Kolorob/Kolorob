package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Yeakub Hassan Rafi on 26-Dec-15.
 */

public class SubCategoryItem implements Serializable {
    private int id;
    private int catId;
    private String subCatName;
    private String subCatHeader;
    private String subCatNameBn;
    private String subCatHeaderBn;
    public SubCategoryItem(int cat_id, int id,String subsubCatName,String subCatHeader,String subsubCatNameBn,String subCatHeaderBn) {
        this.id = id;
        this.catId = cat_id;
        this.subCatName = subsubCatName;
        this.subCatHeader = subCatHeader;
        this.subCatNameBn=subsubCatNameBn;
        this.subCatHeaderBn=subCatHeaderBn;
    }

    public SubCategoryItem(int id, String subsubCatName) {
        this.id = id;
        this.subCatName = subsubCatName;
    }

    public int getId() {
        return id;
    }
    public int getCatId()
    {
        return catId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubCatNameBn() {
        return subCatNameBn;
    }

    public void setSubCatNameBn(String subCatNameBn) {
        this.subCatNameBn = subCatNameBn;
    }

    public String getSubCatHeaderBn() {
        return subCatHeaderBn;
    }

    public void setSubCatHeaderBn(String subCatHeaderBn) {
        this.subCatHeaderBn = subCatHeaderBn;
    }

    public String getSubcatHeader()
    {
        return subCatHeader;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }


    @Override
    public String toString() {
        return "SubCategoryItem{" +
                "cat_id="+catId+
                ",id=" + id +
                ",subCatName='" + subCatName + '\'' +
                '}';
    }

    public static SubCategoryItem parseSubCategoryItem(JSONObject jo) throws JSONException {
        int cat_id = jo.getInt("category_id");
        int id = jo.getInt("subcategory_id");
        String name = jo.getString("subcategory_name");
        String head = jo.getString("subcategory_header");
        String namebn=jo.getString("subcategory_name_bangla");
        String headbn=jo.getString("subcategory_header_bangla");
        return new SubCategoryItem(cat_id,id, name,head,namebn,headbn);
    }
}
