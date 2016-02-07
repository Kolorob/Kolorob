package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/7/2016.
 */
public class FinancialSocialItem {
    private String nodeId;
    private String servicename;
    private String yn;
    private String servicecost;
    private String serviceremark;
    private String refNum;

    public FinancialSocialItem(String nodeId, String servicename, String yn, String servicecost, String serviceremark, String refNum) {
        this.nodeId = nodeId;
        this.servicename = servicename;
        this.yn = yn;
        this.servicecost = servicecost;
        this.serviceremark = serviceremark;
        this.refNum = refNum;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getYn() {
        return yn;
    }

    public void setYn(String yn) {
        this.yn = yn;
    }

    public String getServicecost() {
        return servicecost;
    }

    public void setServicecost(String servicecost) {
        this.servicecost = servicecost;
    }

    public String getServiceremark() {
        return serviceremark;
    }

    public void setServiceremark(String serviceremark) {
        this.serviceremark = serviceremark;
    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public static FinancialSocialItem parseFinancialSocialItem(JSONObject jo) throws JSONException {
        String _nodeId = jo.getString("f_node_id");
        String _servicename = jo.getString("service_name");
        String _yn = jo.getString("yes_no");
        String _servicecost = jo.getString("costs");
        String _serviceremark = jo.getString("remark");
        String _refNum = jo.getString("ref_num");

        return new FinancialSocialItem(_nodeId,
                _servicename, _yn, _servicecost,_serviceremark,_refNum);
    }
}
