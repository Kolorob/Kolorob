package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Israt Jahan on 26-Dec-15.
 */

public class SubCategoryItemNew implements Serializable {

    private int catId;
    private String CatLabel;
    private int subCatId;
    private String subCatLabel;
    private String subCatLabelBn;
    private int refId;
    private String refLabel;
    private String refLabelBn;

    public SubCategoryItemNew(int catId, String catLabel, int subCatId, String subCatLabel, String subCatLabelBn, int refId, String refLabel, String refLabelBn) {
        this.catId = catId;
        CatLabel = catLabel;
        this.subCatId = subCatId;
        this.subCatLabel = subCatLabel;
        this.subCatLabelBn = subCatLabelBn;
        this.refId = refId;
        this.refLabel = refLabel;
        this.refLabelBn = refLabelBn;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatLabel() {
        return CatLabel;
    }

    public void setCatLabel(String catLabel) {
        CatLabel = catLabel;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatLabel() {
        return subCatLabel;
    }

    public void setSubCatLabel(String subCatLabel) {
        this.subCatLabel = subCatLabel;
    }

    public String getSubCatLabelBn() {
        return subCatLabelBn;
    }

    public void setSubCatLabelBn(String subCatLabelBn) {
        this.subCatLabelBn = subCatLabelBn;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public String getRefLabel() {
        return refLabel;
    }

    public void setRefLabel(String refLabel) {
        this.refLabel = refLabel;
    }

    public String getRefLabelBn() {
        return refLabelBn;
    }

    public void setRefLabelBn(String refLabelBn) {
        this.refLabelBn = refLabelBn;
    }

    public static SubCategoryItemNew parseSubCategoryItem(JSONObject jo) throws JSONException {
        int cat_id = jo.getInt("cat_id");
        String catname=jo.getString("cat_label");

        int subcatid = jo.getInt("sub_cat_id");
        String subcatLabele = jo.getString("sub_cat_label");
        String subcatLabelB = jo.getString("sub_cat_label_bn");
        int refId = jo.getInt("ref_id");
        String subcatname= jo.getString("ref_label");
        String subcatnamebn = jo.getString("ref_label_bn");
        return new SubCategoryItemNew(cat_id,catname,subcatid, subcatLabele,subcatLabelB,refId,subcatname,subcatnamebn);
    }
}
