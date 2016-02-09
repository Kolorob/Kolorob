package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/8/2016.
 */
public class EntertainmentFieldItem {
    private String nodeId;
    private int entSubCategoryId;
    private String eventCost;
    private String playgroundcost;
    private String remark;
    private String eventcostffp;
    private String eventcostfoc;
    private String playgroundcostffp;
    private String playgroundcostfoc;

    public EntertainmentFieldItem(String nodeId, int entSubCategoryId, String eventCost, String playgroundcost, String remark, String eventcostffp, String eventcostfoc, String playgroundcostffp, String playgroundcostfoc) {
        this.nodeId = nodeId;
        this.entSubCategoryId = entSubCategoryId;
        this.eventCost = eventCost;
        this.playgroundcost = playgroundcost;
        this.remark = remark;
        this.eventcostffp = eventcostffp;
        this.eventcostfoc = eventcostfoc;
        this.playgroundcostffp = playgroundcostffp;
        this.playgroundcostfoc = playgroundcostfoc;
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

    public String getEventCost() {
        return eventCost;
    }

    public void setEventCost(String eventCost) {
        this.eventCost = eventCost;
    }

    public String getPlaygroundcost() {
        return playgroundcost;
    }

    public void setPlaygroundcost(String playgroundcost) {
        this.playgroundcost = playgroundcost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEventcostffp() {
        return eventcostffp;
    }

    public void setEventcostffp(String eventcostffp) {
        this.eventcostffp = eventcostffp;
    }

    public String getEventcostfoc() {
        return eventcostfoc;
    }

    public void setEventcostfoc(String eventcostfoc) {
        this.eventcostfoc = eventcostfoc;
    }

    public String getPlaygroundcostffp() {
        return playgroundcostffp;
    }

    public void setPlaygroundcostffp(String playgroundcostffp) {
        this.playgroundcostffp = playgroundcostffp;
    }

    public String getPlaygroundcostfoc() {
        return playgroundcostfoc;
    }

    public void setPlaygroundcostfoc(String playgroundcostfoc) {
        this.playgroundcostfoc = playgroundcostfoc;
    }
    public static EntertainmentFieldItem parseEntertainmentFieldItem(JSONObject jo) throws JSONException {
        String _nodeId = jo.getString("node_id");
        int _entSubCategoryId = jo.getInt("ent_sub_category_id");
        String _eventCost = jo.getString("event_cost");
        String _playgroundcost = jo.getString("playground_cost");
        String _remark = jo.getString("remark");
        String _eventcostffp = jo.getString("event_cost_ffp");
        String _eventcostfoc = jo.getString("event_cost_foc");
        String _playgroundcostffp = jo.getString("playground_cost_ffp");
        String _playgroundcostfoc = jo.getString("playground_cost_foc");


        return new EntertainmentFieldItem(
                _nodeId,
                _entSubCategoryId,
                _eventCost,
                _playgroundcost,
                _remark,
                _eventcostffp,
                _eventcostfoc,
                _playgroundcostffp,
                _playgroundcostfoc

        );
    }
}
