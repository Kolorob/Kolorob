package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class CityCorporation extends BaseModel <CityCorporation> {

    private String cityCorporation_name, cityCorporation_bn, cityCorporation_keyword;
    private int id, districtId;


    public CityCorporation(int id, String cityCorporation_name, String cityCorporation_bn, String cityCorporation_keyword, int districtId) {
        this.id = id;
        this.cityCorporation_name = cityCorporation_name;
        this.cityCorporation_bn = cityCorporation_bn;
        this.cityCorporation_keyword = cityCorporation_keyword;
        this.districtId = districtId;

    }

    public CityCorporation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityCorporation_name() {
        return cityCorporation_name;
    }

    public void setCityCorporation_name(String cityCorporation_name) {
        this.cityCorporation_name = cityCorporation_name;
    }

    public String getCityCorporation_bn() {
        return cityCorporation_bn;
    }

    public void setCityCorporation_bn(String cityCorporation_bn) {
        this.cityCorporation_bn = cityCorporation_bn;
    }

    public String getCityCorporation_keyword() {
        return cityCorporation_keyword;
    }

    public void setCityCorporation_keyword(String cityCorporation_keyword) {
        this.cityCorporation_keyword = cityCorporation_keyword;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public CityCorporation parse(JSONObject jo) throws JSONException {

        int id = jo.getInt("id");
        String cc_en = jo.getString("cc_en");
        String cc_bn = jo.getString("cc_bn");
        String cc_keyword = jo.getString("cc_keyword");
        int districtId = jo.getInt("_district");


        return new CityCorporation(id, cc_en, cc_bn, cc_keyword, districtId);
    }



}


