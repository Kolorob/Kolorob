package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.model.SubModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class HealthNewDBModelPharmacy extends SubModel<HealthNewDBModelPharmacy> {

    private int id, healthId;
    private String docAvailability, speciality, vaccineAvailability;

    public HealthNewDBModelPharmacy() {
    }

    public HealthNewDBModelPharmacy(int id, int healthId, String docAvailability, String speciality, String vaccineAvailability) {
        this.id = id;
        this.healthId = healthId;
        this.docAvailability = docAvailability;
        this.speciality = speciality;
        this.vaccineAvailability = vaccineAvailability;
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

    public String getDocAvailability() {
        return docAvailability;
    }

    public void setDocAvailability(String docAvailability) {
        this.docAvailability = docAvailability;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getVaccineAvailability() {
        return vaccineAvailability;
    }

    public void setVaccineAvailability(String vaccineAvailability) {
        this.vaccineAvailability = vaccineAvailability;
    }


    public HealthNewDBModelPharmacy parse(JSONObject jo,int _healthId) throws JSONException {

        int _id = jo.getInt("id");
        String doctorAvailable = jo.getString("doctor_available");
        String _speciality = jo.getString("speciality");

        String vaccineAvailable = jo.getString("vaccine_available");

        return new HealthNewDBModelPharmacy(_id, _healthId,doctorAvailable,
                _speciality,vaccineAvailable);
    }
}
