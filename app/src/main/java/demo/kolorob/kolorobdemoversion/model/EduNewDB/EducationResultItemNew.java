package demo.kolorob.kolorobdemoversion.model.EduNewDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.SubModel;

/**
 * Created by israt.jahan on 7/12/2016.
 */


public class EducationResultItemNew extends SubModel <EducationResultItemNew> implements Serializable{

    private int id, educationId;
    private String examname, studentno, passed, goldena, aplus;

    public EducationResultItemNew(int id, int educationId, String examname, String studentno, String passed, String goldena, String aplus) {
        this.id = id;
        this.educationId = educationId;
        this.examname = examname;
        this.studentno = studentno;
        this.passed = passed;
        this.goldena = goldena;
        this.aplus = aplus;
    }

    public EducationResultItemNew() {

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

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getPassed() {
        return passed;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }

    public String getGoldena() {
        return goldena;
    }

    public void setGoldena(String goldena) {
        this.goldena = goldena;
    }

    public String getAplus() {
        return aplus;
    }

    public void setAplus(String aplus) {
        this.aplus = aplus;
    }

    public EducationResultItemNew parse(JSONObject jo, int educationId) throws JSONException {

        int _id = jo.getInt("id");
        //int _educationId = jo.getInt("_service_provider");
        int _educationId = educationId;
        String _examname = jo.getString("exam_names");
        String _studentno = jo.getString("student_no");
        String _passed = jo.getString("passed");
        String _goldena = jo.getString("golden_a");
        String _aplus = jo.getString("a_plus");

        return new EducationResultItemNew(_id, _educationId,
                _examname,_studentno,_passed,_goldena,_aplus);
    }

}
