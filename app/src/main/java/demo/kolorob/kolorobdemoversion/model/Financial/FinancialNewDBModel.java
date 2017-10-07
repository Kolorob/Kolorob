package demo.kolorob.kolorobdemoversion.model.Financial;

import demo.kolorob.kolorobdemoversion.model.CommonModel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;


/**
 * Created by israt.jahan on 1/29/2017.
 */

public class FinancialNewDBModel extends CommonModel implements Serializable {


    String finType, serviceType;

    public String getFinType() {
        return finType;
    }

    public void setFinType(String finType) {
        this.finType = finType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public FinancialNewDBModel(int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings, String finType, String serviceType) {
        super(id, nameEn, nameBn, houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo, openingTime, closingTime, offDay, lat, lon, categoryId, subcat, refNum, ratings);
        this.finType = finType;
        this.serviceType = serviceType;
    }

    public FinancialNewDBModel(CommonModel cm, String finType, String serviceType){
        super(cm.getId(), cm.getNameEn(), cm.getNameBn(),
                cm.getHouseNo(), cm.getBlock(), cm.getRoad(), cm.getArea(), cm.getAreaBn(), cm.getParentArea(), cm.getWard(), cm.getPoliceStation(),
                cm.getNodeContact(), cm.getNodeEmail(), cm.getOtherInfo(),
                cm.getOpeningTime(), cm.getClosingTime(), cm.getOffDay(),
                cm.getLat(), cm.getLon(),
                cm.getCategoryId(), cm.getSubcat(), cm.getRefNum(), cm.getRatings());
        this.finType = finType;
        this.serviceType = serviceType;

    }

    public static FinancialNewDBModel parseFinancialNewDBModel (JSONObject jo) throws JSONException {

        CommonModel cm = parseCommonModel(jo);
        String _finType = jo.getString("type");
        String _serviceType = jo.getString("service");
        return new FinancialNewDBModel(cm, _finType, _serviceType);
    }
}
