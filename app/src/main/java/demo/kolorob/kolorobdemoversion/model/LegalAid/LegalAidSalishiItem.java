package demo.kolorob.kolorobdemoversion.model.LegalAid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 1/7/2016.
 */
public class LegalAidSalishiItem implements Serializable {
    private String sId;
    private String identifierId;
    private int legalaidSubCategoryId;
    private String sservicename;
    private String sfree;
    private String scost;
    private String spersonauthority;
    private String sremark;

    public LegalAidSalishiItem( String sId,String identifierId, int legalaidSubCategoryId, String sservicename, String sfree, String scost, String spersonauthority, String sremark) {
        this.sId = sId;
        this.identifierId = identifierId;
        this.legalaidSubCategoryId = legalaidSubCategoryId;
        this.sservicename = sservicename;
        this.sfree = sfree;
        this.scost = scost;
        this.spersonauthority = spersonauthority;
        this.sremark = sremark;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public String getsId() {
        return sId;
    }

    public int getLegalaidSubCategoryId() {
        return legalaidSubCategoryId;
    }

    public String getSservicename() {
        return sservicename;
    }

    public String getSfree() {
        return sfree;
    }

    public String getScost() {
        return scost;
    }

    public String getSpersonauthority() {
        return spersonauthority;
    }

    public String getSremark() {
        return sremark;
    }
    public static LegalAidSalishiItem parseLegalAidSalishiItem(JSONObject jo) throws JSONException {
        String _sId = jo.getString("s_id");
        String _identifierId = jo.getString("identifier_id");
        int _legalaidSubCategoryId = jo.getInt("legal_aid_subcategory_id");
        String _sservicename = jo.getString("s_service_name");
        String _sfree = jo.getString("s_free");
        String _scost = jo.getString("s_cost");
        String _spersonauthority = jo.getString("s_person_authority");
        String _sremark = jo.getString("s_remark");


        return new LegalAidSalishiItem(_sId,_identifierId, _legalaidSubCategoryId,
                _sservicename, _sfree, _scost,_spersonauthority,_sremark);
    }
}
