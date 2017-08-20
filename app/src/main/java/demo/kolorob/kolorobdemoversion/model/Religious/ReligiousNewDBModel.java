package demo.kolorob.kolorobdemoversion.model.Religious;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by zahid on 2/8/2017.
 */

public class ReligiousNewDBModel implements Serializable {
    int religousid;
    String nameen;
    String namebn;

    String rs_religion;
    String rs_services_for;
    String other_religion;
    String rs_time;
    String rs_fee;

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
    String areabn;

    String parentarea;

    String offday;
    String closetime;
    String openingtime;
    int categoryId;

    String refnumm;
    String subcat;
    String ratings;

    public ReligiousNewDBModel(int religousid, String nameen, String namebn, String rs_religion, String rs_services_for, String other_religion, String rs_time, String rs_fee, String lat, String lon, String houseno,
                               String block, String area, String policestation, String node_email, String ward, String road,
                               String node_contact, String otherinfo, String areabn, String parentarea, String openingtime , String closetime, String offday,
                               int categoryId, String refnumm, String subcat, String ratings) {
        this.religousid = religousid;
        this.nameen = nameen;
        this.namebn = namebn;

        this.rs_religion = rs_religion;
        this.rs_services_for = rs_services_for;
        this.other_religion = other_religion;
        this.rs_time = rs_time;
        this.rs_fee = rs_fee;

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
        this.areabn=areabn;

        this.parentarea = parentarea;

        this.offday = offday;
        this.closetime = closetime;
        this.openingtime = openingtime;
        this.categoryId = categoryId;
        this.refnumm = refnumm;
        this.subcat = subcat;
        this.ratings = ratings;
    }

    public String getAreabn() {
        return areabn;
    }

    public void setAreabn(String areabn) {
        this.areabn = areabn;
    }



    public String getParentarea() {
        return parentarea;
    }

    public void setParentarea(String parentarea) {
        this.parentarea = parentarea;
    }


    public int getReligousid() {
        return religousid;
    }

    public void setReligousid(int religousid) {
        this.religousid = religousid;
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

    public String getRs_religion() {
        return rs_religion;
    }

    public void setRs_religion(String rs_religion) {
        this.rs_religion = rs_religion;
    }

    public String getRs_services_for() {
        return rs_services_for;
    }

    public void setRs_services_for(String rs_services_for) {
        this.rs_services_for = rs_services_for;
    }

    public String getOther_religion() {
        return other_religion;
    }

    public void setOther_religion(String other_religion) {
        this.other_religion = other_religion;
    }

    public String getRs_time() {
        return rs_time;
    }

    public void setRs_time(String rs_time) {
        this.rs_time = rs_time;
    }

    public String getRs_fee() {
        return rs_fee;
    }

    public void setRs_fee(String rs_fee) {
        this.rs_fee = rs_fee;
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
    public static ReligiousNewDBModel parseReligiousNewDBModel (JSONObject jo) throws JSONException {
        int _religiousid = jo.getInt("id");

        String _nameen = jo.getString("node_name");
        String _namebn = jo.getString("node_bn");

        String _rs_religion = jo.getString("rs_religion");
        String _rs_services_for = jo.getString("rs_services_for");
        String _other_religion = jo.getString("other_religion");
        String _rs_time = jo.getString("rs_time");
        String _rs_fee = jo.getString("rs_fee");

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
        String _areabn=jo.getJSONObject("contact_info").getString("area_bn");

        String __parentarea =jo.getJSONObject("contact_info").getString("parent_area");

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
        return new ReligiousNewDBModel(_religiousid,_nameen,_namebn,_rs_religion, _rs_services_for, _other_religion, _rs_time, _rs_fee,
                _lat, _lon,_houseno,_block,_area,_policestation,_node_email,_ward,_road,_node_contact,_other,_areabn,__parentarea,
                _opentime ,_closetime,_offday,_catid,_refnumm,_sref,_rating);
    }
}
