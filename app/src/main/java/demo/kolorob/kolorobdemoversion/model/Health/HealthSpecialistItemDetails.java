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

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    public String getWeek_fee() {
        return week_fee;
    }

    public void setWeek_fee(String week_fee) {
        this.week_fee = week_fee;
    }

    public String getMonth_fee() {
        return month_fee;
    }

    public void setMonth_fee(String month_fee) {
        this.month_fee = month_fee;
    }

    public String getReport_fee() {
        return report_fee;
    }

    public void setReport_fee(String report_fee) {
        this.report_fee = report_fee;
    }

    public String getOther_fee() {
        return other_fee;
    }

    public void setOther_fee(String other_fee) {
        this.other_fee = other_fee;
    }

    public int getRefNum() {
        return refNum;
    }

    public void setRefNum(int refNum) {
        this.refNum = refNum;
    }

    public static HealthSpecialistItemDetails parseHealthSpecialistItem(JSONObject jo, int foreign_key) throws JSONException {
        String _nodeId=jo.getString("id");
        String _specialistId= jo.getString("num_doctors");
        String _specialisttype=jo.getString("specialist_type");

        String _specialistfees=jo.getString("first_visit_fee");
        String _specialistremarks=jo.getString("specialist_remarks");
        int _refNum= foreign_key;
        String week_fee=jo.getString("week_fee");
        String month_fee=jo.getString("month_fee");
        String report_fee=jo.getString("report_fee");
        String other_fee=jo.getString("other_fee");



        return new HealthSpecialistItemDetails(
                _nodeId,
                _specialistId,
                _specialisttype,
                _specialistfees,
                _specialistremarks,
                _refNum,
                week_fee,
                month_fee,
                report_fee,
                other_fee

        );




    }
}

