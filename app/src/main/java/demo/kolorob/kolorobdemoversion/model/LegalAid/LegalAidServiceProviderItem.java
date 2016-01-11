package demo.kolorob.kolorobdemoversion.model.LegalAid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 1/7/2016.
 */
public class LegalAidServiceProviderItem implements Serializable {
    private String identifierId;
    private String serviceProviderId;
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

    public LegalAidServiceProviderItem( String identifierId, String serviceProviderId,int legalaidSubCategoryId, int categoryId, String legalaidNameEng, String legalaidNameBan, String contactPersonDesignation, String contactNo, String emailAddress, String websiteLink, String fbLink, String registeredWith, String registrationNo, String additionalInfo, String area, String address, String latitude, String longitude) {

        this.identifierId = identifierId;
        this.serviceProviderId = serviceProviderId;
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
    }



    public String getIdentifierId() {
        return identifierId;
    }

    public String getServiceProviderId() {
        return serviceProviderId;
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
    public static LegalAidServiceProviderItem parseLegalAidServiceProviderItem(JSONObject jo) throws JSONException {
        String _identifierId = jo.getString("identifier_id");
        String _serviceProviderId = jo.getString("serviceprovider_id");
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

        return new LegalAidServiceProviderItem(_identifierId, _serviceProviderId, _legalaidSubCategoryId,
                _categoryId,_legalaidNameEng, _legalaidBan, _contactPersonDesignation, _contactNo, _emailAddress, _websiteLink,
                _fbLink, _registeredWith, _registrationNo, _additionalInfo, _area, _address, _latitude, _longitude);

    }
}
