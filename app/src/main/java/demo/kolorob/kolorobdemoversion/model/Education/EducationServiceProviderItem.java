package demo.kolorob.kolorobdemoversion.model.Education;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;



/**
 * Created by Yeakub Hassan Rafi on 27-Dec-15.
 */


public class EducationServiceProviderItem implements Serializable {
    private String identifierId;
    private String serviceProviderId;
    private int eduSubCategoryId;
    private int categoryId;
    private String eduNameEng;
    private String eduNameBan;
    private String eduType;
    private String hostelFacility;
    private String transportFacility;
    private String playground;
    private String contactPersonDesignation;
    private String contactNo;
    private String emailAddress;
    private String websiteLink;
    private String fbLink;
    private String registeredWith;
    private String registrationNo;
    private int totalStudents;
    private int totalClasses;
    private int totalTeachers;
    private String courseProvided;
    private String shift;
    private String canteenFacility;
    private String additionalInfo;
    private String area;
    private String address;
    private String latitude;
    private String longitude;
    private String openingtime;
    private String breaktime;
    private String closingtime;
    private String road;
    private String block;
    private String landmark;
    private String breaktime2;
    private String additionaltime;

    public void setEduNameEng(String eduNameEng) {
        this.eduNameEng = eduNameEng;
    }

    public EducationServiceProviderItem(String identifierId,
                                        String serviceProviderId,
                                        int eduSubCategoryId,
                                        int categoryId,
                                        String eduNameEng,
                                        String eduNameBan,
                                        String eduType,
                                        String hostelFacility,
                                        String transportFacility,
                                        String playground,
                                        String contactPersonDesignation,
                                        String contactNo,
                                        String emailAddress,
                                        String websiteLink,
                                        String fbLink,
                                        String registeredWith,
                                        String registrationNo,
                                        int totalStudents,
                                        int totalClasses,
                                        int totalTeachers,
                                        String courseProvided,
                                        String shift,
                                        String canteenFacility,
                                        String additionalInfo,
                                        String area,
                                        String address,
                                        String latitude,
                                        String longitude,
                                        String openingtime,String breaktime,String closingtime,String road,String block,String landmark,String breaktime2,String additionaltime) {

        this.identifierId = identifierId ;
        this.serviceProviderId = serviceProviderId;
        this.eduSubCategoryId = eduSubCategoryId;
        this.categoryId = categoryId;
        this.eduNameEng = eduNameEng;
        this.eduNameBan = eduNameBan;
        this.eduType = eduType;
        this.hostelFacility = hostelFacility;
        this.transportFacility = transportFacility;
        this.playground = playground;
        this.contactPersonDesignation = contactPersonDesignation;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.websiteLink = websiteLink;
        this.fbLink = fbLink;
        this.registeredWith = registeredWith;
        this.registrationNo = registrationNo;
        this.totalStudents = totalStudents;
        this.totalClasses = totalClasses;
        this.totalTeachers = totalTeachers;
        this.courseProvided = courseProvided;
        this.shift = shift;
        this.canteenFacility = canteenFacility;
        this.additionalInfo = additionalInfo;
        this.area = area;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openingtime=openingtime;
        this.breaktime=breaktime;
        this.closingtime=closingtime;
        this.road=road;
        this.block=block;
        this.landmark=landmark;
        this.breaktime2=breaktime2;
        this.additionaltime=additionaltime;



    }



