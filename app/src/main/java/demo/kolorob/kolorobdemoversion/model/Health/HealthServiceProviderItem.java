package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;



/**
 * Created by Mazharul.Islam1 on 1/10/2016.
 */
public class HealthServiceProviderItem implements Serializable {




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
    private String nodeRegistationNumber;
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

    private String openingtime;
    private String closingtime;
    private String breaktime;
    private String breaktime2;
    private String landmark;
    private String road;
    private String block;
    private String additionaltime;


    public HealthServiceProviderItem(
            String nodeId,
            String nodeName,
            String dateName,
            String dateDate,
            String nodeDesignation,
            String nodeContact,
            String nodeEmail,
            String nodeAdditional,
            String nodeWebsite,
            String nodeFacebook,
            String nodeRegisteredwith,
            String nodeRegistationNumber,
            String editedBy,
            int refNum,
            String nameBn,
            String timeStamp,
            String nodeType,
            String area,
            String address,
            String longitude,
            String latitude,
            int categoryId, String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String additionaltime){

        this.nodeId=nodeId;
        this.nodeName=nodeName;
        this.dateName=dateName;
        this.dateDate = dateDate;
        this.nodeDesignation=nodeDesignation;
        this.nodeContact=nodeContact;
        this.nodeEmail=nodeEmail;
        this.nodeAdditional=nodeAdditional;
        this.nodeWebsite=nodeWebsite;
        this.nodeFacebook=nodeFacebook;
        this.nodeRegisteredwith=nodeRegisteredwith;
        this.nodeRegistationNumber=nodeRegistationNumber;
        this.editedBy=editedBy;
        this.refNum=refNum;
        this.nameBn=nameBn;
        this.timeStamp=timeStamp;
        this.nodeType=nodeType;
        this.area=area;
        this.address=address;
        this.longitude=longitude;
        this.latitude=latitude;
        this.categoryId=categoryId;
        this.openingtime = openingtime;
        this.breaktime = breaktime;
        this.closingtime = closingtime;
        this.landmark = landmark;
        this.road = road;
        this.block = block;
        this.breaktime2 = breaktime2;
        this.additionaltime = additionaltime;


    }


    public String getOpeningtime() {
        return openingtime;
    }

    public String getClosingtime() {
        return closingtime;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public String getBreaktime2() {
        return breaktime2;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getRoad() {
        return road;
    }

    public String getBlock() {
        return block;
    }

    public String getAdditionaltime() {
        return additionaltime;
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

    public String getNodeEmail() {
        return nodeEmail;
    }

    public String getNodeFacebook() {
        return nodeFacebook;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public String getNodeType() {
        return nodeType;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getRefNum() {
        return refNum;
    }

    public String getNameBn() {
        return nameBn;
    }

    public String getNodeRegistationNumber() {
        return nodeRegistationNumber;
    }

    public String getNodeRegisteredwith() {
        return nodeRegisteredwith;
    }

    public String getNodeWebsite() {
        return nodeWebsite;
    }

    public String getNodeAdditional() {
        return nodeAdditional;
    }

    public String getNodeContact() {
        return nodeContact;
    }

    public String getNodeDesignation() {
        return nodeDesignation;
    }

    public String getDateDate() {
        return dateDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public static HealthServiceProviderItem parseHealthServiceProviderItem(JSONObject jo) throws JSONException{
        String _nodeId=jo.getString("node_id");
        String _nodeName= jo.getString("node_name");
        String _dateName=jo.getString("data_name");
        String _dateDate=jo.getString("data_date");
        String _nodeDesignation= jo.getString("node_designation");
        String _nodeContact = jo.getString("node_contact");
        String _nodeEmail=jo.getString("node_email");
        String _nodeAdditional= jo.getString("node_additional");
        String _nodeWebsite = jo.getString("node_website");
        String _nodeFacebook = jo.getString("node_facebook");
        String _nodeRegisteredwith = jo.getString("node_registered_with");
        String _nodeRegistationNumber= jo.getString("node_registration_number");
        String _editedBy= jo.getString("edited_by");
        int _refNum= jo.getInt("ref_num");
        String _nameBn=jo.getString("name_bn");
        String _timeStamp=jo.getString("timestamp");
        String _nodeType=jo.getString("node_type");
        String _area=jo.getString("area");
        String _address=jo.getString("address");
        String _longitude=jo.getString("latitude");
        String _latitude = jo.getString("longitude");

        int _categoryId=jo.getInt("category_id");
        String _openingtime=jo.getString("opening_time");
        String _breaktime=jo.getString("break_time");
        String _closingtime=jo.getString("closing_time");
        String _landmark=jo.getString("landmark");
        String _road=jo.getString("road");
        String _block =jo.getString("block");
        String _breaktime2=jo.getString("breaktime2");
        String _additionaltime=jo.getString("additional_time");


        return new HealthServiceProviderItem(
                _nodeId,
                _nodeName,
                _dateName,
                _dateDate,
                _nodeDesignation,
                _nodeContact,
                _nodeEmail,
                _nodeAdditional,
                _nodeWebsite,
                _nodeFacebook,
                _nodeRegisteredwith,
                _nodeRegistationNumber,
                _editedBy,
                _refNum,
                _nameBn,
                _timeStamp,
                _nodeType,
                _area,
                _address,
                _longitude,
                _latitude,
                _categoryId,

                _openingtime, _breaktime, _closingtime, _landmark, _road, _block, _breaktime2, _additionaltime);





    }
}