package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class Ward extends BaseModel <Ward> {

    private String ward_name, ward_bn, ward_keyword;
    private int id, cc_id;


    public Ward(int id, String ward_name, String ward_bn, String ward_keyword, int cc_id) {

        this.id = id;
        this.ward_name = ward_name;
        this.ward_bn = ward_bn;
        this.ward_keyword = ward_keyword;
        this.cc_id = cc_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getWard_bn() {
        return ward_bn;
    }

    public void setWard_bn(String ward_bn) {
        this.ward_bn = ward_bn;
    }

    public int getCc_id() {
        return cc_id;
    }

    public void setCc_id(int cc_id) {
        this.cc_id = cc_id;
    }

    public String getWard_keyword() {
        return ward_keyword;
    }

    public void setWard_keyword(String ward_keyword) {
        this.ward_keyword = ward_keyword;
    }


    public Ward parse(JSONObject jo) throws JSONException {

        int id = jo.getInt("id");
        String ward_en = jo.getString("ward_en");
        String ward_bn = jo.getString("ward_bn");
        String ward_keyword = jo.getString("ward_keyword");
        int cc_id = jo.getInt("_city_corporation");

        return new Ward(id, ward_en, ward_bn, ward_keyword, cc_id);
    }
}


