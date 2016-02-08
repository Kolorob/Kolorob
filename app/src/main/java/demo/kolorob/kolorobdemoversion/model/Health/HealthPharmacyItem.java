package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Mazhaul Islam on 4/2/2016.
 */
public class HealthPharmacyItem implements Serializable {

    private String nodeId;
    private int docId;
    private String pharmacyFee;
    private String pharmacyDoctorName;
    private String pharmacyTime;
    private String pharmacyNoDegree;
    private String pharmacyLMAF;
    private String pharmacyMBBS;
    private String pharmacySpecialist;
    private String remarks;
    private String pharmacyDocRemarks;
    private String refNumber;


    public HealthPharmacyItem(
            String nodeId,
            int docId,
            String pharmacyFee,
            String pharmacyDoctorName,
            String pharmacyTime,
            String pharmacyNoDegree,
            String pharmacyLMAF,
            String pharmacyMBBS,
            String pharmacySpecialist,
            String remarks,
            String pharmacyDocRemarks,
            String refNumber
    ) {

        this.nodeId = nodeId;
        this.docId = docId;
        this.pharmacyFee = pharmacyFee;
        this.pharmacyDoctorName = pharmacyDoctorName;
        this.pharmacyTime = pharmacyTime;
        this.pharmacyNoDegree = pharmacyNoDegree;
        this.pharmacyLMAF = pharmacyLMAF;
        this.pharmacyMBBS=pharmacyMBBS;
        this.pharmacySpecialist = pharmacySpecialist;
        this.remarks = remarks;
        this.pharmacyDocRemarks=pharmacyDocRemarks;
        this.refNumber = refNumber;


    }

    public String getPharmacyMBBS() {
        return pharmacyMBBS;
    }

    public String getpharmacyDocRemarks() {
        return pharmacyDocRemarks;
    }

    public String getNodeId() {
        return nodeId;
    }

    public int getDocId() {
        return docId;
    }

    public String getPharmacyFee() {
        return pharmacyFee;
    }

    public String getPharmacyDoctorName() {
        return pharmacyDoctorName;
    }

    public String getPharmacyTime() {
        return pharmacyTime;
    }

    public String getPharmacyNoDegree() {
        return pharmacyNoDegree;
    }

    public String getPharmacyLMAF() {
        return pharmacyLMAF;
    }

    public String getPharmacySpecialist() {
        return pharmacySpecialist;
    }

    public String getRemarks() {
        return remarks;
    }


    public String getRefNumber() {
        return refNumber;
    }

    public static HealthPharmacyItem parseHealthPharmacyItem(JSONObject jo) throws JSONException {
        String _nodeId = jo.getString("node_id");
        int _docId = jo.getInt("doc_id");
        String _pharmacyFee = jo.getString("pharmacy_fee");
        String _pharmacyDoctorName = jo.getString("pharmacy_doctor_name");
        String _pharmacyTime = jo.getString("pharmacy_time");
        String _pharmacyNoDegree = jo.getString("pharmacy_no_degree");
        String _pharmacyLMAF = jo.getString("pharmacy_lmaf");
        String _pharmacyMBBS = jo.getString("pharmacy_mbbs");
        String _pharmacySpecialist = jo.getString("pharmacy_specialist");
        String _remarks = jo.getString("pharmacy_remarks");
        String _pharmacyDocRemarks = jo.getString("pharmacy_doc_remarks");
        String _refNumber = jo.getString("ref_num");


        return new HealthPharmacyItem(
                _nodeId,
                _docId,
                _pharmacyFee,
                _pharmacyDoctorName,
                _pharmacyTime,
                _pharmacyNoDegree,
                _pharmacyLMAF,
                _pharmacyMBBS,
                _pharmacySpecialist,
                _remarks,
                _pharmacyDocRemarks,
                _refNumber
        );


    }

}