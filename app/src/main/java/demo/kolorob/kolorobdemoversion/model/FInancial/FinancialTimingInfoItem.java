package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 6/26/2016.
 */
public class FinancialTimingInfoItem {
    String finId;
    String openingtime;
    String closetime;
    String breaktime;
    String offday;

    public FinancialTimingInfoItem(   String finId,String openingtime, String closetime, String breaktime, String offday) {
       this.finId=finId;
        this.openingtime = openingtime;
        this.closetime = closetime;
        this.breaktime = breaktime;
        this.offday = offday;
    }

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public String getFinId() {
        return finId;
    }

    public void setFinId(String finId) {
        this.finId = finId;
    }

    public void setBreaktime(String breaktime) {
        this.breaktime = breaktime;
    }

    public String getOffday() {
        return offday;
    }

    public void setOffday(String offday) {
        this.offday = offday;
    }
    public static FinancialTimingInfoItem parseFinancialTimingInfoItem(JSONObject jo) throws JSONException {
        String _finId = jo.getString("id");
        String _opentime = jo.getString("opening_time");
        String _breaktime = jo.getString("break_time");
        String _closetime = jo.getString("closing_time");
        String _offday = jo.getString("off_day");
        return new FinancialTimingInfoItem(_finId,_opentime,
                _breaktime,_closetime,_offday);
    }
}
