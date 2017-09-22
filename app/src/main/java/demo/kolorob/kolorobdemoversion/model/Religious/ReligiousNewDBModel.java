package demo.kolorob.kolorobdemoversion.model.Religious;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by zahid on 2/8/2017.
 */

public class ReligiousNewDBModel implements Serializable {
    int shelterId;
    CommonModel commonModel;
    String rsReligion, rsServicesFor, rsServicesForReligion, otherReligion, rsTime, rsFee;

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public CommonModel getCommonModel() {
        return commonModel;
    }

    public void setCommonModel(CommonModel commonModel) {
        this.commonModel = commonModel;
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

    public ReligiousNewDBModel(int shelterId, CommonModel commonModel, String rsReligion, String rsServicesFor, String rsServicesForReligion, String otherReligion, String rsTime, String rsFee) {
        this.shelterId = shelterId;
        this.commonModel = commonModel;
        this.rsReligion = rsReligion;
        this.rsServicesFor = rsServicesFor;
        this.rsServicesForReligion = rsServicesForReligion;
        this.otherReligion = otherReligion;
        this.rsTime = rsTime;
        this.rsFee = rsFee;
    }

    public static ReligiousNewDBModel parseReligiousNewDBModel (JSONObject jo) throws JSONException {

        CommonModel _commonModel = CommonModel.parseCommonModel(jo);
        int _shelterId = jo.getInt("id");

        String _rsReligion = jo.getString("rs_religion");
        String _rsServicesFor = jo.getString("rs_services_for");
        String _rsServicesForReligion = jo.getString("rs_access");
        String _otherReligion = jo.getString("other_religion");
        String _rsTime = jo.getString("rs_time");
        String _rsFee = jo.getString("rs_fee");

        return new ReligiousNewDBModel(_shelterId, _commonModel, _rsReligion, _rsServicesFor, _rsServicesForReligion, _otherReligion, _rsTime, _rsFee);
    }
}
