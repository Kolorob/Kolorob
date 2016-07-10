package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;



/**
 * Created by Mazharul.Islam1 on 1/10/2016.
 */
public class HealthServiceProviderItemNew implements Serializable {

    private String id;
    private String node_name;
    private String node_bn;
    private String institute_type;
    private String spoken_lang;
    private String capacity;
    private String male_doctors;
    private String female_doctors;
    private String patient_doctor_ratio;
    private String male_nurse;
    private String female_nurse;
    private String patient_nurse_ratio;
    private String lon;
    private String lat;
    private String node_id;
    private String floor;
    private String house_name;
    private String house_no;
    private String road;
    private String line;
    private String avenue;
    private String block;
    private String area;
    private String landmark;
    private String post_office;
    private String police_station;
    private String city;
    private String country;
    private String node_contact;
    private String node_contact2;
    private String node_website;
    private String node_facebook;
    private String node_designation;
    private String address;
    private String opening_time;
    private String break_time;
    private String closing_time;
    private String off_day;
    private String node_registered_with;
    private String node_registered_number;
    private String updated_drugs;
    private String num_trained_pharmacist;
    private String doctor_available;
    private String pharmacy_speciality;
    private String pharmacy_free;
    private String free_service_for;
    private String pharmacy_free_service;
    private String pharmacy_fee;
    private String pharmacy_remarks;
    private String pharmacy_privacy;
    private String quality_equipments;
    private String general_free;
    private String general_free_for;
    private String general_free_services;
    private String general_cost;
    private String general_remark;
    private String emergency_free;
    private String emergency_free_for;
    private String emergency_free_services;
    private String emergency_cost;
    private String emergency_remark;
    private String ambulance_free;
    private String ambulance_free_for;
    private String ambulance_free_services;
    private String ambulance_cost;
    private String ambulance_remark;
    private String maternity_free;
    private String maternity_free_for;
    private String maternity_free_services;
    private String maternity_cost;
    private String maternity_remark;
    private String maternity_complication;
    private String maternity_privacy;
    private String maternity_newborn_care;
    private String family_services;
    private String family_contraceptive_available;
    private String family_contraceptive;
    private String family_privacy;
    private String category;
    private String references;


    public HealthServiceProviderItemNew
            (
                    String id,
                    String node_name,
                    String node_bn,
                    String institute_type,
                    String spoken_lang,
                    String capacity,
                    String male_doctors,
                    String female_doctors,
                    String patient_doctor_ratio,
                    String male_nurse,
                    String female_nurse,
                    String patient_nurse_ratio,
                    String lon,
                    String lat,
                    String node_id,
                    String floor,
                    String house_name,
                    String house_no,
                    String road,
                    String line,
                    String avenue,
                    String block,
                    String area,
                    String landmark,
                    String post_office,
                    String police_station,
                    String city,
                    String country,
                    String node_contact,
                    String node_contact2,
                    String node_website,
                    String node_facebook,
                    String node_designation,
                    String address,
                    String opening_time,
                    String break_time,
                    String closing_time,
                    String off_day,
                    String node_registered_with,
                    String node_registered_number,
                    String updated_drugs,
                    String num_trained_pharmacist,
                    String doctor_available,
                    String pharmacy_speciality,
                    String pharmacy_free,
                    String free_service_for,
                    String pharmacy_free_service,
                    String pharmacy_fee,
                    String pharmacy_remarks,
                    String pharmacy_privacy,
                    String quality_equipments,
                    String general_free,
                    String general_free_for,
                    String general_free_services,
                    String general_cost,
                    String general_remark,
                    String emergency_free,
                    String emergency_free_for,
                    String emergency_free_services,
                    String emergency_cost,
                    String emergency_remark,
                    String ambulance_free,
                    String ambulance_free_for,
                    String ambulance_free_services,
                    String ambulance_cost,
                    String ambulance_remark,
                    String maternity_free,
                    String maternity_free_for,
                    String maternity_free_services,
                    String maternity_cost,
                    String maternity_remark,
                    String maternity_complication,
                    String maternity_privacy,
                    String maternity_newborn_care,
                    String family_services,
                    String family_contraceptive_available,
                    String family_contraceptive,
                    String family_privacy,
                    String category,
                    String references){

        this.id=id;
        this.node_name=node_name;
        this.node_bn=node_bn;
        this.lon = lon;
        this.lat=lat;
        this.node_id=node_id;
        this.floor=floor;
        this.house_name=house_name;
        this.house_no=house_no;
        this.road=road;
        this.line=line;
        this.avenue=avenue;
        this.block=block;
        this.area=area;
        this.landmark=landmark;
        this.post_office=post_office;
        this.police_station=police_station;
        this.city=city;
        this.country=country;
        this.node_contact=node_contact;
        this.node_contact2=node_contact2;
        this.node_website = node_website;
        this.node_facebook = node_facebook;
        this.node_designation = node_designation;
        this.address = address;
        this.opening_time = opening_time;
        this.break_time = break_time;
        this.closing_time = closing_time;
        this.off_day = off_day;

        this.node_registered_with=node_registered_with;
        this.node_registered_number=node_registered_number;
        this.updated_drugs=updated_drugs;
        this.num_trained_pharmacist = num_trained_pharmacist;
        this.pharmacy_speciality=pharmacy_speciality;
        this.pharmacy_free=pharmacy_free;
        this.free_service_for=free_service_for;
        this.pharmacy_free_service=pharmacy_free_service;
        this.pharmacy_fee=pharmacy_fee;
        this.pharmacy_remarks=pharmacy_remarks;
        this.pharmacy_privacy=pharmacy_privacy;
        this.quality_equipments=quality_equipments;
        this.general_free=general_free;
        this.general_free_for=general_free_for;
        this.general_free_services=general_free_services;
        this.general_cost=general_cost;
        this.general_remark=general_remark;
        this.emergency_free=emergency_free;
        this.emergency_free_for=emergency_free_for;
        this.emergency_free_services=emergency_free_services;
        this.emergency_cost=emergency_cost;
        this.emergency_remark = emergency_remark;

        this.ambulance_free = ambulance_free;
        this.ambulance_free_for = ambulance_free_for;
        this.ambulance_free_services = ambulance_free_services;
        this.ambulance_cost = ambulance_cost;
        this.ambulance_remark = ambulance_remark;

        this.maternity_free = maternity_free;
        this.maternity_free_for = maternity_free_for;

        this.maternity_free_services=maternity_free_services;
        this.maternity_cost=maternity_cost;
        this.maternity_remark=maternity_remark;
        this.maternity_complication = maternity_complication;
        this.maternity_privacy=maternity_privacy;
        this.maternity_newborn_care=maternity_newborn_care;
        this.family_services=family_services;
        this.family_contraceptive_available=family_contraceptive_available;
        this.family_contraceptive=family_contraceptive;
        this.family_privacy=family_privacy;
        this.category=category;
        this.references=references;
        this.block=block;
        this.area=area;
        this.landmark=landmark;
        this.post_office=post_office;
        this.police_station=police_station;
        this.city=city;
        this.country=country;
        this.node_contact=node_contact;
        this.node_contact2=node_contact2;
        this.node_website = node_website;
        this.node_facebook = node_facebook;
        this.node_designation = node_designation;
        this.address = address;
        this.opening_time = opening_time;
        this.break_time = break_time;
        this.closing_time = closing_time;
        this.off_day = off_day;




    }




