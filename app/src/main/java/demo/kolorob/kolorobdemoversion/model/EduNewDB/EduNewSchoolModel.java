package demo.kolorob.kolorobdemoversion.model.EduNewDB;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HP on 2/17/2017.
 */

public class EduNewSchoolModel {
    int id;
    int spid;
    String stipend;
    String primary_fees;
    String secondary_fees;
    String collage_fees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStipend() {
        return stipend;
    }

    public void setStipend(String stipend) {
        this.stipend = stipend;
    }

    public String getPrimary_fees() {
        return primary_fees;
    }

    public void setPrimary_fees(String primary_fees) {
        this.primary_fees = primary_fees;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public String getSecondary_fees() {
        return secondary_fees;
    }

    public void setSecondary_fees(String secondary_fees) {
        this.secondary_fees = secondary_fees;
    }

    public String getCollage_fees() {
        return collage_fees;
    }

    public void setCollage_fees(String collage_fees) {
        this.collage_fees = collage_fees;
    }

    public EduNewSchoolModel(int id, int spid,String stipend, String primary_fees, String secondary_fees, String collage_fees) {
        this.id = id;
        this.id=spid;
        this.stipend = stipend;
        this.primary_fees = primary_fees;
        this.secondary_fees = secondary_fees;
        this.collage_fees = collage_fees;
    }

    public static EduNewSchoolModel parseEduNewSchoolModel(JSONObject jo,int sproviderkey) throws JSONException {
        int _eduId = jo.getInt("id");
        int _sproviderId=sproviderkey;
        String _stipend = jo.getString("stipend");
        String _primary = jo.getString("primary_fees");

        String _secondary = jo.getString("secondary_fees");
        String _collage = jo.getString("collage_fees");
        return new EduNewSchoolModel(_eduId,_sproviderId,_stipend,
                _primary,_secondary,_collage);
    }
}
