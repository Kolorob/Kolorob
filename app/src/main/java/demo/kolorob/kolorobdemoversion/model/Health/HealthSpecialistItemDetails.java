package demo.kolorob.kolorobdemoversion.model.Health;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Mazharul.Islam1 on 7/12/2016.
 */


public class HealthSpecialistItemDetails {
    private String nodeId;
    private String specialistId;
    private String specialisttype;
    private String specialistfees;
    private String specialistremarks;
    private int refNum;
    private String week_fee;
    private String month_fee;
    private String report_fee;
    private String other_fee;


    public HealthSpecialistItemDetails(String nodeId,
                                       String specialistId,
                                       String specialisttype,
                                       String specialistfees,
                                       String specialistremarks,
                                       int refNum,
                                       String week_fee,
                                       String month_fee,
                                       String report_fee,
                                       String other_fee) {
        this.nodeId = nodeId;
        this.specialistId = specialistId;
        this.specialisttype = specialisttype;
        this.specialistfees = specialistfees;
        this.specialistremarks = specialistremarks;
        this.refNum = refNum;
        this.week_fee=week_fee;
        this.month_fee=month_fee;
        this.report_fee=report_fee;
        this.other_fee=other_fee;
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

