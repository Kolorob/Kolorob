package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 6/26/2016.
 */
public class FinancialNewItem {
    String finId;
    String nameen;
    String namebn;
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
    String openingtime;
    String closetime;
    String breaktime;
    String offday;
    String registeredwith;
    String registerednumber;
    String category;
    String refnum;

    public FinancialNewItem(String finId, String nameen, String namebn) {
        this.finId = finId;
        this.nameen = nameen;
        this.namebn = namebn;
    }

    public String getFinId() {
        return finId;
    }

    public void setFinId(String finId) {
        this.finId = finId;
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

    public static FinancialNewItem parseFinancialMapInfoItem(JSONObject jo) throws JSONException {
        String _finId = jo.getString("id");
        String _nameen = jo.getString("node_name");
        String _namebn = jo.getString("node_bn");

        return new FinancialNewItem(_finId,_nameen,
                _namebn);
    }
}
