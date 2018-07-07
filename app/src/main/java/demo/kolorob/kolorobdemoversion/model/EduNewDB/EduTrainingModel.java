package demo.kolorob.kolorobdemoversion.model.EduNewDB;

import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.model.SubModel;

/**
 * Created by israt.jahan on 1/27/2017.
 */

public class EduTrainingModel extends SubModel <EduTrainingModel> {

    int id, educationId;
    String courseduration, cost, trainingname, coursename;

    public EduTrainingModel(int id, int educationId, String courseduration, String cost, String trainingname, String coursename) {
        this.id = id;
        this.educationId = educationId;
        this.courseduration = courseduration;
        this.cost = cost;
        this.trainingname = trainingname;
        this.coursename = coursename;
    }

    public EduTrainingModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
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


    public EduTrainingModel parse(JSONObject jo, int educationId) throws JSONException {

        int _id = jo.getInt("id");

        int _educationId = educationId;
        String _courseduration = jo.getString("course_duration");
        String _cost = jo.getString("cost");
        String _trainingname = jo.getString("training_name");
        String _coursename = jo.getString("course_name");
        return new EduTrainingModel(_id, _educationId,
                _courseduration,_cost,_trainingname,_coursename);
    }
}
