package demo.kolorob.kolorobdemoversion.model.Education;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 7/11/2016.
 */
public class EducationTuitionDetailsItem {
    private int eduId;
    private int serviceproviderId;
    private String tuitionfree;
    private String tuitionlevel;
    private String tuitionstipendfacility;
    private String tuitionstipendtype;
    private String tuitiondetails;
    private String tuitionminfee;
    private String tuitionmaxfee;
    private String tuitionmincoaching;
    private String tuitionmaxcoaching;
    private String tuitionadditional;

    public EducationTuitionDetailsItem(int eduId, int serviceproviderId, String tuitionfree, String tuitionlevel,
                                       String tuitionstipendfacility, String tuitionstipendtype, String tuitiondetails,
                                       String tuitionminfee, String tuitionmaxfee, String tuitionmincoaching,
                                       String tuitionmaxcoaching, String tuitionadditional) {
        this.eduId = eduId;
        this.serviceproviderId = serviceproviderId;
        this.tuitionfree = tuitionfree;
        this.tuitionlevel = tuitionlevel;
        this.tuitionstipendfacility = tuitionstipendfacility;
        this.tuitionstipendtype = tuitionstipendtype;
        this.tuitiondetails = tuitiondetails;
        this.tuitionminfee = tuitionminfee;
        this.tuitionmaxfee = tuitionmaxfee;
        this.tuitionmincoaching = tuitionmincoaching;
        this.tuitionmaxcoaching = tuitionmaxcoaching;
        this.tuitionadditional = tuitionadditional;
    }

    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
    }

    public String getTuitionfree() {
        return tuitionfree;
    }

    public void setTuitionfree(String tuitionfree) {
        this.tuitionfree = tuitionfree;
    }

    public String getTuitionlevel() {
        return tuitionlevel;
    }

    public void setTuitionlevel(String tuitionlevel) {
        this.tuitionlevel = tuitionlevel;
    }

    public String getTuitionstipendfacility() {
        return tuitionstipendfacility;
    }

    public void setTuitionstipendfacility(String tuitionstipendfacility) {
        this.tuitionstipendfacility = tuitionstipendfacility;
    }

    public String getTuitionstipendtype() {
        return tuitionstipendtype;
    }

    public void setTuitionstipendtype(String tuitionstipendtype) {
        this.tuitionstipendtype = tuitionstipendtype;
    }

    public String getTuitiondetails() {
        return tuitiondetails;
    }

    public void setTuitiondetails(String tuitiondetails) {
        this.tuitiondetails = tuitiondetails;
    }

    public String getTuitionminfee() {
        return tuitionminfee;
    }

    public void setTuitionminfee(String tuitionminfee) {
        this.tuitionminfee = tuitionminfee;
    }

    public String getTuitionmaxfee() {
        return tuitionmaxfee;
    }

    public void setTuitionmaxfee(String tuitionmaxfee) {
        this.tuitionmaxfee = tuitionmaxfee;
    }

    public String getTuitionmincoaching() {
        return tuitionmincoaching;
    }

    public void setTuitionmincoaching(String tuitionmincoaching) {
        this.tuitionmincoaching = tuitionmincoaching;
    }

    public String getTuitionmaxcoaching() {
        return tuitionmaxcoaching;
    }

    public void setTuitionmaxcoaching(String tuitionmaxcoaching) {
        this.tuitionmaxcoaching = tuitionmaxcoaching;
    }

    public String getTuitionadditional() {
        return tuitionadditional;
    }

    public void setTuitionadditional(String tuitionadditional) {
        this.tuitionadditional = tuitionadditional;
    }

    public int getServiceproviderId() {
        return serviceproviderId;
    }

    public void setServiceproviderId(int serviceproviderId) {
        this.serviceproviderId = serviceproviderId;
    }

    public static EducationTuitionDetailsItem parseEducationTuitionDetailsItem(JSONObject jo) throws JSONException {
        int _eduId = jo.getInt("id");
        int _sproviderid = jo.getInt("_service_provider");
        String _tuitionfree = jo.getString("tuition_free");
        String _tuitionlevel = jo.getString("tuition_level");
        String _tuitionstipendfacility = jo.getString("tuition_stipend_speciality");
        String _tuitionstipendtype = jo.getString("tuition_stipend_type");
        String _tuitiondetails = jo.getString("tuition_details");
        String _tuitionminfee = jo.getString("tuition_min_fee");
        String _tuitionmaxfee =jo.getString("tuition_max_fee");
        String _tuitionmincoaching = jo.getString("tuition_min_coaching");
        String _tuitionmaxcoaching = jo.getString("tuition_max_coaching");
        String _tuitionadditional = jo.getString("tuition_additional");
        return new EducationTuitionDetailsItem(_eduId,_sproviderid,
                _tuitionfree,_tuitionlevel,_tuitionstipendfacility,_tuitionstipendtype,_tuitiondetails,_tuitionminfee,
                _tuitionmaxfee,_tuitionmincoaching,_tuitionmaxcoaching,_tuitionadditional);
    }
}
