package demo.kolorob.kolorobdemoversion.model.Health;



/**
 * Created by israt.jahan on 2/3/2016.
 */

import org.json.JSONException;
import org.json.JSONObject;
public class HealthVaccinesItem {
    private String nodeId;
    private String vaccinename;
    private String vaccinefee;
    private String vaccineremarks;
    private int refNum;

    public HealthVaccinesItem(String nodeId, String vaccinename, String vaccinefee, String vaccineremarks, int refNum) {
        this.nodeId = nodeId;
        this.vaccinename = vaccinename;
        this.vaccinefee = vaccinefee;
        this.vaccineremarks = vaccineremarks;
        this.refNum = refNum;
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

    public int getRefNum() {
        return refNum;
    }

    public void setRefNum(int refNum) {
        this.refNum = refNum;
    }
    public static HealthVaccinesItem parseHealthVaccinesItem(JSONObject jo) throws JSONException {
        String _nodeId=jo.getString("node_id");
        String _vaccinename= jo.getString("vaccine_name");
        String _vaccinefee=jo.getString("vaccine_fee");
        String _vaccineremarks=jo.getString("vaccine_remarks");
        int _refNum= jo.getInt("ref_num");


        return new HealthVaccinesItem(
                _nodeId,
                _vaccinename,
                _vaccinefee,
                _vaccineremarks,
                _refNum

        );




    }
}
