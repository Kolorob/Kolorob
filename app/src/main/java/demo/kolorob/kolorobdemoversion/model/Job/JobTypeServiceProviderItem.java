package demo.kolorob.kolorobdemoversion.model.Job;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mity on 2/14/16.
 */
public class JobTypeServiceProviderItem {
    private String jobId;
    private String identifierId;
    private int jobSubCategoryId;
    private String jobname;

    private String jobnewssource;
    private String jobremark;
    private String jobtype;

    public JobTypeServiceProviderItem(String jobId, String identifierId, int jobSubCategoryId, String jobname, String jobnewssource, String jobremark, String jobtype) {
        this.jobId = jobId;
        this.identifierId = identifierId;
        this.jobSubCategoryId = jobSubCategoryId;
        this.jobname = jobname;
        this.jobnewssource = jobnewssource;
        this.jobremark = jobremark;
        this.jobtype = jobtype;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public void setIdentifierId(String identifierId) {
        this.identifierId = identifierId;
    }

    public int getJobSubCategoryId() {
        return jobSubCategoryId;
    }

    public void setJobSubCategoryId(int jobSubCategoryId) {
        this.jobSubCategoryId = jobSubCategoryId;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobnewssource() {
        return jobnewssource;
    }

    public void setJobnewssource(String jobnewssource) {
        this.jobnewssource = jobnewssource;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getJobremark() {
        return jobremark;
    }

    public void setJobremark(String jobremark) {
        this.jobremark = jobremark;
    }

    public static JobTypeServiceProviderItem parseJobTypeServiceProvider(JSONObject jo) throws JSONException {
        String _job_id = jo.getString("job_id");
        String _identifier_id = jo.getString("identifier_id");
        int _jobSubCategoryId = jo.getInt("job_subcategory_id");
        String _job_name = jo.getString("job_name");
        String _job_news_source = jo.getString("job_news_source");
        String _job_remark = jo.getString("job_remark");
        String _job_type = jo.getString("job_type");


        return new JobTypeServiceProviderItem(_job_id, _identifier_id, _jobSubCategoryId
               , _job_name, _job_news_source, _job_remark, _job_type);
    }
}
