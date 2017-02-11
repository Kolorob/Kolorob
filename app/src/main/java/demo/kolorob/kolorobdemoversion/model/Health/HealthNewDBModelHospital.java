package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class HealthNewDBModelHospital {
    int servicecenterid;
    String emergencyavailable;
    String emergencynumber;
    String ambulanceavailable;
    String ambulancenumber;
    String maternityavailable;
    String maternitynumber;
    String maternityprivacy;

    public HealthNewDBModelHospital(int servicecenterid, String emergencyavailable, String emergencynumber, String ambulanceavailable, String ambulancenumber, String maternityavailable, String maternitynumber, String maternityprivacy) {
        this.servicecenterid = servicecenterid;
        this.emergencyavailable = emergencyavailable;
        this.emergencynumber = emergencynumber;
        this.ambulanceavailable = ambulanceavailable;
        this.ambulancenumber = ambulancenumber;
        this.maternityavailable = maternityavailable;
        this.maternitynumber = maternitynumber;
        this.maternityprivacy = maternityprivacy;
    }

    public int getServicecenterid() {
        return servicecenterid;
    }

    public void setServicecenterid(int servicecenterid) {
        this.servicecenterid = servicecenterid;
    }

    public String getMaternityavailable() {
        return maternityavailable;
    }

    public void setMaternityavailable(String maternityavailable) {
        this.maternityavailable = maternityavailable;
    }

    public String getEmergencyavailable() {
        return emergencyavailable;
    }

    public void setEmergencyavailable(String emergencyavailable) {
        this.emergencyavailable = emergencyavailable;
    }

    public String getEmergencynumber() {
        return emergencynumber;
    }

    public void setEmergencynumber(String emergencynumber) {
        this.emergencynumber = emergencynumber;
    }

    public String getAmbulanceavailable() {
        return ambulanceavailable;
    }

    public void setAmbulanceavailable(String ambulanceavailable) {
        this.ambulanceavailable = ambulanceavailable;
    }

    public String getAmbulancenumber() {
        return ambulancenumber;
    }

    public void setAmbulancenumber(String ambulancenumber) {
        this.ambulancenumber = ambulancenumber;
    }

    public String getMaternitynumber() {
        return maternitynumber;
    }

    public void setMaternitynumber(String maternitynumber) {
        this.maternitynumber = maternitynumber;
    }

    public String getMaternityprivacy() {
        return maternityprivacy;
    }

    public void setMaternityprivacy(String maternityprivacy) {
        this.maternityprivacy = maternityprivacy;
    }

    public static HealthNewDBModelHospital parseHealthNewDBModelHospital(JSONObject jo, int sproviderkey) throws JSONException {
       int _servicecenterid=sproviderkey;
        String _eavailable = jo.getString("emergency_availabe");
        String _enumber = jo.getString("emergency_num");
        String _ambavailable=jo.getString("ambulance_available");
        String _ambnumber = jo.getString("ambulance_num");
        String _mtravailable = jo.getString("maternity_available");
        String _mtrnum=jo.getString("maternity_num");
        String _mtrprivacy = jo.getString("maternity_privacy");

        return new HealthNewDBModelHospital(_servicecenterid,_eavailable,_enumber,
                _ambavailable,_ambnumber,_mtravailable,_mtrnum,_mtrprivacy);
    }
}
