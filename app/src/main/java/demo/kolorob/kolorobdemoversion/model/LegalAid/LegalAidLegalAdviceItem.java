package demo.kolorob.kolorobdemoversion.model.LegalAid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 1/7/2016.
 */
public class LegalAidLegalAdviceItem implements Serializable {
    private int legaladviceId;
    private String identifierId;
    private int legalaidSubCategoryId;
    private String legalaidservicename;
    private String legalaidfree;
    private String legalaidcost;
    private String legalaidpersonauthority;
    private String legalaidremark;

    public LegalAidLegalAdviceItem(int legaladviceId, String identifierId, int legalaidSubCategoryId, String legalaidservicename, String legalaidfree, String legalaidcost, String legalaidpersonauthority, String legalaidremark) {
        this.legaladviceId = legaladviceId;
        this.identifierId = identifierId;
        this.legalaidSubCategoryId = legalaidSubCategoryId;
        this.legalaidservicename = legalaidservicename;
        this.legalaidfree = legalaidfree;
        this.legalaidcost = legalaidcost;
        this.legalaidpersonauthority = legalaidpersonauthority;
        this.legalaidremark = legalaidremark;
    }

    public int getLegaladviceId() {
        return legaladviceId;
    }

    public void setLegaladviceId(int legaladviceId) {
        this.legaladviceId = legaladviceId;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public void setIdentifierId(String identifierId) {
        this.identifierId = identifierId;
    }

    public int getLegalaidSubCategoryId() {
        return legalaidSubCategoryId;
    }

    public void setLegalaidSubCategoryId(int legalaidSubCategoryId) {
        this.legalaidSubCategoryId = legalaidSubCategoryId;
    }

    public String getLegalaidservicename() {
        return legalaidservicename;
    }

    public void setLegalaidservicename(String legalaidservicename) {
        this.legalaidservicename = legalaidservicename;
    }

    public String getLegalaidfree() {
        return legalaidfree;
    }

    public void setLegalaidfree(String legalaidfree) {
        this.legalaidfree = legalaidfree;
    }

    public String getLegalaidcost() {
        return legalaidcost;
    }

    public void setLegalaidcost(String legalaidcost) {
        this.legalaidcost = legalaidcost;
    }

    public String getLegalaidpersonauthority() {
        return legalaidpersonauthority;
    }

    public void setLegalaidpersonauthority(String legalaidpersonauthority) {
        this.legalaidpersonauthority = legalaidpersonauthority;
    }

    public void setLegalaidremark(String legalaidremark) {
        this.legalaidremark = legalaidremark;
    }

    public String getLegalaidremark() {
        return legalaidremark;
    }
    public static LegalAidLegalAdviceItem parseLegalAidLegalAdviceItem(JSONObject jo) throws JSONException {
        int _legaladviceId = jo.getInt("legal_advice_id");
        String _identifierId = jo.getString("identifier_id");
        int _legalaidSubCategoryId = jo.getInt("legal_aid_subcategory_id");
        String _legalaidservicename = jo.getString("legal_aid_service_name");
        String _legalaidfree = jo.getString("legal_aid_free");
        String _legalaidcost = jo.getString("legal_aid_cost");
        String _legalaidpersonauthority = jo.getString("legal_aid_person_authority");
        String _legalaidremark = jo.getString("legal_aid_remark");


        return new LegalAidLegalAdviceItem(_legaladviceId,_identifierId, _legalaidSubCategoryId,
                _legalaidservicename, _legalaidfree, _legalaidcost,_legalaidpersonauthority,_legalaidremark);
    }
}
