package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.model.SubModel;

/**
 * Created by shamima.brishti on 2/9/18.
 */

public class HealthModelChamber extends SubModel<HealthModelChamber> {

    private int id, healthId;
    private String speciality;

    public HealthModelChamber(){

    }

    public HealthModelChamber(int id, int healthId, String speciality) {
        this.id = id;
        this.healthId = healthId;
        this.speciality = speciality;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public HealthModelChamber parse(JSONObject jo, int healthId) throws JSONException {

        int _id = jo.getInt("id");
        String _speciality = jo.getString("speciality");

        return new HealthModelChamber(_id, healthId, _speciality);
    }
}
