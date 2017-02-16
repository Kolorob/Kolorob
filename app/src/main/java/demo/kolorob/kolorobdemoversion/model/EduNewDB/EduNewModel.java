package demo.kolorob.kolorobdemoversion.model.EduNewDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 1/26/2017.
 */

public class EduNewModel implements Serializable {
    int eduId;
    String nameen;
    String namebn;
    String edtype;
    String shift;
    String studentno;
    String teachersno;
    String averagestdperclass;
    String facility;
    String lat;
    String lon;
    String houseno;
    String block;
    String area;
    String policestation;
    String node_email;
    String ward;
    String road;
    String node_contact;
    String otherinfo;

    String offday;
    String closetime;
    String openingtime;
    int categoryId;

    String refnumm;
    String subcat;
    String ratings;

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

    public String getAveragestdperclass() {
        return averagestdperclass;
    }

    public void setAveragestdperclass(String averagestdperclass) {
        this.averagestdperclass = averagestdperclass;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
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

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
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

    public String getPolicestation() {
        return policestation;
    }

    public void setPolicestation(String policestation) {
        this.policestation = policestation;
    }

    public String getNode_email() {
        return node_email;
    }

    public void setNode_email(String node_email) {
        this.node_email = node_email;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getNode_contact() {
        return node_contact;
    }

    public void setNode_contact(String node_contact) {
        this.node_contact = node_contact;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public String getOffday() {
        return offday;
    }

    public void setOffday(String offday) {
        this.offday = offday;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
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

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public EduNewModel(int eduId, String nameen, String namebn, String edtype, String shift, String studentno, String teachersno,
                       String averagestdperclass, String facility, String lat, String lon, String houseno, String block,
                       String area, String policestation, String node_email, String ward, String road, String node_contact,
                       String otherinfo, String openingtime , String closetime, String offday, int categoryId, String refnumm,
                       String subcat, String ratings) {
        this.eduId = eduId;
        this.nameen = nameen;
        this.namebn = namebn;
        this.edtype = edtype;
        this.shift = shift;
        this.studentno = studentno;
        this.teachersno = teachersno;
        this.averagestdperclass = averagestdperclass;
        this.facility = facility;
        this.lat = lat;
        this.lon = lon;
        this.houseno = houseno;
        this.block = block;
        this.area = area;
        this.policestation = policestation;
        this.node_email = node_email;
        this.ward = ward;
        this.road = road;
        this.node_contact = node_contact;
        this.otherinfo = otherinfo;
        this.offday = offday;
        this.closetime = closetime;
        this.openingtime = openingtime;
        this.categoryId = categoryId;
        this.refnumm = refnumm;
        this.subcat = subcat;
        this.ratings = ratings;
    }

    public static EduNewModel parseEduNewModel (JSONObject jo) throws JSONException {
            int _eduId = jo.getInt("id");

            String _nameen = jo.getString("node_name");
        String _namebn = jo.getString("node_bn");

            String _edtype = jo.getString("education_type");
            String _shift = jo.getString("shift");
            String _studentno = jo.getString("no_of_students");
            String _teachersno = jo.getString("no_of_teachers");
            String _avgstdperclass = jo.getString("class_size");
            String _facility = jo.getString("facility");


            String _lat = jo.getJSONObject("map_info").getString("lat");
            String _lon = jo.getJSONObject("map_info").getString("lon");
            String _houseno = jo.getJSONObject("contact_info").getString("house_no");
            String _block = jo.getJSONObject("contact_info").getString("block");
            String _area = jo.getJSONObject("contact_info").getString("area");
            String _policestation = jo.getJSONObject("contact_info").getString("police_station");
            String _node_email = jo.getJSONObject("contact_info").getString("node_email");

            String _ward = jo.getJSONObject("contact_info").getString("ward");
            String _road = jo.getJSONObject("contact_info").getString("road");
            String _node_contact = jo.getJSONObject("contact_info").getString("node_contact");

            String _other=jo.getJSONObject("contact_info").getString("other");
            String _opentime = jo.getJSONObject("timing_info").getString("opening_time");
            String _closetime = jo.getJSONObject("timing_info").getString("closing_time");
            String _offday = jo.getJSONObject("timing_info").getString("off_day");
            int _catid=jo.getInt("category");
            JSONArray jr=jo.getJSONArray("references");

            String k=jr.toString();
            String _refnumm=k.substring(1,k.length()-1);
            String _rating=jo.getString("rating");
            JSONArray sref2=jo.getJSONArray("sub_categories");

            String ki=sref2.toString();
            String _sref=ki.substring(1,ki.length()-1);
            return new EduNewModel(_eduId,_nameen,_namebn,_edtype,_shift,_studentno,_teachersno,_avgstdperclass,_facility,
                    _lat, _lon,_houseno,_block,_area,_policestation,_node_email,_ward,_road,_node_contact,_other,
                    _opentime ,_closetime,_offday,_catid,_refnumm,_sref,_rating);
        }
    }

