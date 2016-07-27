package demo.kolorob.kolorobdemoversion.model.Entertainment;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.Serializable;

/**
 * Created by arafat on 1/5/2016.
 */
public class EntertainmentServiceProviderItemNew implements Serializable {
    private String nodeId;
    private int entSubCategoryId;
    private String nodeName;
    private String nodeNameBn;
    public static JSONObject contact_info;


    private String nodeDesignation;
    private String nodeContact;
    private String nodeEmail;
    private String nodeAdditional;
    private String nodeWebsite;
    private String nodeFacebook;
    private String nodeRegisteredWith;
    private String nodeRegistrationNumber;


    private String nodeType;
    private String area;
    private String address;
    private String latitude;
    private String longitude;
    private String categoryId;
    private String openingtime;
    private String closingtime;
    private String breaktime;
    private String breaktime2;
    private String road;
    private String block;
    private String landmark;
    private String floor;
    private String house_name;
    private String house_no;
    private String line;
    private String avenue;
    private String post_office;
    private String police_station;
    private String city;
    private String off_day;





    public EntertainmentServiceProviderItemNew(
            String nodeId,
            int entSubCategoryId,
            String nodeName,
            String nodeNameBn,

            String nodeDesignation,
            String nodeContact,
            String nodeEmail,
            String nodeAdditional,
            String nodeWebsite,
            String nodeFacebook,
            String nodeRegisteredWith,
            String nodeRegistrationNumber,

            String nodeType,
            String area,
            String address,
            String latitude,
            String longitude,
            String categoryId,
            String openingtime,
            String breaktime,
            String closingtime,
            String road,
            String block,
            String landmark,
            String breaktime2,
            String floor,
                    String house_name,
                    String house_no,
                    String line,
                    String avenue,
                    String post_office,
                    String police_station,
                    String city,
                    String off_day){

        this.nodeId=nodeId;
        this.entSubCategoryId=entSubCategoryId;
        this.nodeName=nodeName;
        this.nodeNameBn=nodeNameBn;

        this.nodeDesignation=nodeDesignation;
        this.nodeContact=nodeContact;
        this.nodeEmail=nodeEmail;
        this.nodeAdditional=nodeAdditional;
        this.nodeWebsite=nodeWebsite;
        this.nodeFacebook=nodeFacebook;
        this.nodeRegisteredWith=nodeRegisteredWith;
        this.nodeRegistrationNumber=nodeRegistrationNumber;

        this.nodeType=nodeType;
        this.area=area;
        this.address=address;
        this.latitude=latitude;
        this.longitude=longitude;
        this.categoryId=categoryId;
        this.openingtime = openingtime;
        this.breaktime = breaktime;
        this.closingtime = closingtime;
        this.road = road;
        this.block = block;
        this.landmark=landmark;
        this.breaktime2=breaktime2;
        this.floor = floor;
        this.house_no = house_no;
        this.house_name = house_name;
        this.line = line;
        this.avenue = avenue;
        this.post_office = post_office;
        this.police_station = police_station;
        this.city = city;
        this.off_day = off_day;



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

    public String getFloor() {
        return floor;
    }

    public String getHouse_no() {
        return house_no;
    }

    public String getHouse_name() {
        return house_name;
    }

    public String getLine() {
        return line;
    }

    public String getAvenue() {
        return avenue;
    }

    public String getPost_office() {
        return post_office;
    }

    public String getPolice_station() {
        return police_station;
    }

    public String getCity() {
        return city;
    }

    public String getOff_day() {
        return off_day;
    }

    public static EntertainmentServiceProviderItemNew parseEntertainmentServiceProviderItem(JSONObject jo, int i) throws JSONException
    {

        String _latitude="";
        String _longitude="";
        String _nodeType="";
        String floor="";
        String house_name="";
        String house_no="";
        String _road="";
        String line="";
        String avenue="";
        String _block ="";
        String _area ="";
        String _landmark="";
        String post_office="";
        String police_station="";
        String city="";
        String _nodeContact="";
        String _nodeEmail="";
        String _nodeWebsite="";
        String _nodeFacebook="";
        String _nodeDesignation="";

//        String _nodeIds= String.valueOf(i)="";

        String _openingtime="";
        String _breaktime="";
        String _closingtime="";
        String _breaktime2="";
        String off_day="";

        String _nodeId= jo.getString("id");
        String _nodeName = jo.getString("node_name");
        String _nodeNameBn= jo.getString("node_bn");

        if(jo.has("map_info")) {
            JSONObject map_info=jo.getJSONObject("map_info");
            _latitude = map_info.getString("lat");
            _longitude= map_info.getString("lon");
            _nodeType = map_info.getString("node_type");

        }


        if(jo.has("contact_info")) {
            JSONObject contact_info=jo.getJSONObject("contact_info");

            floor=contact_info.getString("floor");
            house_name=contact_info.getString("house_name");
            house_no=contact_info.getString("house_no");
            _road=contact_info.getString("road");
            line=contact_info.getString("line");
            avenue=contact_info.getString("avenue");
            _block =contact_info.getString("block");
            _area = contact_info.getString("area");
            _landmark=contact_info.getString("landmark");
            post_office=contact_info.getString("post_office");
            police_station=contact_info.getString("police_station");
            city=contact_info.getString("city");
            _nodeContact = contact_info.getString("node_contact");
            _nodeEmail = contact_info.getString("node_email");
            _nodeWebsite = contact_info.getString("node_website");
            _nodeFacebook = contact_info.getString("node_facebook");
            _nodeDesignation = contact_info.getString("address");
        }


        if(jo.has("timing_info")) {
               JSONObject timing_info=jo.getJSONObject("timing_info");
              _openingtime=timing_info.getString("opening_time");
              _breaktime=timing_info.getString("break_time");
              _closingtime=timing_info.getString("closing_time");
              _breaktime2=timing_info.getString("break_time");
               off_day=timing_info.getString("off_day");
        }




        //JSONObject registration_info=jo.getJSONObject("registration_info");
       // String _nodeRegisteredWiths = registration_info.getString("node_registered_with");
        String _nodeRegisteredWith = "node_registered_with";
       // String _nodeRegistrationNumbers = registration_info.getString("node_registered_number");
        String _nodeRegistrationNumber =  "node_registered_number";
//

        Integer _entSubCategoryId= 2;




        String _nodeAdditional = jo.getString("references");
        _nodeAdditional=_nodeAdditional.replace('[',',');
        _nodeAdditional=_nodeAdditional.replace(']',',');


      //  Log.d("saved","--------"+_nodeAdditional);


        String _address = "address";

        String _categoryId= jo.getString("sub_categories");
        _categoryId=_categoryId.replace('[',',');
        _categoryId=_categoryId.replace(']',',');




        return new EntertainmentServiceProviderItemNew(
                _nodeId,
                _entSubCategoryId,
                _nodeName,
                _nodeNameBn,
                _nodeDesignation,
                _nodeContact,
                _nodeEmail,
                _nodeAdditional,
                _nodeWebsite,
                _nodeFacebook,
                _nodeRegisteredWith,
                _nodeRegistrationNumber,
                _nodeType,
                _area,
                _address,
                _latitude,
                _longitude,
                _categoryId,
                _openingtime,
                _breaktime,
                _closingtime,
                _road, _block,_landmark,
                _breaktime2,floor,
                 house_name,
                 house_no,
                line,
                avenue,
                post_office,
                 police_station,
                 city,
                off_day);
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
    public String getCategoryId() {
        return categoryId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }




}