package demo.kolorob.kolorobdemoversion.model.Education;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 6/26/2016.
 */
public class EducationNewItem implements Serializable {
    int eduId;
    String nameen;
    String namebn;
    String edtype;
    String shift;
    String studentno;
    String teachersno;
    String classno;
    String additional;
    String malestudent;
    String femalestudent;
    String specialneeds;
    String washroom_no;
    String washroom_male;
    String washroomcleanliness;
    String watercondition;
    String watersource;
    String averagestudent;
    String washroomfemale;
    String lat;
    String lon;
    String floor;
    String housename;
    String houseno;
    String road;
    String line;
    String avenue;
    String block;
    String area;
    String landmark;
    String postoffice;
    String policestation;
    String city;
    String country;
    String node_contact;
    String node_contact2;
    String node_email;
    String node_website;
    String node_facebook;
    String node_designation;
    String address;
    String openingtime;
    String closetime;
    String breaktime;
    String offday;
    String registeredwith;
    String registerednumber;
    int categoryId;

    String refnumm;
    String rating;
    String subref;

    public EducationNewItem(int eduId, String nameen, String namebn, String edtype, String shift, String studentno,
                            String teachersno, String classno, String additional, String malestudent, String femalestudent,
                            String specialneeds, String washroom_no, String washroom_male, String washroomcleanliness,
                            String watercondition, String watersource, String averagestudent, String washroomfemale, String lat,
                            String lon, String floor, String housename, String houseno, String road, String line, String avenue,
                            String block, String area, String landmark, String postoffice, String policestation, String city,
                            String country, String node_contact, String node_contact2, String node_email, String node_website,
                            String node_facebook, String node_designation, String address, String openingtime,
                            String closetime, String breaktime, String offday, String registeredwith,
                            String registerednumber, int categoryId, String refnumm,String rating,String subref) {
        this.eduId = eduId;
        this.nameen = nameen;
        this.namebn = namebn;
        this.edtype = edtype;
        this.shift = shift;
        this.studentno = studentno;
        this.teachersno = teachersno;
        this.classno = classno;
        this.additional = additional;
        this.malestudent = malestudent;
        this.femalestudent = femalestudent;
        this.specialneeds = specialneeds;
        this.washroom_no = washroom_no;
        this.washroom_male = washroom_male;
        this.washroomcleanliness = washroomcleanliness;
        this.watercondition = watercondition;
        this.watersource = watersource;
        this.averagestudent = averagestudent;
        this.washroomfemale = washroomfemale;
        this.lat = lat;
        this.lon = lon;
        this.floor = floor;
        this.housename = housename;
        this.houseno = houseno;
        this.road = road;
        this.line = line;
        this.avenue = avenue;
        this.block = block;
        this.area = area;
        this.landmark = landmark;
        this.postoffice = postoffice;
        this.policestation = policestation;
        this.city = city;
        this.country = country;
        this.node_contact = node_contact;
        this.node_contact2 = node_contact2;
        this.node_email = node_email;
        this.node_website = node_website;
        this.node_facebook = node_facebook;
        this.node_designation = node_designation;
        this.address = address;
        this.openingtime = openingtime;
        this.closetime = closetime;
        this.breaktime = breaktime;
        this.offday = offday;
        this.registeredwith = registeredwith;
        this.registerednumber = registerednumber;
        this.categoryId = categoryId;
        this.refnumm = refnumm;
        this.rating=rating;
        this.subref=subref;
    }

    public int getEduId() {
        return eduId;
    }

