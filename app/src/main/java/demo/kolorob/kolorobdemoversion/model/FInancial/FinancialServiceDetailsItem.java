package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/7/2016.
 */
public class FinancialServiceDetailsItem {
    private String finId;

    private String servicecost;
    private String serviceremark;
    private String servicetype;
    private String servicesubtype;

    public FinancialServiceDetailsItem(String finId, String servicecost, String serviceremark, String servicetype, String servicesubtype) {
        this.finId = finId;
        this.servicecost = servicecost;
        this.serviceremark = serviceremark;
        this.servicetype = servicetype;
        this.servicesubtype = servicesubtype;
    }

    public String getFinId() {
        return finId;
    }

    public void setFinId(String finId) {
        this.finId = finId;
    }

    public String getServicecost() {
        return servicecost;
    }

    public void setServicecost(String servicecost) {
        this.servicecost = servicecost;
    }

    public String getServiceremark() {
        return serviceremark;
    }

    public void setServiceremark(String serviceremark) {
        this.serviceremark = serviceremark;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getServicesubtype() {
        return servicesubtype;
    }

    public void setServicesubtype(String servicesubtype) {
        this.servicesubtype = servicesubtype;
    }

    public static FinancialServiceDetailsItem parseFinancialServiceDetailsItem(JSONObject jo) throws JSONException {
        String _finId = jo.getString("id");

        String _servicecost = jo.getString("financial_cost");
        String _serviceremark = jo.getString("financial_remarks");
        String _servicetype = jo.getJSONObject("financial_services").getString("type");
        String _servicesubtype = jo.getJSONObject("financial_services").getString("sub_type");
        return new FinancialServiceDetailsItem(_finId,
                 _servicecost,_serviceremark,_servicetype,_servicesubtype);
    }
}
