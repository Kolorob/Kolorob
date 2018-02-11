package demo.kolorob.kolorobdemoversion.model.Health;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/29/2017.
 */

public class HealthNewDBModelMain extends CommonModel implements Serializable {

    private String instituteType;


    public HealthNewDBModelMain() {

    }


    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }

    public HealthNewDBModelMain(int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings, String comment, String instituteType) {
        super(id, nameEn, nameBn, houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo, openingTime, closingTime, offDay, lat, lon, categoryId, subcat, refNum, ratings, comment);
        this.instituteType = instituteType;
    }

    public HealthNewDBModelMain(CommonModel cm, String instituteType){
        super(cm.getId(), cm.getNameEn(), cm.getNameBn(),
                cm.getHouseNo(), cm.getBlock(), cm.getRoad(), cm.getArea(), cm.getAreaBn(), cm.getParentArea(), cm.getWard(), cm.getPoliceStation(),
                cm.getNodeContact(), cm.getNodeEmail(), cm.getOtherInfo(),
                cm.getOpeningTime(), cm.getClosingTime(), cm.getOffDay(),
                cm.getLat(), cm.getLon(),
                cm.getCategoryId(), cm.getSubcat(), cm.getRefNum(), cm.getRatings(), cm.getComment());
        this.instituteType = instituteType;
    }

    @Override
    public HealthNewDBModelMain parse (JSONObject jo) throws JSONException {

        CommonModel _commonModel = new CommonModel().parse(jo);
        String _instituteType = jo.getString("institute_type");

        return new HealthNewDBModelMain(_commonModel, _instituteType);
    }
}
