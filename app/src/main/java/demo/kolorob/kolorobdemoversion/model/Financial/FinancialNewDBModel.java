package demo.kolorob.kolorobdemoversion.model.Financial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class FinancialNewDBModel implements Serializable {

    int finId;
    CommonModel commonModel;
    String finType, serviceType;

    public int getFinId() {
        return finId;
    }

    public void setFinId(int finId) {
        this.finId = finId;
    }

    public CommonModel getCommonModel() {
        return commonModel;
    }

    public void setCommonModel(CommonModel commonModel) {
        this.commonModel = commonModel;
    }

    public String getFinType() {
        return finType;
    }

    public void setFinType(String finType) {
        this.finType = finType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public FinancialNewDBModel(int finId, CommonModel commonModel, String finType, String serviceType) {
        this.finId = finId;
        this.commonModel = commonModel;
        this.finType = finType;
        this.serviceType = serviceType;
    }



    public static FinancialNewDBModel parseFinancialNewDBModel (JSONObject jo) throws JSONException {

        CommonModel _commonModel = CommonModel.parseCommonModel(jo);
        int _finId = jo.getInt("id");

        String _finType = jo.getString("type");
        String _serviceType = jo.getString("service");

        return new FinancialNewDBModel(_finId, _commonModel, _finType, _serviceType);
    }
}
