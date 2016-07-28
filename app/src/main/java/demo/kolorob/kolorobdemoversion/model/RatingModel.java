package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by israt.jahan on 7/28/2016.
 */
public class RatingModel {
    static Date today = Calendar.getInstance().getTime();
    int id;
    String ratingvalue;
    String date;

    public RatingModel(int id, String ratingvalue, String date) {
        this.id = id;
        this.ratingvalue = ratingvalue;
        date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRatingvalue() {
        return ratingvalue;
    }

    public void setRatingvalue(String ratingvalue) {
        this.ratingvalue = ratingvalue;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        date = date;
    }

    public static RatingModel parseRatingModel(JSONObject jo) throws JSONException {
        int id = jo.getInt("id");
        String name = jo.getString("avg");

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String folderName = simpleDateFormat.format(today);

        return new RatingModel(id, name, folderName);
    }
}
