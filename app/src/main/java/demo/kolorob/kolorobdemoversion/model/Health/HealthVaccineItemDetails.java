package demo.kolorob.kolorobdemoversion.model.Health;

/**
 * Created by Mazharul.Islam1 on 7/12/2016.
 */

import org.json.JSONException;
import org.json.JSONObject;
public class HealthVaccineItemDetails {
    private String nodeId;
    private String vaccinename;
    private String vaccinefee;
    private String vaccineremarks;
    private int foreign_key;

    public HealthVaccineItemDetails(String nodeId, String vaccinename, String vaccinefee, String vaccineremarks, int foreign_key) {
        this.nodeId = nodeId;
        this.vaccinename = vaccinename;
        this.vaccinefee = vaccinefee;
        this.vaccineremarks = vaccineremarks;
        this.foreign_key = foreign_key;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getVaccinename() {
        return vaccinename;
    }

    public void setVaccinename(String vaccinename) {
        this.vaccinename = vaccinename;
    }

    public String getVaccinefee() {
        return vaccinefee;
    }

    public void setVaccinefee(String vaccinefee) {
        this.vaccinefee = vaccinefee;
    }

    public String getVaccineremarks() {
        return vaccineremarks;
    }

    public void setVaccineremarks(String vaccineremarks) {
        this.vaccineremarks = vaccineremarks;
    }

    public int getForeign_key() {
        return foreign_key;
    }

    public void setForeign_key(int foreign_key) {
        this.foreign_key = foreign_key;
    }

    public static HealthVaccineItemDetails parseHealthVaccinesItem(JSONObject jo, int foreign_keys) throws JSONException {
        String _nodeId=jo.getString("id");
        String _vaccinename= jo.getString("vaccine_name");
        String _vaccinefee=jo.getString("vaccine_fee");
        String _vaccineremarks=jo.getString("vaccine_remark");
        int foreign_key= foreign_keys;


        return new HealthVaccineItemDetails(
                _nodeId,
                _vaccinename,
                _vaccinefee,
                _vaccineremarks,
                foreign_key

        );




    }
}

