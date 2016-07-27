package demo.kolorob.kolorobdemoversion.model.LegalAid;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by arafat 24/06/16.
 */
public class LegalAidServiceProviderItemNew implements Serializable {
    private String identifierId;
    private String post_office;
    private int legalaidSubCategoryId;
    private String categoryId;
    private String legalaidNameEng;
    private String legalaidNameBan;
    private String contactPersonDesignation;
    private String contactNo;
    private String emailAddress;
    private String websiteLink;
    private String fbLink;
    private String registeredWith;
    private String registrationNo;
    private String additionalInfo;
    private String area;
    private String address;
    private String latitude;
    private String longitude;
    private String openingtime;
    private String closingtime;
    private String breaktime;
    private String breaktime2;
    private String landmark;
    private String road;
    private String block;
    private String off_day;
    private String floor;
    private String house_name;
    private String house_no;
    private String line;
    private String avenue;
    private String police_station;

    public LegalAidServiceProviderItemNew(String identifierId, String post_office, int legalaidSubCategoryId, String categoryId, String legalaidNameEng, String legalaidNameBan, String contactPersonDesignation, String contactNo, String emailAddress, String websiteLink, String fbLink, String registeredWith, String registrationNo, String additionalInfo, String area, String address, String latitude, String longitude, String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String off_day, String floor
            , String house_name,
             String house_no,
             String line,
             String avenue,
             String police_station) {
        this.identifierId = identifierId;
        this.post_office = post_office;
        this.legalaidSubCategoryId = legalaidSubCategoryId;
        this.categoryId = categoryId;
        this.legalaidNameEng = legalaidNameEng;
        this.legalaidNameBan = legalaidNameBan;
        this.contactPersonDesignation = contactPersonDesignation;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.websiteLink = websiteLink;
        this.fbLink = fbLink;
        this.registeredWith = registeredWith;
        this.registrationNo = registrationNo;
        this.additionalInfo = additionalInfo;
        this.area = area;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openingtime = openingtime;
        this.breaktime = breaktime;
        this.closingtime = closingtime;
        this.landmark = landmark;
        this.road = road;
        this.block = block;
        this.breaktime2 = breaktime2;
        this.off_day = off_day;
        this.floor = floor;
        this.house_name=house_name;
        this.house_no=house_no;
        this.landmark = landmark;
        this.road = road;
        this.block = block;
        this.breaktime2 = breaktime2;
        this.off_day = off_day;
        this.line=line;
        this.avenue=avenue;
        this.police_station=police_station;

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

    public String getOff_day() {
        return off_day;
    }

    public void setOff_day(String off_day) {
        this.off_day = off_day;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public String getPost_office() {
        return post_office;
    }

    public void setPost_office(String post_office) {
        this.post_office = post_office;
    }

    public int getLegalaidSubCategoryId() {
        return legalaidSubCategoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getLegalaidNameEng() {
        return legalaidNameEng;
    }

    public String getLegalaidNameBan() {
        return legalaidNameBan;
    }

    public String getContactPersonDesignation() {
        return contactPersonDesignation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public String getFbLink() {
        return fbLink;
    }

    public String getRegisteredWith() {
        return registeredWith;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getArea() {
        return area;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
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

    public String getFloor() {
        return floor;
    }

    public String getHouse_name() {
        return house_name;
    }

    public String getHouse_no() {
        return house_no;
    }

    public String getLine() {
        return line;
    }

    public String getAvenue() {
        return avenue;
    }

    public String getPolice_station() {
        return police_station;
    }

    public static LegalAidServiceProviderItemNew parseLegalAidServiceProviderItemNew(JSONObject jo) throws JSONException {


        String _openingtime="";
        String _breaktime="";
        String _closingtime="";
        String off_day="";
        String _registeredWith="";
        String _registrationNo="";

        String _identifierId = jo.getString("id");
        String _legalaidNameEng = jo.getString("node_name");
        String _legalaidBan = jo.getString("node_bn");



        JSONObject map_info= jo.getJSONObject("map_info");
        String _latitude = map_info.getString("lat");
        String _longitude = map_info.getString("lon");


        JSONObject contactInfo= jo.getJSONObject("contact_info");
        String floor=contactInfo.getString("floor");
        String house_name=contactInfo.getString("house_name");
        String house_no=contactInfo.getString("house_no");
        String _road=contactInfo.getString("road");
        String line=contactInfo.getString("line");
        String avenue=contactInfo.getString("avenue");
        String _block =contactInfo.getString("block");
        String _area = contactInfo.getString("area");
        String _landmark=contactInfo.getString("landmark");
        String post_office = contactInfo.getString("post_office");
        String police_station=contactInfo.getString("police_station");
        String _contactNo = contactInfo.getString("node_contact");
        String _emailAddress = contactInfo.getString("node_email");
        String _websiteLink = contactInfo.getString("node_website");
        String _fbLink = contactInfo.getString("node_facebook");
        String _contactPersonDesignation = contactInfo.getString("node_designation");
        String _additionalInfo = contactInfo.getString("city");
        String _address = contactInfo.getString("country");

        if(jo.has("timing_info")) {
            JSONObject timingInfo= jo.getJSONObject("timing_info");
            _openingtime=timingInfo.getString("opening_time");
            _breaktime=timingInfo.getString("break_time");
            _closingtime=timingInfo.getString("closing_time");
             off_day=timingInfo.getString("off_day");
        }

        if (jo.has("registration_info"))
        {

            JSONObject registrationInfo= jo.getJSONObject("registration_info");
            _registeredWith = registrationInfo.getString("node_registered_with");
            _registrationNo = registrationInfo.getString("node_registered_number");
        }
        String _breaktime2=jo.getString("references");
        _breaktime2=_breaktime2.replace('[',',');
        _breaktime2=_breaktime2.replace(']',',');

        String _categoryId = jo.getString("sub_categories");
        _categoryId=_categoryId.replace('[',',');
        _categoryId=_categoryId.replace(']',',');


        int _legalaidSubCategoryId = 2;





        return new LegalAidServiceProviderItemNew(_identifierId, post_office, _legalaidSubCategoryId,
                _categoryId,_legalaidNameEng, _legalaidBan, _contactPersonDesignation, _contactNo, _emailAddress, _websiteLink,
                _fbLink, _registeredWith, _registrationNo,
                _additionalInfo, _area, _address, _latitude, _longitude, _openingtime, _breaktime, _closingtime, _landmark, _road, _block, _breaktime2,
                off_day,floor,house_name,house_no,line,avenue,police_station);

    }
}
