package demo.kolorob.kolorobdemoversion.model.Health;



/**
 * Created by mity on 2/4/16.
 */

import org.json.JSONException;
import org.json.JSONObject;
public class HealthSpecialistItem {
    private String nodeId;
    private String specialistId;
    private String specialisttype;
    private String specialistfees;

    private String specialistremarks;
    private int refNum;

    public HealthSpecialistItem(String nodeId, String specialistId, String specialisttype, String specialistfees, String specialistremarks, int refNum) {
        this.nodeId = nodeId;
        this.specialistId = specialistId;
        this.specialisttype = specialisttype;
        this.specialistfees = specialistfees;
        this.specialistremarks = specialistremarks;
        this.refNum = refNum;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialisttype() {
        return specialisttype;
    }

    public void setSpecialisttype(String specialisttype) {
        this.specialisttype = specialisttype;
    }

    public String getSpecialistfees() {
        return specialistfees;
    }

    public void setSpecialistfees(String specialistfees) {
        this.specialistfees = specialistfees;
    }

    public String getSpecialistremarks() {
        return specialistremarks;

    }

    public void setSpecialistremarks(String specialistremarks) {
        this.specialistremarks = specialistremarks;
    }

    public int getRefNum() {
        return refNum;
    }

    public void setRefNum(int refNum) {
        this.refNum = refNum;
    }

    public static HealthSpecialistItem parseHealthSpecialistItem(JSONObject jo) throws JSONException {
        String _nodeId=jo.getString("node_id");
        String _specialistId= jo.getString("specialist_id");
        String _specialisttype=jo.getString("specialist_type");

        String _specialistfees=jo.getString("specialist_fees");
        String _specialistremarks=jo.getString("specialist_remarks");
        int _refNum= jo.getInt("ref_num");


        return new HealthSpecialistItem(
                _nodeId,
                _specialistId,
                _specialisttype,
                _specialistfees,
                _specialistremarks,
                _refNum

        );




    }
}
