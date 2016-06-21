package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mazharul.Islam1 on 6/20/2016.
 */
public class EntertainmentSubTypeItem {
    private String nodeId;
    private String name;
    private String bn_label;
    private String en_label;
    private String _facilities;
    private String column_name;


    public EntertainmentSubTypeItem(String nodeId, String name, String bn_label, String en_label, String _facilities, String column_name) {
        this.nodeId = nodeId;
        this.name = name;
        this.bn_label = bn_label;
        this.en_label = en_label;
        this._facilities = _facilities;
        this.column_name = column_name;

    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBn_label() {
        return bn_label;
    }

    public void setBn_label(String bn_label) {
        this.bn_label = bn_label;
    }

    public String getEn_label() {
        return en_label;
    }

    public void setEn_label(String en_label) {
        this.en_label = en_label;
    }

    public String get_facilities() {
        return _facilities;
    }

    public void set_facilities(String _facilities) {
        this._facilities = _facilities;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public static EntertainmentSubTypeItem parseEntertainmentSubTypeItem(JSONObject jo) throws JSONException {
        String _nodeId = jo.getString("node_id");
        String name = jo.getString("name");
        String bn_label = jo.getString("bn_label");
        String en_label = jo.getString("en_label");
        String _facilities = jo.getString("_facilities");
        String column_name = jo.getString("column_name");



        return new EntertainmentSubTypeItem(
                _nodeId,
                name,
                bn_label,
                en_label,
                _facilities,
                column_name

        );
    }
}