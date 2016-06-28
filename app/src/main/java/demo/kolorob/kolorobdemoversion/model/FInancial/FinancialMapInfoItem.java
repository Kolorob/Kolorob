package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 6/26/2016.
 */
public class FinancialMapInfoItem {
    String lat;
    String lon;

    public FinancialMapInfoItem(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
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
    public static FinancialMapInfoItem parseFinancialMapInfoItem(JSONObject jo) throws JSONException {
        String _lat = jo.getString("lat");

        String _lon = jo.getString("lon");

        return new FinancialMapInfoItem(_lat,
                _lon);
    }
}