    public void setEduId(int eduId) {
        this.eduId = eduId;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    public String getNamebn() {
        return namebn;
    }

    public String getWatersource() {
        return watersource;
    }

    public String getSubref() {
        return subref;
    }

    public void setSubref(String subref) {
        this.subref = subref;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWatersource(String watersource) {
        this.watersource = watersource;
    }

    public void setNamebn(String namebn) {
        this.namebn = namebn;
    }

    public String getEdtype() {
        return edtype;
    }

    public void setEdtype(String edtype) {
        this.edtype = edtype;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getTeachersno() {
        return teachersno;
    }

    public void setTeachersno(String teachersno) {
        this.teachersno = teachersno;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getMalestudent() {
        return malestudent;
    }

    public void setMalestudent(String malestudent) {
        this.malestudent = malestudent;
    }

    public String getFemalestudent() {
        return femalestudent;
    }

    public void setFemalestudent(String femalestudent) {
        this.femalestudent = femalestudent;
    }

    public String getSpecialneeds() {
        return specialneeds;
    }

    public void setSpecialneeds(String specialneeds) {
        this.specialneeds = specialneeds;
    }

    public String getWashroom_no() {
        return washroom_no;
    }

    public void setWashroom_no(String washroom_no) {
        this.washroom_no = washroom_no;
    }

    public String getWashroom_male() {
        return washroom_male;
    }

    public void setWashroom_male(String washroom_male) {
        this.washroom_male = washroom_male;
    }

    public String getWashroomcleanliness() {
        return washroomcleanliness;
    }

    public void setWashroomcleanliness(String washroomcleanliness) {
        this.washroomcleanliness = washroomcleanliness;
    }

    public String getWatercondition() {
        return watercondition;
    }

    public void setWatercondition(String watercondition) {
        this.watercondition = watercondition;
    }

    public String getAveragestudent() {
        return averagestudent;
    }

    public void setAveragestudent(String averagestudent) {
        this.averagestudent = averagestudent;
    }

    public String getWashroomfemale() {
        return washroomfemale;
    }

    public void setWashroomfemale(String washroomfemale) {
        this.washroomfemale = washroomfemale;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
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

    public String getPostoffice() {
        return postoffice;
    }

    public void setPostoffice(String postoffice) {
        this.postoffice = postoffice;
    }

    public String getPolicestation() {
        return policestation;
    }

    public void setPolicestation(String policestation) {
        this.policestation = policestation;
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

    public String getNode_email() {
        return node_email;
    }

    public void setNode_email(String node_email) {
        this.node_email = node_email;
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

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(String breaktime) {
        this.breaktime = breaktime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOffday() {
        return offday;
    }

    public void setOffday(String offday) {
        this.offday = offday;
    }

    public String getRegisteredwith() {
        return registeredwith;
    }

    public void setRegisteredwith(String registeredwith) {
        this.registeredwith = registeredwith;
    }

    public String getRegisterednumber() {
        return registerednumber;
    }

    public void setRegisterednumber(String registerednumber) {
        this.registerednumber = registerednumber;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRefnumm() {
        return refnumm;
    }

    public void setRefnumm(String refnumm) {
        this.refnumm = refnumm;
    }

    public static EducationNewItem parseEducationNewItem(JSONObject jo) throws JSONException {
        int _eduId = jo.getInt("id");
        String _namebn = jo.getString("node_bn");
        String _nameen = jo.getString("node_name");

        String _edtype = jo.getString("education_type");
        String _shift = jo.getString("shift");
        String _studentno = jo.getString("no_of_students");
        String _teachersno = jo.getString("no_of_teachers");
        String _classno = jo.getString("no_of_classes");
        String _additional = jo.getString("additional_data");
        String _malestudent = jo.getString("no_of_male_students");
        String _femalestudent = jo.getString("no_of_female_students");
        String _specialneeds = jo.getString("special_needs");
        String _washroom_no = jo.getString("washroom_no");
        String _washroom_male = jo.getString("washroom_male");
        String _washroomcleanliness = jo.getString("clean_washrooms");
        String _watercondition = jo.getString("clean_water");
        String _watersource = jo.getString("water_source");
        String _averagestudent = jo.getString("class_size");
        String _washroomfemale = jo.getString("washroom_female");



        String _lat = jo.getJSONObject("map_info").getString("lat");
        String _lon = jo.getJSONObject("map_info").getString("lon");
        String _floor = jo.getJSONObject("contact_info").getString("floor");
        String _housename = jo.getJSONObject("contact_info").getString("house_name");

        String _houseno = jo.getJSONObject("contact_info").getString("house_no");
        String _road = jo.getJSONObject("contact_info").getString("road");
        String _line = jo.getJSONObject("contact_info").getString("line");

        String _avenue = jo.getJSONObject("contact_info").getString("avenue");
        String _block = jo.getJSONObject("contact_info").getString("block");
        String _area = jo.getJSONObject("contact_info").getString("area");

        String _landmark = jo.getJSONObject("contact_info").getString("landmark");
        String _postoffice = jo.getJSONObject("contact_info").getString("post_office");
        String _policestation = jo.getJSONObject("contact_info").getString("police_station");

        String _city = jo.getJSONObject("contact_info").getString("city");
        String _country = jo.getJSONObject("contact_info").getString("country");
        String _node_contact = jo.getJSONObject("contact_info").getString("node_contact");

        String _node_contact2 = jo.getJSONObject("contact_info").getString("node_contact2");
        String _node_email = jo.getJSONObject("contact_info").getString("node_email");
        String _node_website = jo.getJSONObject("contact_info").getString("node_website");

        String _node_facebook=jo.getJSONObject("contact_info").getString("node_facebook");
        String _node_designation=jo.getJSONObject("contact_info").getString("node_designation");
        String _address=jo.getJSONObject("contact_info").getString("address");
        String _opentime = jo.getJSONObject("timing_info").getString("opening_time");
        String _breaktime = jo.getJSONObject("timing_info").getString("break_time");
        String _closetime = jo.getJSONObject("timing_info").getString("closing_time");
        String _offday = jo.getJSONObject("timing_info").getString("off_day");
        String _regwith = jo.getJSONObject("registration_info").getString("node_registered_with");

        String _regnum = jo.getJSONObject("registration_info").getString("node_registered_number");
        int _catid=jo.getInt("category");
        JSONArray jr=jo.getJSONArray("references");

        String k=jr.toString();
        String _refnumm=k.substring(1,k.length()-1);
        String _rating=jo.getString("rating");
        JSONArray sref2=jo.getJSONArray("sub_categories");

        String ki=sref2.toString();
        String _sref=ki.substring(1,ki.length()-1);
        return new EducationNewItem(_eduId,_nameen,_namebn,_edtype,_shift,_studentno,_teachersno,_classno,_additional,_malestudent,
                _femalestudent,_specialneeds,_washroom_no,_washroom_male,_washroomcleanliness,_watercondition,_watersource,_averagestudent,_washroomfemale,_lat, _lon,_floor,_housename,_houseno,_road,_line,_avenue,_block,_area,_landmark,_postoffice,_policestation,
                _city,_country,_node_contact,_node_contact2,_node_email,_node_website,_node_facebook,_node_designation,_address,
                _opentime,
                _breaktime,_closetime,_offday,_regwith,
                _regnum,_catid,_refnumm,_rating,_sref);
    }
}
