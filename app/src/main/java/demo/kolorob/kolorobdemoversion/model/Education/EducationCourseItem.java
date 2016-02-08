package demo.kolorob.kolorobdemoversion.model.Education;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Yeakub Hassan Rafi on 30-Dec-15.
 */
public class EducationCourseItem implements Serializable {
    private String courseId;
    private String identifierId;
    private int eduSubCategoryId;
    private  String educoursename;
    private String educourseduration;
    private String educourseadmissiontime;
    private String educoursecost;
    private String educoursetype;

    public EducationCourseItem(String courseId, String identifierId, int eduSubCategoryId, String educoursename, String educourseduration, String educourseadmissiontime, String educoursecost, String educoursetype) {
        this.courseId = courseId;
        this.identifierId = identifierId;
        this.eduSubCategoryId = eduSubCategoryId;
        this.educoursename = educoursename;
        this.educourseduration = educourseduration;
        this.educourseadmissiontime = educourseadmissiontime;
        this.educoursecost = educoursecost;
        this.educoursetype = educoursetype;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public void setIdentifierId(String identifierId) {
        this.identifierId = identifierId;
    }

    public int getEduSubCategoryId() {
        return eduSubCategoryId;
    }

    public void setEduSubCategoryId(int eduSubCategoryId) {
        this.eduSubCategoryId = eduSubCategoryId;
    }

    public String getEducoursename() {
        return educoursename;
    }

    public void setEducoursename(String educoursename) {
        this.educoursename = educoursename;
    }

    public String getEducourseduration() {
        return educourseduration;
    }

    public void setEducourseduration(String educourseduration) {
        this.educourseduration = educourseduration;
    }

    public String getEducourseadmissiontime() {
        return educourseadmissiontime;
    }

    public void setEducourseadmissiontime(String educourseadmissiontime) {
        this.educourseadmissiontime = educourseadmissiontime;
    }

    public String getEducoursecost() {
        return educoursecost;
    }

    public void setEducoursecost(String educoursecost) {
        this.educoursecost = educoursecost;
    }

    public String getEducoursetype() {
        return educoursetype;
    }

    public void setEducoursetype(String educoursetype) {
        this.educoursetype = educoursetype;
    }

    public static EducationCourseItem parseEducationCourseItem(JSONObject jo) throws JSONException {
        String _courseId = jo.getString("course_id");
        String _identifierId = jo.getString("identifier_id");
        int _eduSubCategoryId = jo.getInt("edu_subcategory_id");
        String _educoursename = jo.getString("edu__course_name");
        String _educourseduration = jo.getString("edu_course_duration");
        String _educourseadmissiontime = jo.getString("edu_course_admission_time");
        String _educoursecost = jo.getString("edu_course_cost");
        String _educoursetype = jo.getString("edu_course_type");


        return new EducationCourseItem(_courseId,
                _identifierId, _eduSubCategoryId, _educoursename,_educourseduration,_educourseadmissiontime,
                _educoursecost,_educoursetype );
    }
}
