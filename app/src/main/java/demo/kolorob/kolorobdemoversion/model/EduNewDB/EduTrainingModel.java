package demo.kolorob.kolorobdemoversion.model.EduNewDB;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 1/27/2017.
 */

public class EduTrainingModel {
    int eduid;
    int serviceproviderid;
    String courseduration;
    String cost;
    String trainingname;
    String coursename;

    public EduTrainingModel(int eduid, int serviceproviderid, String courseduration, String cost, String trainingname, String coursename) {
        this.eduid = eduid;
        this.serviceproviderid = serviceproviderid;
        this.courseduration = courseduration;
        this.cost = cost;
        this.trainingname = trainingname;
        this.coursename = coursename;
    }

    public int getEduid() {
        return eduid;
    }

    public void setEduid(int eduid) {
        this.eduid = eduid;
    }

    public int getServiceproviderid() {
        return serviceproviderid;
    }

    public void setServiceproviderid(int serviceproviderid) {
        this.serviceproviderid = serviceproviderid;
    }

    public String getCourseduration() {
        return courseduration;
    }

    public void setCourseduration(String courseduration) {
        this.courseduration = courseduration;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTrainingname() {
        return trainingname;
    }

    public void setTrainingname(String trainingname) {
        this.trainingname = trainingname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
    public static EduTrainingModel parseEduTrainingModel(JSONObject jo) throws JSONException {
        int _eduId = jo.getInt("id");
        int _sproviderid = jo.getInt("_service_provider");
        String _courseduration = jo.getString("course_duration");
        String _cost = jo.getString("cost");

        String _trainingname = jo.getString("training_name");
        String _coursename = jo.getString("course_name");
        return new EduTrainingModel(_eduId,_sproviderid,
                _courseduration,_cost,_trainingname,_coursename);
    }
}
