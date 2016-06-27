package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 6/26/2016.
 */
public class FinancialContactInfoItem {
    String finId;
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

    public FinancialContactInfoItem(String finId,String floor, String housename, String houseno, String road, String line, String avenue, String block, String area, String landmark, String postoffice, String policestation, String city, String country, String node_contact, String node_contact2, String node_email, String node_website, String node_facebook, String node_designation) {
        this.finId=finId;
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
    public static FinancialContactInfoItem parseFinancialContactInfoItem(JSONObject jo) throws JSONException {
        String _finId = jo.getString("id");
        String _floor = jo.getString("floor");
        String _housename = jo.getString("house_name");
        String _houseno = jo.getString("house_no");
        String _road = jo.getString("road");
        String _line = jo.getString("line");
        String _avenue = jo.getString("avenue");
        String _block = jo.getString("block");
        String _area = jo.getString("area");

        String _landmark = jo.getString("landmark");
        String _postoffice = jo.getString("post_office");
        String _policestation = jo.getString("police_station");
        String _city = jo.getString("city");
        String _country = jo.getString("country");
        String _node_contact = jo.getString("node_contact");
        String _node_contact2 = jo.getString("node_contact2");
        String _node_email = jo.getString("node_email");
        String _node_website = jo.getString("node_website");
        String _node_facebook=jo.getString("node_facebook");
        String _node_designation=jo.getString("node_designation");



        return new FinancialContactInfoItem(_finId,_floor,_housename,_houseno,_road,_line,_avenue,_block,_area,_landmark,_postoffice,_policestation,
                _city,_country,_node_contact,_node_contact2,_node_email,_node_website,_node_facebook,_node_designation
        );
    }
}
