package demo.kolorob.kolorobdemoversion.model.Financial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class FinancialNewDBModel implements Serializable {
    int finid;
    String nameen;
    String namebn;
    String fintype;
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
    String parent_area;
    String offday;
    String closetime;
    String openingtime;
    int categoryId;

    String refnumm;
    String ratings;
    String subcat;


    public FinancialNewDBModel(int finid, String nameen, String namebn, String fintype, String servicetype,
                               String lat, String lon, String houseno, String block, String area, String policestation,
                               String node_email, String ward, String road, String node_contact, String otherinfo,String areabn, String parent_area,
                               String openingtime , String closetime,String offday , int categoryId, String refnumm, String ratings, String subcat) {
        this.finid = finid;
        this.nameen = nameen;
        this.namebn = namebn;
        this.fintype = fintype;
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
        this.areabn=areabn;
        this.parent_area = parent_area;
        this.offday = offday;
        this.closetime = closetime;
        this.openingtime = openingtime;
        this.categoryId = categoryId;
        this.refnumm = refnumm;
        this.subcat = subcat;
        this.ratings = ratings;
    }

    public int getFinid() {
        return finid;
    }

    public void setFinid(int finid) {
        this.finid = finid;
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

    public String getFintype() {
        return fintype;
    }

    public void setFintype(String fintype) {
        this.fintype = fintype;
    }

    public String getServicetype() {
        return servicetype;
    }

    public String getAreabn() {
        return areabn;
    }

    public void setAreabn(String areabn) {
        this.areabn = areabn;
    }

    public String getParent_area() {
        return parent_area;
    }

    public void setParent_area(String parent_area) {
        this.parent_area = parent_area;
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

    public static FinancialNewDBModel parseFinancialNewDBModel (JSONObject jo) throws JSONException {
        int _finid = jo.getInt("id");

        String _nameen = jo.getString("node_name");
        String _namebn = jo.getString("node_bn");

        String _fintype = jo.getString("type");
        String _servicetype = jo.getString("service");

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
        String _parentarea = jo.getJSONObject("contact_info").getString("parent_area");

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
        return new FinancialNewDBModel(_finid,_nameen,_namebn,_fintype,_servicetype,
                _lat, _lon,_houseno,_block,_area,_policestation,_node_email,_ward,_road,_node_contact,_other,_areabn, _parentarea,
                _opentime  ,_closetime,_offday,_catid,_refnumm,_rating,_sref);
    }
}
