package demo.kolorob.kolorobdemoversion.model.Education;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/7/2016.
 */
public class EducationTrainingDetailsItem {
    private int eduId;
    private int serviceproviderId;
    private String courseduration;
    private String admissionmonth;
    private String cost;
    private String trainingnametype;
    private String trainingnamesubtype;

    public EducationTrainingDetailsItem(int eduId, int serviceproviderId, String courseduration, String admissionmonth,
                                        String cost, String trainingnametype, String trainingnamesubtype) {
        this.eduId = eduId;
        this.serviceproviderId = serviceproviderId;
        this.courseduration = courseduration;
        this.admissionmonth = admissionmonth;
        this.cost = cost;
        this.trainingnametype = trainingnametype;
        this.trainingnamesubtype = trainingnamesubtype;
    }

    public int getServiceproviderId() {
        return serviceproviderId;
    }

    public void setServiceproviderId(int serviceproviderId) {
        this.serviceproviderId = serviceproviderId;
    }

    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
    }

    public String getCourseduration() {
        return courseduration;
    }

    public void setCourseduration(String courseduration) {
        this.courseduration = courseduration;
    }

    public String getAdmissionmonth() {
        return admissionmonth;
    }

    public void setAdmissionmonth(String admissionmonth) {
        this.admissionmonth = admissionmonth;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTrainingnametype() {
        return trainingnametype;
    }

    public void setTrainingnametype(String trainingnametype) {
        this.trainingnametype = trainingnametype;
    }

    public String getTrainingnamesubtype() {
        return trainingnamesubtype;
    }

    public void setTrainingnamesubtype(String trainingnamesubtype) {
        this.trainingnamesubtype = trainingnamesubtype;
    }

    public static EducationTrainingDetailsItem parseEducationTrainingDetailsItem(JSONObject jo) throws JSONException {
        int _eduId = jo.getInt("id");
        int _sproviderid = jo.getInt("_service_provider");
        String _courseduration = jo.getString("course_duration");
        String _admissionmonth = jo.getString("admission_month");
        String _cost = jo.getString("cost");

        String _trainingnametype = jo.getJSONObject("training_name").getString("type");
        String _trainingnamesubtype = jo.getJSONObject("training_name").getString("sub_type");
        return new EducationTrainingDetailsItem(_eduId,_sproviderid,
                _courseduration,_admissionmonth,_cost,_trainingnametype,_trainingnamesubtype);
    }
}
