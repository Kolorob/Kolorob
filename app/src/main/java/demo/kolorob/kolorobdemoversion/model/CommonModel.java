package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;

/**
 * Created by shamima.yasmin on 9/21/2017.
 */

public class CommonModel implements Serializable {

    int id;
    String nameEn, nameBn;

    String houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo;

    String openingTime, closingTime, offDay;

    String lat, lon;

    int categoryId;
    String subcat, refNum, ratings;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameBn() {
        return nameBn;
    }

    public void setNameBn(String nameBn) {
        this.nameBn = nameBn;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaBn() {
        return areaBn;
    }

    public void setAreaBn(String areaBn) {
        this.areaBn = areaBn;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getNodeContact() {
        return nodeContact;
    }

    public void setNodeContact(String nodeContact) {
        this.nodeContact = nodeContact;
    }

    public String getNodeEmail() {
        return nodeEmail;
    }

    public void setNodeEmail(String nodeEmail) {
        this.nodeEmail = nodeEmail;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getOffDay() {
        return offDay;
    }

    public void setOffDay(String offDay) {
        this.offDay = offDay;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }


    protected CommonModel(int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameBn = nameBn;
        this.houseNo = houseNo;
        this.block = block;
        this.road = road;
        this.area = area;
        this.areaBn = areaBn;
        this.parentArea = parentArea;
        this.ward = ward;
        this.policeStation = policeStation;
        this.nodeContact = nodeContact;
        this.nodeEmail = nodeEmail;
        this.otherInfo = otherInfo;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.offDay = offDay;
        this.lat = lat;
        this.lon = lon;
        this.categoryId = categoryId;
        this.subcat = subcat;
        this.refNum = refNum;
        this.ratings = ratings;
    }


    protected static CommonModel parseCommonModel (JSONObject jo) throws JSONException {

        int _id = jo.getInt("id");
        String _nameEn = jo.getString("node_name");
        String _nameBn = jo.getString("node_bn");


        String _houseNo = jo.getJSONObject("contact_info").getString("house_no");
        String _block = jo.getJSONObject("contact_info").getString("block");
        String _road = jo.getJSONObject("contact_info").getString("road");
        String _area = jo.getJSONObject("contact_info").getString("area");
        String _areaBn=jo.getJSONObject("contact_info").getString("area_bn");
        String _parentArea = jo.getJSONObject("contact_info").getString("parent_area");
        String _ward = jo.getJSONObject("contact_info").getString("ward");
        String _policeStation = jo.getJSONObject("contact_info").getString("police_station");
        String _nodeContact = jo.getJSONObject("contact_info").getString("node_contact");
        String _nodeEmail = jo.getJSONObject("contact_info").getString("node_email");
        String _otherInfo = jo.getJSONObject("contact_info").getString("other");

        String _openingTime = jo.getJSONObject("timing_info").getString("opening_time");
        String _closingTime = jo.getJSONObject("timing_info").getString("closing_time");
        String _offDay = jo.getJSONObject("timing_info").getString("off_day");

        String _lat = jo.getJSONObject("map_info").getString("lat");
        String _lon = jo.getJSONObject("map_info").getString("lon");

        int _catid = jo.getInt("category");

        JSONArray subCats = jo.getJSONArray("sub_categories");
        String subCatString = subCats.toString();
        String _subCat = subCatString.substring(1, subCatString.length() - 1);

        JSONArray references = jo.getJSONArray("references");
        String referenceString = references.toString();
        String _refNum = referenceString.substring(1, referenceString.length() - 1);

        String _rating = jo.getString("rating");

        return new CommonModel(_id, _nameEn, _nameBn,
                _houseNo, _block, _road, _area, _areaBn, _parentArea, _ward, _policeStation,
                _nodeContact, _nodeEmail, _otherInfo,
                _openingTime, _closingTime, _offDay,
                _lat, _lon,
                _catid, _subCat, _refNum, _rating);
    }
}
