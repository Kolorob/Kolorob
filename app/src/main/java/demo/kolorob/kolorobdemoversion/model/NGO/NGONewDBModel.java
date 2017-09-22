package demo.kolorob.kolorobdemoversion.model.NGO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by zahid on 2/8/2017.
 */

public class NGONewDBModel implements Serializable {

    int ngoId;
    CommonModel commonModel;
    String ngoServices, ngoServicesFor, ngoServicesOther, ngoServiceType, dropTime, ngoFee;

    public int getNgoId() {
        return ngoId;
    }

    public void setNgoId(int ngoId) {
        this.ngoId = ngoId;
    }

    public CommonModel getCommonModel(){
        return commonModel;
    }

    public void setCommonModel(CommonModel commonModel){
        this.commonModel = commonModel;
    }

    public String getNgoServices() {
        return ngoServices;
    }

    public void setNgoServices(String ngoServices) {
        this.ngoServices = ngoServices;
    }

    public String getNgoServicesFor() {
        return ngoServicesFor;
    }

    public void setNgoServicesFor(String ngoServicesFor) {
        this.ngoServicesFor = ngoServicesFor;
    }

    public String getNgoServicesOther() {
        return ngoServicesOther;
    }

    public void setNgoServicesOther(String ngoServicesOther) {
        this.ngoServicesOther = ngoServicesOther;
    }

    public String getNgoServiceType() {
        return ngoServiceType;
    }

    public void setNgoServiceType(String ngoServiceType) {
        this.ngoServiceType = ngoServiceType;
    }

    public String getDropTime() {
        return dropTime;
    }

    public void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

    public String getNgoFee() {
        return ngoFee;
    }

    public void setNgoFee(String ngoFee) {
        this.ngoFee = ngoFee;
    }

    public NGONewDBModel(int ngoId, CommonModel commonModel, String ngoServices, String ngoServicesFor, String ngoServicesOther, String ngoServiceType, String dropTime, String ngoFee) {
        this.ngoId = ngoId;
        this.commonModel = commonModel;
        this.ngoServices = ngoServices;
        this.ngoServicesFor = ngoServicesFor;
        this.ngoServicesOther = ngoServicesOther;
        this.ngoServiceType = ngoServiceType;
        this.dropTime = dropTime;
        this.ngoFee = ngoFee;
    }

    public static NGONewDBModel parseNgoNewDBModel (JSONObject jo) throws JSONException {

        CommonModel _commonModel = CommonModel.parseCommonModel(jo);
        int _ngoId = jo.getInt("id");

        String _ngoSevices = jo.getString("ngo_services");
        String _ngoServicesFor = jo.getString("ngo_services_for");
        String _ngoServicesOther = jo.getString("ngo_services_other");
        String _ngoServiceType = jo.getString("service_type");
        String _dropTime = jo.getString("drop_time");
        String _ngoFee = jo.getString("ngo_fee");

        return new NGONewDBModel(_ngoId, _commonModel, _ngoSevices, _ngoServicesFor, _ngoServicesOther, _ngoServiceType, _dropTime, _ngoFee);
    }
}
