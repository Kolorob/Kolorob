package demo.kolorob.kolorobdemoversion.model.Finance;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by mity on 1/11/16.
 */
public class FinanceServiceProviderItem implements Serializable {


    private String nodeId;
    private String nodeName;
    private String dateName;
    private String dateDate;
    private String nodeDesignation;
    private String nodeContact;
    private String nodeEmail;
    private String nodeAdditional;
    private String nodeWebsite;
    private String nodeFacebook;
    private String nodeRegisteredwith;
    private String nodeRegistationnumber;
    private String editedBy;
    private int refNum;
    private String nameBn;
    private String timeStamp;
    private String nodeType;
    private String area;
    private String address;
    private String longitude;
    private String latitude;
    private int categoryId;

    public FinanceServiceProviderItem(String nodeId, String nodeName, String dateName, String dateDate, String nodeDesignation, String nodeContact, String nodeEmail, String nodeAdditional, String nodeWebsite, String nodeFacebook, String nodeRegisteredwith, String nodeRegistationnumber, String editedBy, int refNum, String nameBn, String timeStamp, String nodeType, String area, String address, String longitude, String latitude, int categoryId) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.dateName = dateName;
        this.dateDate = dateDate;
        this.nodeDesignation = nodeDesignation;
        this.nodeContact = nodeContact;
        this.nodeEmail = nodeEmail;
        this.nodeAdditional = nodeAdditional;
        this.nodeWebsite = nodeWebsite;
        this.nodeFacebook = nodeFacebook;
        this.nodeRegisteredwith = nodeRegisteredwith;
        this.nodeRegistationnumber = nodeRegistationnumber;
        this.editedBy = editedBy;
        this.refNum = refNum;
        this.nameBn = nameBn;
        this.timeStamp = timeStamp;
        this.nodeType = nodeType;
        this.area = area;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categoryId = categoryId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getDateName() {
        return dateName;
    }

    public String getDateDate() {
        return dateDate;
    }

    public String getNodeDesignation() {
        return nodeDesignation;
    }

    public String getNodeContact() {
        return nodeContact;
    }

    public String getNodeEmail() {
        return nodeEmail;
    }

    public String getNodeAdditional() {
        return nodeAdditional;
    }

    public String getNodeWebsite() {
        return nodeWebsite;
    }

    public String getNodeFacebook() {
        return nodeFacebook;
    }

    public String getNodeRegisteredwith() {
        return nodeRegisteredwith;
    }

    public String getNodeRegistationnumber() {
        return nodeRegistationnumber;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public int getRefNum() {
        return refNum;
    }

    public String getNameBn() {
        return nameBn;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getNodeType() {
        return nodeType;
    }

    public String getArea() {
        return area;
    }

    public String getAddress() {
        return address;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public static FinanceServiceProviderItem parseFinanceServiceProviderItem(JSONObject jo) throws JSONException {
        String _FinnodeId=jo.getString("node_id");
        String _FinnodeName= jo.getString("node_name");
        String _FindateName=jo.getString("data_name");
        String _FinDate=jo.getString("data_date");
        String _FinnodeDesignation= jo.getString("node_designation");
        String _FinnodeContact = jo.getString("node_contact");
        String _FinnodeEmail=jo.getString("node_email");
        String _FinnodeAdditional= jo.getString("node_additional");
        String _FinnodeWebsite = jo.getString("node_website");
        String _FinnodeFacebook = jo.getString("node_facebook");
        String _FinnodeRegisteredwith = jo.getString("node_registered_with");
        String _FinnodeRegistationNumber= jo.getString("node_registration_number");
        String _FineditedBy= jo.getString("edited_by");
        int _FinrefNum= jo.getInt("ref_num");
        String _FinnameBn=jo.getString("name_bn");
        String _FintimeStamp=jo.getString("timestamp");
        String _FinnodeType=jo.getString("node_type");
        String _Finarea=jo.getString("area");
        String _Finaddress=jo.getString("address");
        String _Finlongitude=jo.getString("latitude");
        String _Finlatitude = jo.getString("longitude");

        int _categoryId=jo.getInt("category_id");


        return new FinanceServiceProviderItem(
                _FinnodeId,
                _FinnodeName,
                _FindateName,
                _FinDate,
                _FinnodeDesignation,
                _FinnodeContact,
                _FinnodeEmail,
                _FinnodeAdditional,
                _FinnodeWebsite,
                _FinnodeFacebook,
                _FinnodeRegisteredwith,
                _FinnodeRegistationNumber,
                _FineditedBy,
                _FinrefNum,
                _FinnameBn,
                _FintimeStamp,
                _FinnodeType,
                _Finarea,
                _Finaddress,
                _Finlongitude,
                _Finlatitude,
                _categoryId

        );





    }
}
