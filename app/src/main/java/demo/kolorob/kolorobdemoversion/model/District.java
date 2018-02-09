package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shamima.brishti on 2/9/18.
 */

public class District extends BaseModel <District>{

    private int id;
    private String district_en, district_bn, district_keyword;

    public District(){

    }

    public District(int id, String district_en, String district_bn, String district_keyword) {
        this.id = id;
        this.district_en = district_en;
        this.district_bn = district_bn;
        this.district_keyword = district_keyword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict_en() {
        return district_en;
    }

    public void setDistrict_en(String district_en) {
        this.district_en = district_en;
    }

    public String getDistrict_bn() {
        return district_bn;
    }

    public void setDistrict_bn(String district_bn) {
        this.district_bn = district_bn;
    }

    public String getDistrict_keyword() {
        return district_keyword;
    }

    public void setDistrict_keyword(String district_keyword) {
        this.district_keyword = district_keyword;
    }

    public District parse(JSONObject jo) throws JSONException {

        int id = jo.getInt("id");
        String district_en = jo.getString("cc_en");
        String district_bn = jo.getString("cc_bn");
        String district_keyword = jo.getString("cc_keyword");

        return new District(id, district_en, district_bn, district_keyword);
    }

}
