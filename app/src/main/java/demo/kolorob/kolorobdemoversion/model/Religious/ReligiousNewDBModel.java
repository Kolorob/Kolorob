package demo.kolorob.kolorobdemoversion.model.Religious;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by zahid on 2/8/2017.
 */

public class ReligiousNewDBModel extends CommonModel implements Serializable {

    private String rsReligion, rsServicesFor, rsServicesForReligion, otherReligion, rsTime, rsFee;

    public ReligiousNewDBModel() {

    }


    public String getRsReligion() {
        return rsReligion;
    }

    public void setRsReligion(String rsReligion) {
        this.rsReligion = rsReligion;
    }

    public String getRsServicesFor() {
        return rsServicesFor;
    }

    public void setRsServicesFor(String rsServicesFor) {
        this.rsServicesFor = rsServicesFor;
    }

    public String getRsServicesForReligion() {
        return rsServicesForReligion;
    }

    public void setRsServicesForReligion(String rsServicesForReligion) {
        this.rsServicesForReligion = rsServicesForReligion;
    }

    public String getOtherReligion() {
        return otherReligion;
    }

    public void setOtherReligion(String otherReligion) {
        this.otherReligion = otherReligion;
    }

    public String getRsTime() {
        return rsTime;
    }

    public void setRsTime(String rsTime) {
        this.rsTime = rsTime;
    }

    public String getRsFee() {
        return rsFee;
    }

    public void setRsFee(String rsFee) {
        this.rsFee = rsFee;
    }

    public ReligiousNewDBModel(int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings, String comment, String rsReligion, String rsServicesFor, String rsServicesForReligion, String otherReligion, String rsTime, String rsFee) {
        super(id, nameEn, nameBn, houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo, openingTime, closingTime, offDay, lat, lon, categoryId, subcat, refNum, ratings, comment);
        this.rsReligion = rsReligion;
        this.rsServicesFor = rsServicesFor;
        this.rsServicesForReligion = rsServicesForReligion;
        this.otherReligion = otherReligion;
        this.rsTime = rsTime;
        this.rsFee = rsFee;
    }

    public ReligiousNewDBModel(CommonModel cm, String rsReligion, String rsServicesFor, String rsServicesForReligion, String otherReligion, String rsTime, String rsFee){
        super(cm.getId(), cm.getNameEn(), cm.getNameBn(),
                cm.getHouseNo(), cm.getBlock(), cm.getRoad(), cm.getArea(), cm.getAreaBn(), cm.getParentArea(), cm.getWard(), cm.getPoliceStation(),
                cm.getNodeContact(), cm.getNodeEmail(), cm.getOtherInfo(),
                cm.getOpeningTime(), cm.getClosingTime(), cm.getOffDay(),
                cm.getLat(), cm.getLon(),
                cm.getCategoryId(), cm.getSubcat(), cm.getRefNum(), cm.getRatings(), cm.getComment());
        this.rsReligion = rsReligion;
        this.rsServicesFor = rsServicesFor;
        this.rsServicesForReligion = rsServicesForReligion;
        this.otherReligion = otherReligion;
        this.rsTime = rsTime;
        this.rsFee = rsFee;
    }

    @Override
    public ReligiousNewDBModel parse (JSONObject jo) throws JSONException {

        CommonModel _commonModel = new CommonModel().parse(jo);
        String _rsReligion = jo.getString("rs_religion");
        String _rsServicesFor = jo.getString("rs_services_for");
        String _rsServicesForReligion = jo.getString("rs_access");
        String _otherReligion = jo.getString("other_religion");
        String _rsTime = jo.getString("rs_time");
        String _rsFee = jo.getString("rs_fee");

        return new ReligiousNewDBModel(_commonModel, _rsReligion, _rsServicesFor, _rsServicesForReligion, _otherReligion, _rsTime, _rsFee);
    }
}
