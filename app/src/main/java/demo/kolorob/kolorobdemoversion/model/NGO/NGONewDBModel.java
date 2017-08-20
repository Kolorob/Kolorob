package demo.kolorob.kolorobdemoversion.model.NGO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by zahid on 7/297/2017.
 */

public class NGONewDBModel implements Serializable {
    int ngoid;
    String nameen;
    String namebn;

    String services;
    String services_for;
    String services_others;
    String drop_time;
    String ngo_fee;

    String servicetype;
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

    public NGONewDBModel(int ngoid, String nameen, String namebn, String services, String services_for, String services_others, String drop_time, String ngo_fee, String servicetype, String lat, String lon, String houseno, String block, String area, String policestation, String node_email, String ward, String road, String node_contact, String otherinfo, String areabn, String parentarea, String offday, String closetime, String openingtime, int categoryId, String refnumm, String subcat, String ratings) {
        this.ngoid = ngoid;
        this.nameen = nameen;
        this.namebn = namebn;
        this.services = services;
        this.services_for = services_for;
        this.services_others = services_others;
        this.drop_time = drop_time;
        this.ngo_fee = ngo_fee;
        this.servicetype = servicetype;
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
        this.areabn = areabn;
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


    public int getNgoid() {
        return ngoid;
    }

    public void setNgoid(int ngoid) {
        this.ngoid = ngoid;
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

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getServices_for() {
        return services_for;
    }

    public void setServices_for(String services_for) {
        this.services_for = services_for;
    }

    public String getServices_others() {
        return services_others;
    }

    public void setServices_others(String services_others) {
        this.services_others = services_others;
    }

    public String getDrop_time() {
        return drop_time;
    }

    public void setDrop_time(String drop_time) {
        this.drop_time = drop_time;
    }

    public String getNgo_fee() {
        return ngo_fee;
    }

    public void setNgo_fee(String ngo_fee) {
        this.ngo_fee = ngo_fee;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
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

    public static NGONewDBModel parseNgoNewDBModel (JSONObject jo) throws JSONException {
        int _ngoid = jo.getInt("id");

        String _nameen = jo.getString("node_name");
        String _namebn = jo.getString("node_bn");

        String _servicetype = jo.getString("service_type");
        String _services = jo.getString("ngo_services");
        String _services_for = jo.getString("ngo_services_for");
        String _services_others = jo.getString("ngo_services_other");
        String _drop_time = jo.getString("drop_time");
        String _ngo_fee = jo.getString("ngo_fee");

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
        return new NGONewDBModel(_ngoid, _nameen, _namebn, _services, _services_for, _services_others, _drop_time, _ngo_fee, _servicetype, _lat, _lon, _houseno, _block, _area, _policestation, _node_email, _ward, _road, _node_contact, _other, _areabn, __parentarea, _offday, _closetime, _opentime, _catid, _refnumm, _sref, _rating);
    }
}
