package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.Serializable;

/**
 * Created by Mazharul.Islam1 on 1/5/2016.
 */
public class EntertainmentServiceProviderItem implements Serializable {
    private String nodeId;
    private int entSubCategoryId;
    private String nodeName;
    private String nodeNameBn;
    private String dataName;
    private String dataDate;
    private String nodeDesignation;
    private String nodeContact;
    private String nodeEmail;
    private String nodeAdditional;
    private String nodeWebsite;
    private String nodeFacebook;
    private String nodeRegisteredWith;
    private String nodeRegistrationNumber;
    private String editedBy;
    private String uploadingTime;
    private String nodeType;
    private String area;
    private String address;
    private String latitude;
    private String longitude;
    private int categoryId;
    private String openingTime;
    private String breakTime;
    private String closingTime;




    public EntertainmentServiceProviderItem(
            String nodeId,
            int entSubCategoryId,
            String nodeName,
            String nodeNameBn,
            String dataName,
            String dataDate,
            String nodeDesignation,
            String nodeContact,
            String nodeEmail,
            String nodeAdditional,
            String nodeWebsite,
            String nodeFacebook,
            String nodeRegisteredWith,
            String nodeRegistrationNumber,
            String editedBy,
            String uploadingTime,
            String nodeType,
            String area,
            String address,
            String latitude,
            String longitude,
            int categoryId,
            String openingTime,
            String breakTime,
            String closingTime
            ){

        this.nodeId=nodeId;
        this.entSubCategoryId=entSubCategoryId;
        this.nodeName=nodeName;
        this.nodeNameBn=nodeNameBn;
        this.dataName=dataName;
        this.dataDate=dataDate;
        this.nodeDesignation=nodeDesignation;
        this.nodeContact=nodeContact;
        this.nodeEmail=nodeEmail;
        this.nodeAdditional=nodeAdditional;
        this.nodeWebsite=nodeWebsite;
        this.nodeFacebook=nodeFacebook;
        this.nodeRegisteredWith=nodeRegisteredWith;
        this.nodeRegistrationNumber=nodeRegistrationNumber;
        this.editedBy=editedBy;
        this.uploadingTime=uploadingTime;
        this.nodeType=nodeType;
        this.area=area;
        this.address=address;
        this.latitude=latitude;
        this.longitude=longitude;
        this.categoryId=categoryId;
        this.openingTime=openingTime;
        this.breakTime=breakTime;
        this.closingTime=closingTime;


    }


    public static EntertainmentServiceProviderItem parseEntertainmentServiceProviderItem(JSONObject jo) throws JSONException
    {
        String _nodeId= jo.getString("node_id");
        Integer _entSubCategoryId= jo.getInt("ent_sub_category_id");
        String _nodeName = jo.getString("node_name");
        String _nodeNameBn= jo.getString("node_name_bn");
        String _dataName = jo.getString("data_name");
        String _dataDate = jo.getString("data_date");
        String _nodeDesignation = jo.getString("node_designation");
        String _nodeContact = jo.getString("node_contact");
        String _nodeEmail = jo.getString("node_email");
        String _nodeAdditional = jo.getString("node_additional");
        String _nodeWebsite = jo.getString("node_website");
        String _nodeFacebook = jo.getString("node_facebook");
        String _nodeRegisteredWith = jo.getString("node_registered_with");
        String _nodeRegistrationNumber = jo.getString("node_registration_number");
        String _editedBy= jo.getString("edited_by");
        String _uploadingTime = jo.getString("uploading_time");
        String _nodeType = jo.getString("node_type");
        String _area = jo.getString("area");
        String _address = jo.getString("address");
        String _latitude = jo.getString("latitude");
        String _longitude= jo.getString("longitude");
        int _categoryId= jo.getInt("category_id");
        String _openingTime = jo.getString("opening_time");
        String _breakTime = jo.getString("break_time");
        String _closingTime = jo.getString("closing_time");

        return new EntertainmentServiceProviderItem(
                _nodeId,
                _entSubCategoryId,
                _nodeName,
                _nodeNameBn,
                _dataName,
                _dataDate,
                _nodeDesignation,
                _nodeContact,
                _nodeEmail,
                _nodeAdditional,
                _nodeWebsite,
                _nodeFacebook,
                _nodeRegisteredWith,
                _nodeRegistrationNumber,
                _editedBy,
                _uploadingTime,
                _nodeType,
                _area,
                _address,
                _latitude,
                _longitude,
                _categoryId,
                _openingTime,
                _breakTime,
                _closingTime




        );
    }


    public String getNodeId() {
        return nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public int getEntSubCategoryId() {
        return entSubCategoryId;
    }

    public String getNodeNameBn() {
        return nodeNameBn;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataDate() {
        return dataDate;
    }

    public String getNodeDesignation() {
        return nodeDesignation;
    }

    public String getNodeEmail() {
        return nodeEmail;
    }

    public String getNodeAdditional() {
        return nodeAdditional;
    }

    public String getNodeContact() {
        return nodeContact;
    }

    public String getNodeWebsite() {
        return nodeWebsite;
    }

    public String getNodeFacebook() {
        return nodeFacebook;
    }

    public String getNodeRegisteredWith() {
        return nodeRegisteredWith;
    }

    public String getNodeRegistrationNumber() {
        return nodeRegistrationNumber;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public String getUploadingTime() {
        return uploadingTime;
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
}