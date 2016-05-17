package demo.kolorob.kolorobdemoversion.model.Job;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Mazharul.Islam1 on 5/17/2016.
 */
public class JobAdvertisementItem implements Serializable {
    private int id;
    private String form_date;
    private int position;
    private int institute_name;
    private String institute_name_bangla;
    private String post_date;
    private String application_last_date;
    private String location;
    private String post_type;
    private String job_responsibility;
    private String required_experience;
    private String start_salary;
    private String end_salary;
    private String other_benefits;
    private String reference_person;
    private String application_medium;
    private String map_type;
    private String latitude;
    private String longitude;
    private String address_floor;
    private String address_house_name;
    private String address_house_num;
    private String address_road;
    private String address_line;
    private String address_avenue;
    private String address_block;
    private String address_area;
    private String address_post;
    private String address_police;
    private String address_city;
    private String address_country;
    private String mobile1;
    private String mobile2;
    private String email;
    private String website;
    private String facebook;
    private String contact_designation;
    private String opening;
    private String breaks;
    private String closing;
    private String off_day;
    private String remarks;
    private String ref1;
    private String ref2;
    private String ref3;
    private String reg_with;
    private String reg_num;
    private String job_type;
    private String collector_name;
    private String status;
    private String user;
    private String Subcategory;
    private String TypeSubcategory;


    public JobAdvertisementItem(int id, String form_date, int position, int institute_name, String institute_name_bangla,
                                String post_date, String application_last_date, String location,
                                String post_type, String job_responsibility, String required_experience,
                                String start_salary, String end_salary, String other_benefits, String reference_person, String application_medium,
                                String map_type, String latitude, String longitude, String address_floor,
                                String address_house_name, String address_house_num, String address_road, String address_line,
                                String address_avenue, String address_block, String address_area,
                                String address_post, String address_police, String address_city,
                                String mobile1, String mobile2, String email, String website,
                                String facebook, String contact_designation, String opening,
                                String breaks, String closing, String off_day, String remarks,
                                String ref1, String ref2, String ref3, String reg_with, String reg_num, String job_type,
                                String collector_name, String status, String user, String Subcategory, String TypeSubcategory
    ) {
        this.id=id;
        this.form_date = form_date;
        this.position = position;
        this.institute_name = institute_name;
        this.institute_name_bangla = institute_name_bangla;
        this.post_date = post_date;
        this.application_last_date = application_last_date;
        this.location = location;
        this.post_type = post_type;
        this.job_responsibility = job_responsibility;
        this.required_experience = required_experience;
        this.start_salary = start_salary;
        this.end_salary = end_salary;
        this.other_benefits = other_benefits;
        this.reference_person = reference_person;
        this.application_medium = application_medium;
        this.map_type = map_type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address_floor = address_floor;
        this.address_house_name = address_house_name;
        this.address_house_num = address_house_num;
        this.address_road = address_road;
        this.address_line = address_line;
        this.address_avenue = address_avenue;
        this.address_block = address_block;
        this.address_area = address_area;
        this.address_post = address_post;
        this.address_police = address_police;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.email = email;
        this.website = website;
        this.facebook = facebook;
        this.contact_designation = contact_designation;
        this.opening = opening;
        this.breaks = breaks;
        this.closing = closing;
        this.off_day = off_day;
        this.remarks = remarks;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.reg_with = reg_with;
        this.reg_num = reg_num;
        this.job_type = job_type;
        this.collector_name = collector_name;
        this.status = status;
        this.user = user;
        this.Subcategory = Subcategory;
        this.TypeSubcategory = TypeSubcategory;

    }

    public String getSubcategory() {
        return Subcategory;
    }

    public void setSubcategory(String subcategory) {
        Subcategory = subcategory;
    }

    public String getTypeSubcategory() {
        return TypeSubcategory;
    }

