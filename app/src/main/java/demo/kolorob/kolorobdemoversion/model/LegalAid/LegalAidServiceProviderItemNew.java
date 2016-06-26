package demo.kolorob.kolorobdemoversion.model.LegalAid;

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
    private int categoryId;
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

    public LegalAidServiceProviderItemNew(String identifierId, String post_office, int legalaidSubCategoryId, int categoryId, String legalaidNameEng, String legalaidNameBan, String contactPersonDesignation, String contactNo, String emailAddress, String websiteLink, String fbLink, String registeredWith, String registrationNo, String additionalInfo, String area, String address, String latitude, String longitude, String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String off_day, String floor
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

    public int getCategoryId() {
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

        String _identifierId = jo.getString("identifier_id");
        String post_office = jo.getString("post_office");
        int _legalaidSubCategoryId = jo.getInt("legal_aid_subcategory_id");
        int _categoryId = jo.getInt("category_id");
        String _legalaidNameEng = jo.getString("legal_aid_name_eng");
        String _legalaidBan = jo.getString("legal_aid_name_ban");
        String _contactPersonDesignation = jo.getString("contact_person_designation");
        String _contactNo = jo.getString("contact_no");
        String _emailAddress = jo.getString("email_address");
        String _websiteLink = jo.getString("website_link");
        String _fbLink = jo.getString("fb_link");
        String _registeredWith = jo.getString("registered_with");
        String _registrationNo = jo.getString("registration_no");
        String _additionalInfo = jo.getString("additional_info");
        String _area = jo.getString("area");
        String _address = jo.getString("address");
        String _latitude = jo.getString("latitude");
        String _longitude = jo.getString("longitude");
        String _openingtime=jo.getString("opening_time");
        String _breaktime=jo.getString("break_time");
        String _closingtime=jo.getString("closing_time");
        String _landmark=jo.getString("landmark");
        String _road=jo.getString("road");
        String _block =jo.getString("block");
        String _breaktime2=jo.getString("break_time2");
        String off_day=jo.getString("off_day");
        String floor=jo.getString("floor");
        String house_name=jo.getString("house_name");
        String house_no=jo.getString("house_no");
        String line=jo.getString("line");
        String avenue=jo.getString("off_day");
        String police_station=jo.getString("off_day");








        return new LegalAidServiceProviderItemNew(_identifierId, post_office, _legalaidSubCategoryId,
                _categoryId,_legalaidNameEng, _legalaidBan, _contactPersonDesignation, _contactNo, _emailAddress, _websiteLink,
                _fbLink, _registeredWith, _registrationNo,
                _additionalInfo, _area, _address, _latitude, _longitude, _openingtime, _breaktime, _closingtime, _landmark, _road, _block, _breaktime2,
                off_day,floor,house_name,house_no,line,avenue,police_station);

    }
}
