package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class HealthNewDBModelMain implements Serializable {

    int healthId;
    CommonModel commonModel;
    String instituteType;


    public int getHealthId() {
        return healthId;
    }

    public void setHealthId(int healthId) {
        this.healthId = healthId;
    }

    public CommonModel getCommonModel() {
        return commonModel;
    }

    public void setCommonModel(CommonModel commonModel) {
        this.commonModel = commonModel;
    }

    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }

    public HealthNewDBModelMain(int healthId, CommonModel commonModel, String instituteType) {
        this.healthId = healthId;
        this.commonModel = commonModel;
        this.instituteType = instituteType;
    }

    public static HealthNewDBModelMain parseHealthNewDBModelMain (JSONObject jo) throws JSONException {

        CommonModel _commonModel = CommonModel.parseCommonModel(jo);
        int _healthId = jo.getInt("id");
        String _instituteType = jo.getString("institute_type");

        return new HealthNewDBModelMain(_healthId, _commonModel, _instituteType);
    }
}