    public void setTypeSubcategory(String typeSubcategory) {
        TypeSubcategory = typeSubcategory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCollector_name() {
        return collector_name;
    }

    public void setCollector_name(String collector_name) {
        this.collector_name = collector_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getReg_num() {
        return reg_num;
    }

    public void setReg_num(String reg_num) {
        this.reg_num = reg_num;
    }

    public String getReg_with() {
        return reg_with;
    }

    public void setReg_with(String reg_with) {
        this.reg_with = reg_with;
    }

    public String getRef3() {
        return ref3;
    }

    public void setRef3(String ref3) {
        this.ref3 = ref3;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBreaks() {
        return breaks;
    }

    public void setBreaks(String breaks) {
        this.breaks = breaks;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String getOff_day() {
        return off_day;
    }

    public void setOff_day(String off_day) {
        this.off_day = off_day;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getAddress_block() {
        return address_block;
    }

    public void setAddress_block(String address_block) {
        this.address_block = address_block;
    }

    public String getAddress_police() {
        return address_police;
    }

    public void setAddress_police(String address_police) {
        this.address_police = address_police;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getContact_designation() {
        return contact_designation;
    }

    public void setContact_designation(String contact_designation) {
        this.contact_designation = contact_designation;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForm_date() {
        return form_date;
    }

    public void setForm_date(String form_date) {
        this.form_date = form_date;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(int institute_name) {
        this.institute_name = institute_name;
    }

    public String getInstitute_name_bangla() {
        return institute_name_bangla;
    }

    public void setInstitute_name_bangla(String institute_name_bangla) {
        this.institute_name_bangla = institute_name_bangla;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getApplication_last_date() {
        return application_last_date;
    }

    public void setApplication_last_date(String application_last_date) {
        this.application_last_date = application_last_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getJob_responsibility() {
        return job_responsibility;
    }

    public void setJob_responsibility(String job_responsibility) {
        this.job_responsibility = job_responsibility;
    }

    public String getRequired_experience() {
        return required_experience;
    }

    public void setRequired_experience(String required_experience) {
        this.required_experience = required_experience;
    }

    public String getStart_salary() {
        return start_salary;
    }

    public void setStart_salary(String start_salary) {
        this.start_salary = start_salary;
    }

    public String getEnd_salary() {
        return end_salary;
    }

    public void setEnd_salary(String end_salary) {
        this.end_salary = end_salary;
    }

    public String getOther_benefits() {
        return other_benefits;
    }

    public void setOther_benefits(String other_benefits) {
        this.other_benefits = other_benefits;
    }

    public String getReference_person() {
        return reference_person;
    }

    public void setReference_person(String reference_person) {
        this.reference_person = reference_person;
    }

    public String getApplication_medium() {
        return application_medium;
    }

    public void setApplication_medium(String application_medium) {
        this.application_medium = application_medium;
    }

    public String getMap_type() {
        return map_type;
    }

    public void setMap_type(String map_type) {
        this.map_type = map_type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress_floor() {
        return address_floor;
    }

    public void setAddress_floor(String address_floor) {
        this.address_floor = address_floor;
    }

    public String getAddress_house_name() {
        return address_house_name;
    }

    public void setAddress_house_name(String address_house_name) {
        this.address_house_name = address_house_name;
    }

    public String getAddress_house_num() {
        return address_house_num;
    }

    public void setAddress_house_num(String address_house_num) {
        this.address_house_num = address_house_num;
    }

    public String getAddress_road() {
        return address_road;
    }

    public void setAddress_road(String address_road) {
        this.address_road = address_road;
    }

    public String getAddress_line() {
        return address_line;
    }

    public void setAddress_line(String address_line) {
        this.address_line = address_line;
    }

    public String getAddress_avenue() {
        return address_avenue;
    }

    public void setAddress_avenue(String address_avenue) {
        this.address_avenue = address_avenue;
    }

    public String getAddress_area() {
        return address_area;
    }

    public void setAddress_area(String address_area) {
        this.address_area = address_area;
    }

    public String getAddress_post() {
        return address_post;
    }

    public void setAddress_post(String address_post) {
        this.address_post = address_post;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_country() {
        return address_country;
    }

    public void setAddress_country(String address_country) {
        this.address_country = address_country;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public static JobServiceProviderItem parseJobServiceProviderItem(JSONObject jo) throws JSONException {
        String _identifierId = jo.getString("identifier_id");
        String _serviceProviderId = jo.getString("serviceprovider_id");
        int _jobSubCategoryId = jo.getInt("job_subcategory_id");
        int _categoryId = jo.getInt("category_id");

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



        return new JobServiceProviderItem(_identifierId, _serviceProviderId, _jobSubCategoryId,
                _categoryId, _contactPersonDesignation, _contactNo, _emailAddress, _websiteLink,
                _fbLink, _registeredWith, _registrationNo, _additionalInfo, _area, _address, _latitude, _longitude);

    }
}

