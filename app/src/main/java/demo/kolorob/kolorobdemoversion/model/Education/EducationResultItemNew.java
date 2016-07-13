package demo.kolorob.kolorobdemoversion.model.Education;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 7/12/2016.
 */


public class EducationResultItemNew implements Serializable {
    private int eduId;
    private int serviceproviderId;
    private String examname;
    private String studentno;
    private String passed;
    private String goldena;
    private String aplus;

    public int getServiceproviderId() {
        return serviceproviderId;
    }

    public void setServiceproviderId(int serviceproviderId) {
        this.serviceproviderId = serviceproviderId;
    }

    public EducationResultItemNew(int eduId, int serviceproviderId, String examname, String studentno,
                                  String passed, String goldena, String aplus) {
        this.eduId = eduId;
        this.serviceproviderId = serviceproviderId;
        this.examname = examname;
        this.studentno = studentno;
        this.passed = passed;
        this.goldena = goldena;
        this.aplus = aplus;
    }

    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
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

    public static EducationResultItemNew parseEducationResultItemNew(JSONObject jo) throws JSONException {
        int _eduId = jo.getInt("id");
        int _sproviderid = jo.getInt("_service_provider");
        String _examname = jo.getString("exam_names");
        String _studentno = jo.getString("student_no");
        String _passed = jo.getString("passed");
        String _goldena = jo.getString("golden_a");
        String _aplus = jo.getString("a_plus");

        return new EducationResultItemNew(_eduId,_sproviderid,
                _examname,_studentno,_passed,_goldena,_aplus);
    }
}