    public String getOpeningtime() {
        return openingtime;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public String getClosingtime() {
        return closingtime;
    }

    public String getRoad() {
        return road;
    }

    public String getBlock() {
        return block;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getBreaktime2() {
        return breaktime2;
    }

    public String getAdditionaltime() {
        return additionaltime;
    }

    public String getIdentifierId(){return identifierId ;}
    public String getServiceProviderId(){return serviceProviderId;}
    public int getEduSubCategoryId(){return eduSubCategoryId;}
    public int getCategoryId(){return categoryId;}
    public String getEduNameEng(){return eduNameEng;}
    public String getEduNameBan(){return eduNameBan;}
    public String getEduType(){return eduType;}
    public String getHostelFacility(){return hostelFacility;}
    public String getTransportFacility(){return transportFacility;}
    public String getPlayground(){return playground;}

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPersonDesignation(){return contactPersonDesignation;}
    public String getContactNo(){return contactNo;}
    public String getEmailAddress(){return emailAddress;}
    public String getWebsiteLink(){return websiteLink;}
    public String getFbLink(){return fbLink;}
    public String getRegisteredWith(){return registeredWith;}
    public String getRegistrationNo(){return registrationNo;}
    public int getTotalStudents(){return totalStudents;}
    public int getTotalClasses(){return totalClasses;}
    public int getTotalTeachers(){return totalTeachers;}
    public String getCourseProvided(){return courseProvided;}
    public String getShift(){return shift;}
    public String getCanteenFacility(){return canteenFacility;}
    public String getAdditionalInfo(){return additionalInfo;}
    public String getLatitude(){return latitude;}
    public String getLongitude(){return longitude;}


    public static EducationServiceProviderItem parseEducationServiceProviderItem(JSONObject jo) throws JSONException {
        String _identifierId=jo.getString("identifier_id");
        String _serviceProviderId=jo.getString("serviceprovider_id");
        int _eduSubCategoryId=jo.getInt("edu_subcategory_id");
        int _categoryId=jo.getInt("category_id");
        String _eduNameEng=jo.getString("edu_name_eng");
        String _eduNameBan=jo.getString("edu_name_ban");
        String _eduType=jo.getString("edtype");
        String _hostelFacility=jo.getString("hostel_facility");
        String _transportFacility=jo.getString("transport_facility");
        String _playground=jo.getString("playground");
        String _contactPersonDesignation=jo.getString("contact_person_designation");
        String _contactNo=jo.getString("contact_no");
        String _emailAddress=jo.getString("email_address");
        String _websiteLink=jo.getString("website_link");
        String _fbLink=jo.getString("fb_link");
        String _registeredWith=jo.getString("registered_with");
        String _registrationNo=jo.getString("registration_no");
        int _totalStudents=jo.getInt("total_students");
        int _totalClasses=jo.getInt("total_classes");
        int _totalTeachers=jo.getInt("total_teachers");
        String _courseProvided=jo.getString("course_provided");
        String _shift=jo.getString("shift");
        String _canteenFacility=jo.getString("canteen_facility");
        String _additionalInfo=jo.getString("additional_info");
        String _area=jo.getString("area");
        String _address=jo.getString("address");
        String _latitude = jo.getString("latitude");
        String _longitude = jo.getString("longitude");
        String _openingtime=jo.getString("opening_time");
        String _breaktime=jo.getString("break_time");
        String _closingtime=jo.getString("closing_time");
        String _road=jo.getString("road");
        String _block=jo.getString("block");
        String _landmark = jo.getString("landmark");
        String _breaktime2 = jo.getString("breaktime2");
        String _additionaltime= jo.getString("additional_time");

        return new EducationServiceProviderItem( _identifierId,
                _serviceProviderId,
                _eduSubCategoryId,
                _categoryId,
                _eduNameEng,
                _eduNameBan,
                _eduType,
                _hostelFacility,
                _transportFacility,
                _playground,
                _contactPersonDesignation,
                _contactNo,
                _emailAddress,
                _websiteLink,
                _fbLink,
                _registeredWith,
                _registrationNo,
                _totalStudents,
                _totalClasses,
                _totalTeachers,
                _courseProvided,
                _shift,
                _canteenFacility,
                _additionalInfo,_area,_address,
                _latitude,
                _longitude,_openingtime,
                _breaktime,_closingtime,_road,_block,_landmark,_breaktime2,_additionaltime);
    }

}
