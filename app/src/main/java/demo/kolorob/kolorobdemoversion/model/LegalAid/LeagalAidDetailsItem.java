package demo.kolorob.kolorobdemoversion.model.LegalAid;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mazharul.Islam1 on 6/20/2016.
 */
public class LeagalAidDetailsItem {
    private String nodeId;
    private String lagal_cost;
    private String legal_responsible_person;
    private String lagal_remarks;
    private String type;
    private String sub_type;


    public LeagalAidDetailsItem(String nodeId,
                                String lagal_cost,
                                String legal_responsible_person,
                                String lagal_remarks,
                                String type,
                                String sub_type) {
        this.nodeId = nodeId;
        this.lagal_cost = lagal_cost;
        this.legal_responsible_person = legal_responsible_person;
        this.lagal_remarks = lagal_remarks;
        this.type = type;
        this.sub_type = sub_type;


    }

    public String getLagal_cost() {
        return lagal_cost;
    }

    public void setLagal_cost(String lagal_cost) {
        this.lagal_cost = lagal_cost;
    }

    public String getLegal_responsible_person() {
        return legal_responsible_person;
    }

    public void setLegal_responsible_person(String legal_responsible_person) {
        this.legal_responsible_person = legal_responsible_person;
    }

    public String getLagal_remarks() {
        return lagal_remarks;
    }

    public void setLagal_remarks(String lagal_remarks) {
        this.lagal_remarks = lagal_remarks;
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

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public static LeagalAidDetailsItem parseLegalAidDetailsItem(JSONObject jo,int foreign_key) throws JSONException {

        String id = jo.getString("id");
        String legal_cost = jo.getString("legal_cost");
        String legal_responsible_person = jo.getString("legal_responsible_person");
        String legal_remark = String.valueOf(foreign_key);
        JSONObject legal_service=jo.getJSONObject("legal_service");
        String type = legal_service.getString("type");
        String sub_type = legal_service.getString("sub_type");




        return new LeagalAidDetailsItem(
                id,
                legal_cost,
                legal_responsible_person,
                legal_remark,
                type,
                sub_type


        );
    }
}