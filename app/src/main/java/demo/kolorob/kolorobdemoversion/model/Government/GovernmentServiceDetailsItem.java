package demo.kolorob.kolorobdemoversion.model.Government;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/7/2016.
 */
public class GovernmentServiceDetailsItem {
    private int finId;
    private int serviceproviderId;
    private String servicecost;
    private String responsibleperson;
    private String detailstep;
    private String servicetype;
    private String servicesubtype;


    public GovernmentServiceDetailsItem(int finId, int serviceproviderId, String servicecost, String responsibleperson,
                                        String detailstep, String servicetype, String servicesubtype) {
        this.finId = finId;
        this.serviceproviderId = serviceproviderId;
        this.servicecost = servicecost;
        this.responsibleperson = responsibleperson;
        this.detailstep = detailstep;
        this.servicetype = servicetype;
        this.servicesubtype = servicesubtype;
    }

    public int getFinId() {
        return finId;
    }

    public int getServiceproviderId() {
        return serviceproviderId;
    }

    public void setServiceproviderId(int serviceproviderId) {
        this.serviceproviderId = serviceproviderId;
    }

    public void setFinId(int finId) {
        this.finId = finId;
    }

    public String getServicecost() {
        return servicecost;
    }

    public void setServicecost(String servicecost) {
        this.servicecost = servicecost;
    }

    public String getResponsibleperson() {
        return responsibleperson;
    }

    public void setResponsibleperson(String responsibleperson) {
        this.responsibleperson = responsibleperson;
    }

    public String getDetailstep() {
        return detailstep;
    }

    public void setDetailstep(String detailstep) {
        this.detailstep = detailstep;
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

    public static GovernmentServiceDetailsItem parseGovernmentServiceDetailsItem(JSONObject jo) throws JSONException {
        int _finId = jo.getInt("id");
        int _sproviderid = jo.getInt("_service_provider");
        String _servicecost = jo.getString("cost");
        String _person = jo.getString("responsible_person");
        String _detail = jo.getString("detail_step");
        String _servicetype = jo.getJSONObject("gov_service").getString("type");
        String _servicesubtype = jo.getJSONObject("gov_service").getString("sub_type");
        return new GovernmentServiceDetailsItem(_finId,_sproviderid,
                 _servicecost,_person,_detail,_servicetype,_servicesubtype);
    }
}
