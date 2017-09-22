package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class EntertainmentNewDBModel implements Serializable {
    int entId;
    CommonModel commonModel;
    String entType, entryFee;

    public int getEntid() {
        return entId;
    }

    public void setEntid(int entId) {
        this.entId = entId;
    }

    public CommonModel getCommonModel() {
        return commonModel;
    }

    public void setCommonModel(CommonModel commonModel) {
        this.commonModel = commonModel;
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

    public EntertainmentNewDBModel(int entId, CommonModel commonModel, String entType, String entryFee) {
        this.entId = entId;
        this.commonModel = commonModel;
        this.entType = entType;
        this.entryFee = entryFee;
    }

    public static EntertainmentNewDBModel parseEntertainmentNewDBModel (JSONObject jo) throws JSONException {

        CommonModel _commonModel = CommonModel.parseCommonModel(jo);

        int _entid = jo.getInt("id");
        String _entType = jo.getString("type");
        String _entryFee = jo.getString("entry_fee");


        return new EntertainmentNewDBModel(_entid, _commonModel, _entType, _entryFee);
    }
}
