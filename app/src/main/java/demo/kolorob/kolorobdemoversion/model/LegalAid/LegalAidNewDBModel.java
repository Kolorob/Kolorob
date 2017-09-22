package demo.kolorob.kolorobdemoversion.model.LegalAid;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class LegalAidNewDBModel implements Serializable {

    int legalId;
    CommonModel commonModel;
    String service;

    public int getLegalId() {
        return legalId;
    }

    public void setLegalId(int legalId) {
        this.legalId = legalId;
    }

    public CommonModel getCommonModel() {
        return commonModel;
    }

    public void setCommonModel(CommonModel commonModel) {
        this.commonModel = commonModel;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LegalAidNewDBModel(int legalId, CommonModel commonModel, String service) {
        this.legalId = legalId;
        this.commonModel = commonModel;
        this.service = service;
    }

    public static LegalAidNewDBModel parseLegalAidNewDBModel (JSONObject jo) throws JSONException {

        CommonModel _commonModel = CommonModel.parseCommonModel(jo);
        int _healthId = jo.getInt("id");
        String _service = jo.getString("sevice");

        return new LegalAidNewDBModel(_healthId, _commonModel, _service);
    }
}
