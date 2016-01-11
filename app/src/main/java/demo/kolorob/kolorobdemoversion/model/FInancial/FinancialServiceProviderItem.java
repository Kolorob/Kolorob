package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 1/11/2016.
 */
public class FinancialServiceProviderItem implements Serializable {


    private String nodeId;
    private String nodeName;
    private String nodeDesignation;
    private String nodeContact;
    private String nodeEmail;
    private String nodeAdditional;
    private String nodeWebsite;
    private String nodeFacebook;
    private String nodeRegisteredwith;
    private String nodeRegistationnumber;

    private int refNum;
    private String area;
    private String address;
    private String longitude;
    private String latitude;
    private int categoryId;

    public FinancialServiceProviderItem(String nodeId, String nodeName, String nodeDesignation, String nodeContact, String nodeEmail, String nodeAdditional, String nodeWebsite, String nodeFacebook, String nodeRegisteredwith, String nodeRegistationnumber, int refNum, String area, String address, String latitude, String longitude, int categoryId) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.nodeDesignation = nodeDesignation;
        this.nodeContact = nodeContact;
        this.nodeEmail = nodeEmail;
        this.nodeAdditional = nodeAdditional;
        this.nodeWebsite = nodeWebsite;
        this.nodeFacebook = nodeFacebook;
        this.nodeRegisteredwith = nodeRegisteredwith;
        this.nodeRegistationnumber = nodeRegistationnumber;
        this.refNum = refNum;
        this.area = area;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryId = categoryId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public int getRefNum() {
        return refNum;
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

    public String getNodeRegistrationnumber() {
        return nodeRegistationnumber;
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

    public static FinancialServiceProviderItem parseFinancialServiceProviderItem(JSONObject jo) throws JSONException {
        String _FinnodeId=jo.getString("node_id");
        String _FinnodeName= jo.getString("node_name");
        String _FinnodeDesignation= jo.getString("node_designation");
        String _FinnodeContact = jo.getString("node_contact");
        String _FinnodeEmail=jo.getString("node_email");
        String _FinnodeAdditional= jo.getString("node_additional");
        String _FinnodeWebsite = jo.getString("node_website");
        String _FinnodeFacebook = jo.getString("node_facebook");
        String _FinnodeRegisteredwith = jo.getString("node_registered_with");
        String _FinnodeRegistrationNumber= jo.getString("node_registration_number");
        int    _FinrefNum= jo.getInt("ref_num");
        String _Finarea=jo.getString("area");
        String _Finaddress=jo.getString("address");
        String _Finlatitude=jo.getString("latitude");
        String _Finlongitude = jo.getString("longitude");

        int _categoryId=jo.getInt("category_id");


        return new FinancialServiceProviderItem(
                _FinnodeId,
                _FinnodeName,
                _FinnodeDesignation,
                _FinnodeContact,
                _FinnodeEmail,
                _FinnodeAdditional,
                _FinnodeWebsite,
                _FinnodeFacebook,
                _FinnodeRegisteredwith,
                _FinnodeRegistrationNumber,
                _FinrefNum,
                _Finarea,
                _Finaddress,
                _Finlatitude,
                _Finlongitude,
                _categoryId
        );





    }
}