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
    String rating;



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
                    String references,String rating){

        this.id=id;
        this.node_name=node_name;
        this.node_bn=node_bn;
        this.institute_type = institute_type;
        this.spoken_lang=spoken_lang;
        this.capacity=capacity;
        this.male_doctors=male_doctors;
        this.female_doctors=female_doctors;
        this.patient_doctor_ratio=patient_doctor_ratio;
        this.male_nurse=male_nurse;
        this.female_nurse=female_nurse;
        this.patient_nurse_ratio=patient_nurse_ratio;
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
        this.doctor_available=doctor_available;
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
        this.rating=rating;




    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getNode_bn() {
        return node_bn;
    }

    public void setNode_bn(String node_bn) {
        this.node_bn = node_bn;
    }

    public String getInstitute_type() {
        return institute_type;
    }

    public void setInstitute_type(String institute_type) {
        this.institute_type = institute_type;
    }

    public String getSpoken_lang() {
        return spoken_lang;
    }

    public void setSpoken_lang(String spoken_lang) {
        this.spoken_lang = spoken_lang;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getMale_doctors() {
        return male_doctors;
    }

    public void setMale_doctors(String male_doctors) {
        this.male_doctors = male_doctors;
    }

    public String getFemale_doctors() {
        return female_doctors;
    }

    public void setFemale_doctors(String female_doctors) {
        this.female_doctors = female_doctors;
    }

    public String getPatient_doctor_ratio() {
        return patient_doctor_ratio;
    }

    public void setPatient_doctor_ratio(String patient_doctor_ratio) {
        this.patient_doctor_ratio = patient_doctor_ratio;
    }

    public String getMale_nurse() {
        return male_nurse;
    }

    public void setMale_nurse(String male_nurse) {
        this.male_nurse = male_nurse;
    }

    public String getFemale_nurse() {
        return female_nurse;
    }

    public void setFemale_nurse(String female_nurse) {
        this.female_nurse = female_nurse;
    }

    public String getPatient_nurse_ratio() {
        return patient_nurse_ratio;
    }

    public void setPatient_nurse_ratio(String patient_nurse_ratio) {
        this.patient_nurse_ratio = patient_nurse_ratio;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getHouse_no() {
        return house_no;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPost_office() {
        return post_office;
    }

    public void setPost_office(String post_office) {
        this.post_office = post_office;
    }

    public String getPolice_station() {
        return police_station;
    }

    public void setPolice_station(String police_station) {
        this.police_station = police_station;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNode_contact() {
        return node_contact;
    }

    public void setNode_contact(String node_contact) {
        this.node_contact = node_contact;
    }

    public String getNode_contact2() {
        return node_contact2;
    }

    public void setNode_contact2(String node_contact2) {
        this.node_contact2 = node_contact2;
    }

    public String getNode_website() {
        return node_website;
    }

    public void setNode_website(String node_website) {
        this.node_website = node_website;
    }

    public String getNode_facebook() {
        return node_facebook;
    }

    public void setNode_facebook(String node_facebook) {
        this.node_facebook = node_facebook;
    }

    public String getNode_designation() {
        return node_designation;
    }

    public void setNode_designation(String node_designation) {
        this.node_designation = node_designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    public String getBreak_time() {
        return break_time;
    }

    public void setBreak_time(String break_time) {
        this.break_time = break_time;
    }

    public String getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }

    public String getOff_day() {
        return off_day;
    }

    public void setOff_day(String off_day) {
        this.off_day = off_day;
    }

    public String getNode_registered_with() {
        return node_registered_with;
    }

    public void setNode_registered_with(String node_registered_with) {
        this.node_registered_with = node_registered_with;
    }

    public String getNode_registered_number() {
        return node_registered_number;
    }

    public void setNode_registered_number(String node_registered_number) {
        this.node_registered_number = node_registered_number;
    }

    public String getUpdated_drugs() {
        return updated_drugs;
    }

    public void setUpdated_drugs(String updated_drugs) {
        this.updated_drugs = updated_drugs;
    }

    public String getNum_trained_pharmacist() {
        return num_trained_pharmacist;
    }

    public void setNum_trained_pharmacist(String num_trained_pharmacist) {
        this.num_trained_pharmacist = num_trained_pharmacist;
    }

    public String getDoctor_available() {
        return doctor_available;
    }

    public void setDoctor_available(String doctor_available) {
        this.doctor_available = doctor_available;
    }

    public String getPharmacy_speciality() {
        return pharmacy_speciality;
    }

    public void setPharmacy_speciality(String pharmacy_speciality) {
        this.pharmacy_speciality = pharmacy_speciality;
    }

    public String getPharmacy_free() {
        return pharmacy_free;
    }

    public void setPharmacy_free(String pharmacy_free) {
        this.pharmacy_free = pharmacy_free;
    }

    public String getFree_service_for() {
        return free_service_for;
    }

    public void setFree_service_for(String free_service_for) {
        this.free_service_for = free_service_for;
    }

    public String getPharmacy_free_service() {
        return pharmacy_free_service;
    }

    public void setPharmacy_free_service(String pharmacy_free_service) {
        this.pharmacy_free_service = pharmacy_free_service;
    }

    public String getPharmacy_fee() {
        return pharmacy_fee;
    }

    public void setPharmacy_fee(String pharmacy_fee) {
        this.pharmacy_fee = pharmacy_fee;
    }

    public String getPharmacy_remarks() {
        return pharmacy_remarks;
    }

    public void setPharmacy_remarks(String pharmacy_remarks) {
        this.pharmacy_remarks = pharmacy_remarks;
    }

    public String getPharmacy_privacy() {
        return pharmacy_privacy;
    }

    public void setPharmacy_privacy(String pharmacy_privacy) {
        this.pharmacy_privacy = pharmacy_privacy;
    }

    public String getQuality_equipments() {
        return quality_equipments;
    }

    public void setQuality_equipments(String quality_equipments) {
        this.quality_equipments = quality_equipments;
    }

    public String getGeneral_free() {
        return general_free;
    }

    public void setGeneral_free(String general_free) {
        this.general_free = general_free;
    }

    public String getGeneral_free_for() {
        return general_free_for;
    }

    public void setGeneral_free_for(String general_free_for) {
        this.general_free_for = general_free_for;
    }

    public String getGeneral_free_services() {
        return general_free_services;
    }

    public void setGeneral_free_services(String general_free_services) {
        this.general_free_services = general_free_services;
    }

    public String getGeneral_cost() {
        return general_cost;
    }

    public void setGeneral_cost(String general_cost) {
        this.general_cost = general_cost;
    }

    public String getGeneral_remark() {
        return general_remark;
    }

    public void setGeneral_remark(String general_remark) {
        this.general_remark = general_remark;
    }

    public String getEmergency_free() {
        return emergency_free;
    }

    public void setEmergency_free(String emergency_free) {
        this.emergency_free = emergency_free;
    }

    public String getEmergency_free_for() {
        return emergency_free_for;
    }

    public void setEmergency_free_for(String emergency_free_for) {
        this.emergency_free_for = emergency_free_for;
    }

    public String getEmergency_free_services() {
        return emergency_free_services;
    }

    public void setEmergency_free_services(String emergency_free_services) {
        this.emergency_free_services = emergency_free_services;
    }

    public String getEmergency_cost() {
        return emergency_cost;
    }

    public void setEmergency_cost(String emergency_cost) {
        this.emergency_cost = emergency_cost;
    }

    public String getEmergency_remark() {
        return emergency_remark;
    }

    public void setEmergency_remark(String emergency_remark) {
        this.emergency_remark = emergency_remark;
    }

    public String getAmbulance_free() {
        return ambulance_free;
    }

    public void setAmbulance_free(String ambulance_free) {
        this.ambulance_free = ambulance_free;
    }

    public String getAmbulance_free_for() {
        return ambulance_free_for;
    }

    public void setAmbulance_free_for(String ambulance_free_for) {
        this.ambulance_free_for = ambulance_free_for;
    }

    public String getAmbulance_free_services() {
        return ambulance_free_services;
    }

    public void setAmbulance_free_services(String ambulance_free_services) {
        this.ambulance_free_services = ambulance_free_services;
    }

    public String getAmbulance_cost() {
        return ambulance_cost;
    }

    public void setAmbulance_cost(String ambulance_cost) {
        this.ambulance_cost = ambulance_cost;
    }

    public String getAmbulance_remark() {
        return ambulance_remark;
    }

    public void setAmbulance_remark(String ambulance_remark) {
        this.ambulance_remark = ambulance_remark;
    }

    public String getMaternity_free() {
        return maternity_free;
    }

    public void setMaternity_free(String maternity_free) {
        this.maternity_free = maternity_free;
    }

    public String getMaternity_free_for() {
        return maternity_free_for;
    }

    public void setMaternity_free_for(String maternity_free_for) {
        this.maternity_free_for = maternity_free_for;
    }

    public String getMaternity_free_services() {
        return maternity_free_services;
    }

    public void setMaternity_free_services(String maternity_free_services) {
        this.maternity_free_services = maternity_free_services;
    }

    public String getMaternity_cost() {
        return maternity_cost;
    }

    public void setMaternity_cost(String maternity_cost) {
        this.maternity_cost = maternity_cost;
    }

    public String getMaternity_remark() {
        return maternity_remark;
    }

    public void setMaternity_remark(String maternity_remark) {
        this.maternity_remark = maternity_remark;
    }

    public String getMaternity_complication() {
        return maternity_complication;
    }

    public void setMaternity_complication(String maternity_complication) {
        this.maternity_complication = maternity_complication;
    }

    public String getMaternity_privacy() {
        return maternity_privacy;
    }

    public void setMaternity_privacy(String maternity_privacy) {
        this.maternity_privacy = maternity_privacy;
    }

    public String getMaternity_newborn_care() {
        return maternity_newborn_care;
    }

    public void setMaternity_newborn_care(String maternity_newborn_care) {
        this.maternity_newborn_care = maternity_newborn_care;
    }

    public String getFamily_services() {
        return family_services;
    }

    public void setFamily_services(String family_services) {
        this.family_services = family_services;
    }

    public String getFamily_contraceptive_available() {
        return family_contraceptive_available;
    }

    public void setFamily_contraceptive_available(String family_contraceptive_available) {
        this.family_contraceptive_available = family_contraceptive_available;
    }

    public String getFamily_contraceptive() {
        return family_contraceptive;
    }

    public void setFamily_contraceptive(String family_contraceptive) {
        this.family_contraceptive = family_contraceptive;
    }

    public String getFamily_privacy() {
        return family_privacy;
    }

    public void setFamily_privacy(String family_privacy) {
        this.family_privacy = family_privacy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public static HealthServiceProviderItemNew parseHealthServiceProviderItem(JSONObject jo) throws JSONException{

        String  id,
                node_name,
                node_bn,
                institute_type,
                spoken_lang,
                capacity,
                male_doctors,
                female_doctors,
                patient_doctor_ratio,
                male_nurse,
                female_nurse,
                patient_nurse_ratio,
                lon="",
                lat="",
                node_id="",
                floor="",
                house_name="",
                house_no="",
                road="",
                line="",
                avenue="",
                block="",
                area="",
                landmark="",
                post_office="",
                police_station="",
                city="",
                country="",
                node_contact="",
                node_contact2="",
                node_website="",
                node_facebook="",
                node_designation="",
                address="",
                opening_time="",
                break_time="",
                closing_time="",
                off_day="",
                node_registered_with="",
                node_registered_number="",
                updated_drugs="",
                num_trained_pharmacist="",
                doctor_available="",
                pharmacy_speciality="",
                pharmacy_free="",
                free_service_for="",
                pharmacy_free_service="",
                pharmacy_fee="",
                pharmacy_remarks="",
                pharmacy_privacy="",
                quality_equipments="",
                general_free="",
                general_free_for="",
                general_free_services="",
                general_cost="",
                general_remark="",
                emergency_free="",
                emergency_free_for="",
                emergency_free_services="",
                emergency_cost="",
                emergency_remark="",
                ambulance_free="",
                ambulance_free_for="",
                ambulance_free_services="",
                ambulance_cost="",
                ambulance_remark="",
                maternity_free="",
                maternity_free_for="",
                maternity_free_services="",
                maternity_cost="",
                maternity_remark="",
                maternity_complication="",
                maternity_privacy="",
                maternity_newborn_care="",
                family_services="",
                family_contraceptive_available="",
                family_contraceptive="",
                family_privacy="",
                category,
                references,
                checker="";

        id=jo.getString("id");
        node_name= jo.getString("node_name");
        node_bn=jo.getString("node_bn");
        institute_type=jo.getString("institute_type");
        spoken_lang= jo.getString("spoken_lang");
        capacity=jo.getString("capacity");
        male_doctors=jo.getString("male_doctors");
        female_doctors= jo.getString("female_doctors");
        patient_doctor_ratio=jo.getString("patient_doctor_ratio");
        male_nurse=jo.getString("male_nurse");
        female_nurse= jo.getString("female_nurse");
        patient_nurse_ratio=jo.getString("patient_nurse_ratio");



        if(jo.has("map_info")) {
            JSONObject map_info = jo.getJSONObject("map_info");
            lat = map_info.getString("lat");
            lon = map_info.getString("lon");
            node_id = map_info.getString("node_id");

        }

        if(jo.has("contact_info")) {
            JSONObject contact_info= jo.getJSONObject("contact_info");
            floor = contact_info.getString("floor");
            house_name=contact_info.getString("house_name");
            house_no= contact_info.getString("house_no");
            road = contact_info.getString("road");
            line = contact_info.getString("line");
            avenue = contact_info.getString("avenue");
            block= contact_info.getString("block");
            area= contact_info.getString("area");
            landmark= contact_info.getString("landmark");
            post_office=contact_info.getString("post_office");
            police_station=contact_info.getString("police_station");
            city=contact_info.getString("city");
            country=contact_info.getString("country");
            node_contact=contact_info.getString("node_contact");
            node_contact2=contact_info.getString("node_email");
            node_website = contact_info.getString("node_website");
            node_facebook=contact_info.getString("node_facebook");
            node_designation=contact_info.getString("node_designation");
            address=contact_info.getString("address");
        }





        if(jo.has("timing_info")) {
            JSONObject timing_info= jo.getJSONObject("timing_info");
            opening_time = timing_info.getString("opening_time");
            break_time = timing_info.getString("break_time");
            closing_time = timing_info.getString("closing_time");
            off_day = timing_info.getString("off_day");
        }

        if (jo.has("registration_info"))
        {
            JSONObject registration_info= jo.getJSONObject("registration_info");
            node_registered_with=registration_info.getString("node_registered_with");
            node_registered_number=registration_info.getString("node_registered_number");
        }

        //Log.d("registration_info","======");


        if (jo.has("health_pharmacy_info"))
        {
            JSONObject health_pharmacy_info= jo.getJSONObject("health_pharmacy_info");
            updated_drugs=health_pharmacy_info.getString("updated_drugs");
            num_trained_pharmacist= health_pharmacy_info.getString("num_trained_pharmacist");
            doctor_available= health_pharmacy_info.getString("doctor_available");
            pharmacy_speciality=health_pharmacy_info.getString("pharmacy_speciality");
            pharmacy_free=health_pharmacy_info.getString("pharmacy_free");
            free_service_for= health_pharmacy_info.getString("free_service_for");
            pharmacy_free_service = health_pharmacy_info.getString("pharmacy_free_service");
            pharmacy_fee=health_pharmacy_info.getString("pharmacy_fee");
            pharmacy_remarks= health_pharmacy_info.getString("pharmacy_remarks");
            pharmacy_privacy = health_pharmacy_info.getString("pharmacy_privacy");
            quality_equipments = health_pharmacy_info.getString("quality_equipments");

        }

        if (jo.has("health_general_info"))
        {
            JSONObject health_general_info= jo.getJSONObject("health_general_info");
            general_free = health_general_info.getString("general_free");
            general_free_for= health_general_info.getString("general_free_for");
            general_free_services= health_general_info.getString("general_free_services");
            general_cost=health_general_info.getString("general_cost");
            general_remark=health_general_info.getString("general_remark");
        }


        if (jo.has("health_emergency_info"))
        {
            checker=checker+"1";
            JSONObject health_emergency_info= jo.getJSONObject("health_emergency_info");
            emergency_free=health_emergency_info.getString("emergency_free");
            emergency_free_for=health_emergency_info.getString("emergency_free_for");
            emergency_free_services=health_emergency_info.getString("emergency_free_services");
            emergency_cost= health_emergency_info.getString("emergency_cost");
            emergency_remark=health_emergency_info.getString("emergency_remark");
        }



        if (jo.has("health_ambulance_info"))
        {
            checker=checker+"2";
            JSONObject health_ambulance_info= jo.getJSONObject("health_ambulance_info");
            ambulance_free=health_ambulance_info.getString("ambulance_free");

            ambulance_free_for = health_ambulance_info.getString("ambulance_free_for");
            ambulance_free_services=health_ambulance_info.getString("ambulance_free_services");
            ambulance_cost=health_ambulance_info.getString("ambulance_cost");
            ambulance_remark=health_ambulance_info.getString("ambulance_remark");
        }


        if (jo.has("health_maternity_info"))
        {
            checker=checker+"3";
            JSONObject health_maternity_info= jo.getJSONObject("health_maternity_info");
            maternity_free=health_maternity_info.getString("maternity_free");
            maternity_free_for=health_maternity_info.getString("maternity_free_for");
            maternity_free_services=health_maternity_info.getString("maternity_free_services");
            maternity_cost =health_maternity_info.getString("maternity_cost");
            maternity_remark=health_maternity_info.getString("maternity_remark");
            maternity_complication=health_maternity_info.getString("maternity_complication");
            maternity_privacy=health_maternity_info.getString("maternity_privacy");
            maternity_newborn_care=health_maternity_info.getString("maternity_newborn_care");
        }



        if (jo.has("health_fp_info"))
        {
            JSONObject health_fp_info= jo.getJSONObject("health_fp_info");
            family_services =health_fp_info.getString("family_services");
            family_contraceptive_available=health_fp_info.getString("family_contraceptive_available");
            family_contraceptive=health_fp_info.getString("family_contraceptive");
            family_privacy=health_fp_info.getString("family_privacy");
        }
        family_privacy=checker;



        category=jo.getString("sub_categories");
        category=category.replace('[',',');
        category=category.replace(']',',');
        references=jo.getString("references");
        references=references.replace('[',',');
        references=references.replace(']',',');


        //   references=jo.getString("references");

      //  Log.d("references","======"+references.length());


String _rating=jo.getString("rating");

        return new HealthServiceProviderItemNew(
                id,
                node_name,
                node_bn,
                institute_type,
                spoken_lang,
                capacity,
                male_doctors,
                female_doctors,
                patient_doctor_ratio,
                male_nurse,
                female_nurse,
                patient_nurse_ratio,
                lon,
                lat,
                node_id,
                floor,
                house_name,
                house_no,
                road,
                line,
                avenue,
                block,
                area,
                landmark,
                post_office,
                police_station,
                city,
                country,
                node_contact,
                node_contact2,
                node_website,
                node_facebook,
                node_designation,
                address,
                opening_time,
                break_time,
                closing_time,
                off_day,
                node_registered_with,
                node_registered_number,
                updated_drugs,
                num_trained_pharmacist,
                doctor_available,
                pharmacy_speciality,
                pharmacy_free,
                free_service_for,
                pharmacy_free_service,
                pharmacy_fee,
                pharmacy_remarks,
                pharmacy_privacy,
                quality_equipments,
                general_free,
                general_free_for,
                general_free_services,
                general_cost,
                general_remark,
                emergency_free,
                emergency_free_for,
                emergency_free_services,
                emergency_cost,
                emergency_remark,
                ambulance_free,
                ambulance_free_for,
                ambulance_free_services,
                ambulance_cost,
                ambulance_remark,
                maternity_free,
                maternity_free_for,
                maternity_free_services,
                maternity_cost,
                maternity_remark,
                maternity_complication,
                maternity_privacy,
                maternity_newborn_care,
                family_services,
                family_contraceptive_available,
                family_contraceptive,
                family_privacy,
                category,
                references,_rating);





    }
}