package demo.kolorob.kolorobdemoversion.model.NGO;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by zahid on 2/8/2017.
 */

public class NGONewDBModel extends CommonModel implements Serializable {

    String ngoServices, ngoServicesFor, ngoServicesOther, ngoServiceType, dropTime, ngoFee;



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

    public NGONewDBModel(int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings, String ngoServices, String ngoServicesFor, String ngoServicesOther, String ngoServiceType, String dropTime, String ngoFee) {
        super(id, nameEn, nameBn, houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo, openingTime, closingTime, offDay, lat, lon, categoryId, subcat, refNum, ratings);
        this.ngoServices = ngoServices;
        this.ngoServicesFor = ngoServicesFor;
        this.ngoServicesOther = ngoServicesOther;
        this.ngoServiceType = ngoServiceType;
        this.dropTime = dropTime;
        this.ngoFee = ngoFee;
    }

    public NGONewDBModel(CommonModel cm, String ngoServices, String ngoServicesFor, String ngoServicesOther, String ngoServiceType, String dropTime, String ngoFee) {
        super(cm.getId(), cm.getNameEn(), cm.getNameBn(),
                cm.getHouseNo(), cm.getBlock(), cm.getRoad(), cm.getArea(), cm.getAreaBn(), cm.getParentArea(), cm.getWard(), cm.getPoliceStation(),
                cm.getNodeContact(), cm.getNodeEmail(), cm.getOtherInfo(),
                cm.getOpeningTime(), cm.getClosingTime(), cm.getOffDay(),
                cm.getLat(), cm.getLon(),
                cm.getCategoryId(), cm.getSubcat(), cm.getRefNum(), cm.getRatings());
        this.ngoServices = ngoServices;
        this.ngoServicesFor = ngoServicesFor;
        this.ngoServicesOther = ngoServicesOther;
        this.ngoServiceType = ngoServiceType;
        this.dropTime = dropTime;
        this.ngoFee = ngoFee;
    }


    @Override
    public NGONewDBModel parse (JSONObject jo) throws JSONException {

        CommonModel _commonModel = null;
        _commonModel = _commonModel.parse(jo);

        String _ngoSevices = jo.getString("ngo_services");
        String _ngoServicesFor = jo.getString("ngo_services_for");
        String _ngoServicesOther = jo.getString("ngo_services_other");
        String _ngoServiceType = jo.getString("service_type");
        String _dropTime = jo.getString("drop_time");
        String _ngoFee = jo.getString("ngo_fee");

        return new NGONewDBModel(_commonModel, _ngoSevices, _ngoServicesFor, _ngoServicesOther, _ngoServiceType, _dropTime, _ngoFee);
    }
}
