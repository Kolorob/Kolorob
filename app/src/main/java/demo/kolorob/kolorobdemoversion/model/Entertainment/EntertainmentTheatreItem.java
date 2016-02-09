package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/8/2016.
 */
public class EntertainmentTheatreItem {
    private String nodeId;
    private int entSubCategoryId;
    private int theatreId;
    private String eventtype;
    private String eventfee;
    private String eventdate;
    private String remarks;
    private String eventfeeffp;
    private String eventfeefoc;

    public EntertainmentTheatreItem(String nodeId, int entSubCategoryId, int theatreId, String eventtype, String eventfee, String eventdate, String remarks, String eventfeeffp, String eventfeefoc) {
        this.nodeId = nodeId;
        this.entSubCategoryId = entSubCategoryId;
        this.theatreId = theatreId;
        this.eventtype = eventtype;
        this.eventfee = eventfee;
        this.eventdate = eventdate;
        this.remarks = remarks;
        this.eventfeeffp = eventfeeffp;
        this.eventfeefoc = eventfeefoc;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getEntSubCategoryId() {
        return entSubCategoryId;
    }

    public void setEntSubCategoryId(int entSubCategoryId) {
        this.entSubCategoryId = entSubCategoryId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getEventfee() {
        return eventfee;
    }

    public void setEventfee(String eventfee) {
        this.eventfee = eventfee;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEventfeeffp() {
        return eventfeeffp;
    }

    public void setEventfeeffp(String eventfeeffp) {
        this.eventfeeffp = eventfeeffp;
    }

    public String getEventfeefoc() {
        return eventfeefoc;
    }

    public void setEventfeefoc(String eventfeefoc) {
        this.eventfeefoc = eventfeefoc;
    }
    public static EntertainmentTheatreItem parseEntertainmentTheatreItem(JSONObject jo) throws JSONException {
        String _nodeId = jo.getString("node_id");
        int _entSubCategoryId = jo.getInt("ent_sub_category_id");
        int _theatreId = jo.getInt("theatre_id");
        String _eventtype = jo.getString("event_type");
        String _eventfee = jo.getString("event_fee");
        String _eventdate = jo.getString("event_date");
        String _remarks = jo.getString("remarks");
        String _eventfeeffp = jo.getString("event_fee_ffp");
        String _eventfeefoc = jo.getString("event_fee_foc");
        return new EntertainmentTheatreItem(
                _nodeId,
                _entSubCategoryId,
                _theatreId,
                _eventtype,
                _eventfee,
                _eventdate,
                _remarks,
                _eventfeeffp,
                _eventfeefoc

        );
    }
}
