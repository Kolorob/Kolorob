package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class EntertainmentNewDBModel extends CommonModel implements Serializable {

    private String entType, entryFee;

    public EntertainmentNewDBModel() {

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

    public EntertainmentNewDBModel(int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings, String comment, String entType, String entryFee) {
        super(id, nameEn, nameBn, houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo, openingTime, closingTime, offDay, lat, lon, categoryId, subcat, refNum, ratings, comment);
        this.entType = entType;
        this.entryFee = entryFee;
    }

    public EntertainmentNewDBModel(CommonModel cm, String entType, String entryFee) {
        super(cm.getId(), cm.getNameEn(), cm.getNameBn(),
                cm.getHouseNo(), cm.getBlock(), cm.getRoad(), cm.getArea(), cm.getAreaBn(), cm.getParentArea(), cm.getWard(), cm.getPoliceStation(),
                cm.getNodeContact(), cm.getNodeEmail(), cm.getOtherInfo(),
                cm.getOpeningTime(), cm.getClosingTime(), cm.getOffDay(),
                cm.getLat(), cm.getLon(),
                cm.getCategoryId(), cm.getSubcat(), cm.getRefNum(), cm.getRatings(), cm.getComment());
        this.entType = entType;
        this.entryFee = entryFee;
    }


    @Override
    public EntertainmentNewDBModel parse (JSONObject jo) throws JSONException {

        CommonModel _commonModel = new CommonModel().parse(jo);
        String _entType = jo.getString("type");
        String _entryFee = jo.getString("entry_fee");

        return new EntertainmentNewDBModel(_commonModel, _entType, _entryFee);
    }
}
