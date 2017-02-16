package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class HealthNewDBModelPharmacy {
    int servicecenterid;
    String docavailability;
    String speciality;
    String vaccineavailability;

    public HealthNewDBModelPharmacy() {
    }

    public HealthNewDBModelPharmacy(int servicecenterid, String docavailability, String speciality, String vaccineavailability) {
        this.servicecenterid = servicecenterid;
        this.docavailability = docavailability;
        this.speciality = speciality;
        this.vaccineavailability = vaccineavailability;
    }

    public int getServicecenterid() {
        return servicecenterid;
    }

    public void setServicecenterid(int servicecenterid) {
        this.servicecenterid = servicecenterid;
    }

    public String getDocavailability() {
        return docavailability;
    }

    public void setDocavailability(String docavailability) {
        this.docavailability = docavailability;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getVaccineavailability() {
        return vaccineavailability;
    }

    public void setVaccineavailability(String vaccineavailability) {
        this.vaccineavailability = vaccineavailability;
    }
    public static HealthNewDBModelPharmacy parseHealthNewDBModelPharmacy(JSONObject jo,int sproviderkey) throws JSONException {
       int _servicecenterid=sproviderkey;
        String _davailable = jo.getString("doctor_available");
        String _speciality = jo.getString("speciality");

        String _vaccineavailable = jo.getString("vaccine_available");

        return new HealthNewDBModelPharmacy(_servicecenterid,_davailable,
                _speciality,_vaccineavailable);
    }
}
