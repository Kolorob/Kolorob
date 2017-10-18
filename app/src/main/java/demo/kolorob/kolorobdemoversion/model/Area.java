package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class Area extends BaseModel <Area> implements Serializable {

    private String area_name, area_bn, area_keyword, parentArea, lat, lon;
    private int id, ward_id;

    public Area(int id, String area_name, String area_bn, String area_keyword, String parentArea, String lat, String lon, int ward_id) {
        this.id = id;
        this.area_name = area_name;
        this.area_bn = area_bn;
        this.area_keyword = area_keyword;
        this.parentArea = parentArea;
        this.lat = lat;
        this.lon = lon;
        this.ward_id = ward_id;
    }

    public Area() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWard_id() {
        return ward_id;
    }

    public void setWard_id(int ward_id) {
        this.ward_id = ward_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public String getArea_bn() {
        return area_bn;
    }

    public void setArea_bn(String area_bn) {
        this.area_bn = area_bn;
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

    public String getArea_keyword() {
        return area_keyword;
    }

    public void setArea_keyword(String area_keyword) {
        this.area_keyword = area_keyword;
    }

    public  Area parse(JSONObject jo) throws JSONException {

        int id = jo.getInt("id");
        String area_en = jo.getString("area_en");
        String area_bn = jo.getString("area_bn");
        String area_keyword = jo.getString("area_keyword");
        String parent_area = jo.getString("parent_area");
        int ward_id = jo.getInt("_ward");
        String lat = jo.getString("lat");
        String lon = jo.getString("lon");

        return new Area(id, area_en, area_bn, area_keyword, parent_area, lat, lon, ward_id);
    }
}

