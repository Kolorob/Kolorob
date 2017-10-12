package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class HealthNewDBModelPharmacy {
    int id, healthId;
    String docavailability, speciality, vaccineavailability;

    public HealthNewDBModelPharmacy() {
    }

    public HealthNewDBModelPharmacy(int id, int healthId, String docavailability, String speciality, String vaccineavailability) {
        this.id = id;
        this.healthId = healthId;
        this.docavailability = docavailability;
        this.speciality = speciality;
        this.vaccineavailability = vaccineavailability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealthId() {
        return healthId;
    }

    public void setHealthId(int healthId) {
        this.healthId = healthId;
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

        int _id = jo.getInt("id");
        int _healthId = sproviderkey;
        String _davailable = jo.getString("doctor_available");
        String _speciality = jo.getString("speciality");

        String _vaccineavailable = jo.getString("vaccine_available");

        return new HealthNewDBModelPharmacy(_id, _healthId,_davailable,
                _speciality,_vaccineavailable);
    }
}
