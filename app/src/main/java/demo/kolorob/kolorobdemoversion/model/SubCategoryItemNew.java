package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Israt Jahan on 26-Dec-15.
 */

public class SubCategoryItemNew implements Serializable {

    private int catId;
    private String CatName;
    private int subCatHeaderId;
    private String subCatHeaderNameEn;
    private String subCatHeaderNameBn;
    private int subCatId;
    private String subCatNameEn;
    private String subCatNameBn;

    public SubCategoryItemNew(int catId, String catName, int subCatHeaderId, String subCatHeaderNameEn, String subCatHeaderNameBn, int subCatId, String subCatNameEn, String subCatNameBn) {
        this.catId = catId;
        CatName = catName;
        this.subCatHeaderId = subCatHeaderId;
        this.subCatHeaderNameEn = subCatHeaderNameEn;
        this.subCatHeaderNameBn = subCatHeaderNameBn;
        this.subCatId = subCatId;
        this.subCatNameEn = subCatNameEn;
        this.subCatNameBn = subCatNameBn;
    }

    public SubCategoryItemNew(int id, String subsubCatName) {
        this.subCatId = id;
        this.subCatNameEn = subsubCatName;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public int getSubCatHeaderId() {
        return subCatHeaderId;
    }

    public void setSubCatHeaderId(int subCatHeaderId) {
        this.subCatHeaderId = subCatHeaderId;
    }

    public String getSubCatHeaderNameEn() {
        return subCatHeaderNameEn;
    }

    public void setSubCatHeaderNameEn(String subCatHeaderNameEn) {
        this.subCatHeaderNameEn = subCatHeaderNameEn;
    }

    public String getSubCatHeaderNameBn() {
        return subCatHeaderNameBn;
    }

    public void setSubCatHeaderNameBn(String subCatHeaderNameBn) {
        this.subCatHeaderNameBn = subCatHeaderNameBn;
    }

    public String getSubCatNameEn() {
        return subCatNameEn;
    }

    public void setSubCatNameEn(String subCatNameEn) {
        this.subCatNameEn = subCatNameEn;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatNameBn() {
        return subCatNameBn;
    }

    public void setSubCatNameBn(String subCatNameBn) {
        this.subCatNameBn = subCatNameBn;
    }

    @Override
    public String toString() {
        return "SubCategoryItem{" +
                "cat_id="+catId+
                ",id=" + subCatId +
                ",subCatName='" + subCatNameEn + '\'' +
                '}';
    }

    public static SubCategoryItemNew parseSubCategoryItem(JSONObject jo) throws JSONException {
        int cat_id = jo.getInt("cat_id");
        String catname=jo.getString("cat_label");

        int subcatheaderid = jo.getInt("sub_cat_id");
        String headen = jo.getString("sub_cat_label");
        String headbn = jo.getString("sub_cat_label_bn");
        int subcatid = jo.getInt("ref_id");
        String subcatname= jo.getString("ref_label");
        String subcatnamebn = jo.getString("ref_label_bn");
        return new SubCategoryItemNew(cat_id,catname, subcatheaderid,headen,headbn,subcatid,subcatname,subcatnamebn);
    }
}
