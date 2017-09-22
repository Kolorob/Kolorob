package demo.kolorob.kolorobdemoversion.model.Government;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class GovernmentNewDBModel implements Serializable {

    int govId;
    CommonModel commonModel;
    String serviceName;

    public int getGovId() {
        return govId;
    }

    public void setGovId(int govId) {
        this.govId = govId;
    }

    public CommonModel getCommonModel() {
        return commonModel;
    }

    public void setCommonModel(CommonModel commonModel) {
        this.commonModel = commonModel;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public GovernmentNewDBModel(int govId, CommonModel commonModel, String serviceName) {
        this.govId = govId;
        this.commonModel = commonModel;
        this.serviceName = serviceName;
    }

    public static GovernmentNewDBModel parseGovernmentNewDBModel (JSONObject jo) throws JSONException {
        CommonModel _commonModel = CommonModel.parseCommonModel(jo);
        int _govId = jo.getInt("id");
        String _serviceName = jo.getString("service_name");

        return new GovernmentNewDBModel(_govId, _commonModel, _serviceName);
    }
}
