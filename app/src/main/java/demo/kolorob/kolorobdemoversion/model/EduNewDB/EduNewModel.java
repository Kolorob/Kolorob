package demo.kolorob.kolorobdemoversion.model.EduNewDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/26/2017.
 */

public class EduNewModel implements Serializable {

    int eduId;
    CommonModel commonId;

    String educationType, shift, studentNo, teachersNo, averageStudentPerClass, facility;


    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
    }

    public CommonModel getCommonId() {
        return commonId;
    }

    public void setCommonId(CommonModel commonId) {
        this.commonId = commonId;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getTeachersNo() {
        return teachersNo;
    }

    public void setTeachersNo(String teachersNo) {
        this.teachersNo = teachersNo;
    }

    public String getAverageStudentPerClass() {
        return averageStudentPerClass;
    }

    public void setAverageStudentPerClass(String averageStudentPerClass) {
        this.averageStudentPerClass = averageStudentPerClass;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public EduNewModel(int eduId, CommonModel commonId, String educationType, String shift, String studentNo, String teachersNo, String averageStudentPerClass, String facility) {
        this.eduId = eduId;
        this.commonId = commonId;
        this.educationType = educationType;
        this.shift = shift;
        this.studentNo = studentNo;
        this.teachersNo = teachersNo;
        this.averageStudentPerClass = averageStudentPerClass;
        this.facility = facility;
    }

    public static EduNewModel parseEduNewModel (JSONObject jo, CommonModel commonId) throws JSONException {

            int _eduId = jo.getInt("id");
            CommonModel _commonId = commonId;

            String _educationType = jo.getString("education_type");
            String _shift = jo.getString("shift");
            String _studentNo = jo.getString("no_of_students");
            String _teachersNo = jo.getString("no_of_teachers");
            String _avgStudentPerClass = jo.getString("class_size");
            String _facility = jo.getString("facility");

            return new EduNewModel(_eduId, _commonId, _educationType, _shift, _studentNo, _teachersNo, _avgStudentPerClass, _facility);
        }
    }
