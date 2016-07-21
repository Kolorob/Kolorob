package demo.kolorob.kolorobdemoversion.model.Entertainment;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mazharul.Islam1 on 6/20/2016.
 */
public class EntertainmentTypeItem {
    private String nodeId;
    private String recreation_price;
    private String recreation_remarks;
    private String type;
    private String sub_type;


    public EntertainmentTypeItem(String nodeId, String recreation_price, String recreation_remarks, String type, String sub_type) {
        this.nodeId = nodeId;
        this.recreation_price = recreation_price;
        this.recreation_remarks = recreation_remarks;
        this.type = type;
        this.sub_type = sub_type;


    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getRecreation_price() {
        return recreation_price;
    }

    public void setRecreation_price(String recreation_price) {
        this.recreation_price = recreation_price;
    }

    public String getRecreation_remarks() {
        return recreation_remarks;
    }

    public void setRecreation_remarks(String recreation_remarks) {
        this.recreation_remarks = recreation_remarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public static EntertainmentTypeItem parseEntertainmentTypeItem(int nodeId,JSONObject jo) throws JSONException {
        String type="";
        String sub_type="";
        String id = String.valueOf(nodeId);
        String recreation_price = jo.getString("recreation_price");
        String recreation_remark = jo.getString("recreation_remark");



        if (jo.has("recreation_services"))
        {
            JSONObject recreation_services=jo.getJSONObject("recreation_services");


            type = recreation_services.getString("type");
            sub_type = recreation_services.getString("sub_type");
        }





        return new EntertainmentTypeItem(
                id,
                recreation_price,
                recreation_remark,
                type,
                sub_type


        );
    }
}