    public static HealthServiceProviderItemNew parseHealthServiceProviderItem(JSONObject jo) throws JSONException{

        String _nodeId=jo.getString("node_id");
        String _nodeName= jo.getString("node_name");
        String _dateName=jo.getString("data_name");
        String lat=jo.getString("lat");
        String node_id= jo.getString("node_id");
        String floor = jo.getString("floor");
        String house_name=jo.getString("house_name");
        String house_no= jo.getString("house_no");
        String road = jo.getString("road");
        String line = jo.getString("line");
        String avenue = jo.getString("avenue");
        String block= jo.getString("block");
        String area= jo.getString("area");
        int landmark= jo.getInt("landmark");
        String post_office=jo.getString("post_office");
        String police_station=jo.getString("police_station");
        String city=jo.getString("city");
        String country=jo.getString("country");
        String node_contact=jo.getString("node_contact");
        String node_contact2=jo.getString("node_email");
        String node_website = jo.getString("node_website");
        int node_facebook=jo.getInt("node_facebook");
        String node_designation=jo.getString("node_designation");
        String address=jo.getString("address");
        String opening_time=jo.getString("opening_time");
        String break_time=jo.getString("break_time");
        String closing_time=jo.getString("closing_time");
        String off_day =jo.getString("off_day");
        String node_registered_with=jo.getString("node_registered_with");
        String node_registered_number=jo.getString("node_registered_number");
        String updated_drugs=jo.getString("updated_drugs");
        String num_trained_pharmacist= jo.getString("num_trained_pharmacist");
        String pharmacy_speciality=jo.getString("pharmacy_speciality");
        String pharmacy_free=jo.getString("pharmacy_free");
        String free_service_for= jo.getString("free_service_for");
        String pharmacy_free_service = jo.getString("pharmacy_free_service");
        String pharmacy_fee=jo.getString("pharmacy_fee");
        String pharmacy_remarks= jo.getString("pharmacy_remarks");
        String pharmacy_privacy = jo.getString("pharmacy_privacy");
        String quality_equipments = jo.getString("quality_equipments");
        String general_free = jo.getString("general_free");
        String general_free_for= jo.getString("general_free_for");
        String general_free_services= jo.getString("general_free_services");
        String general_cost=jo.getString("general_cost");
        String general_remark=jo.getString("general_remark");

        String emergency_free=jo.getString("emergency_free");
        String emergency_free_for=jo.getString("emergency_free_for");
        String emergency_free_services=jo.getString("emergency_free_services");
        int emergency_cost= jo.getInt("emergency_cost");
        String emergency_remark=jo.getString("emergency_remark");

        String ambulance_free=jo.getString("ambulance_free");
        String ambulance_free_for = jo.getString("ambulance_free_for");
        int ambulance_free_services=jo.getInt("ambulance_free_services");
        String ambulance_cost=jo.getString("ambulance_cost");
        String ambulance_remark=jo.getString("ambulance_remark");

        String maternity_free=jo.getString("maternity_free");
        String maternity_free_for=jo.getString("maternity_free_for");
        String maternity_free_services=jo.getString("maternity_free_services");
        String maternity_cost =jo.getString("maternity_cost");
        String maternity_remark=jo.getString("maternity_remark");
        String maternity_complication=jo.getString("maternity_complication");

        String maternity_privacy=jo.getString("maternity_privacy");
        String maternity_newborn_care=jo.getString("maternity_newborn_care");
        String family_services =jo.getString("family_services");
        String family_contraceptive_available=jo.getString("family_contraceptive_available");
        String family_contraceptive=jo.getString("family_contraceptive");
        String family_privacy=jo.getString("family_privacy");
        String category=jo.getString("category");
        String references=jo.getString("references");


        return new HealthServiceProviderItemNew(
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