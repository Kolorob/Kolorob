package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class EntertainmentNewDBModel implements Serializable {
    int entId;
    CommonModel commonId;
    String entType, entryFee;

    public int getEntid() {
        return entId;
    }

    public void setEntid(int entId) {
        this.entId = entId;
    }

    public CommonModel getCommonId() {
        return commonId;
    }

    public void setCommonId(CommonModel commonId) {
        this.commonId = commonId;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    public EntertainmentNewDBModel(int entId, CommonModel commonId, String entType, String entryFee) {
        this.entId = entId;
        this.commonId = commonId;
        this.entType = entType;
        this.entryFee = entryFee;
    }

    public static EntertainmentNewDBModel parseEntertainmentNewDBModel (JSONObject jo, CommonModel commonId) throws JSONException {

        int _entid = jo.getInt("id");
        CommonModel _commonId = commonId;

        String _entType = jo.getString("type");
        String _entryFee = jo.getString("entry_fee");


        return new EntertainmentNewDBModel(_entid, _commonId, _entType, _entryFee);
    }
}
