package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/8/2016.
 */
public class EntertainmentFitnessItem {

    private String nodeId;
    private int entSubCategoryId;
    private int yearofestablishment;
    private int workers;
    private String offers;
    private String offerdetails;
    private String servicetype;
    private String type;
    private String servicedetails;

    public EntertainmentFitnessItem(String nodeId, int entSubCategoryId, int yearofestablishment, int workers, String offers, String offerdetails, String servicetype, String type, String servicedetails) {
        this.nodeId = nodeId;
        this.entSubCategoryId = entSubCategoryId;
        this.yearofestablishment = yearofestablishment;
        this.workers = workers;
        this.offers = offers;
        this.offerdetails = offerdetails;
        this.servicetype = servicetype;
        this.type = type;
        this.servicedetails = servicedetails;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getEntSubCategoryId() {
        return entSubCategoryId;
    }

    public void setEntSubCategoryId(int entSubCategoryId) {
        this.entSubCategoryId = entSubCategoryId;
    }

    public int getYearofestablishment() {
        return yearofestablishment;
    }

    public void setYearofestablishment(int yearofestablishment) {
        this.yearofestablishment = yearofestablishment;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getOfferdetails() {
        return offerdetails;
    }

    public void setOfferdetails(String offerdetails) {
        this.offerdetails = offerdetails;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServicedetails() {
        return servicedetails;
    }

    public void setServicedetails(String servicedetails) {
        this.servicedetails = servicedetails;
    }

    public static EntertainmentFitnessItem parseEntertainmentFitnessItem(JSONObject jo) throws JSONException {
        String _nodeId = jo.getString("node_id");
        int _entSubCategoryId = jo.getInt("ent_sub_category_id");
        int _yearofestablishment = jo.getInt("year_of_establishment");
        int _workers = jo.getInt("num_workers");
        String _offers = jo.getString("offers");
        String _offerdetails = jo.getString("offer_details");
        String _servicetype = jo.getString("service_type");
        String _type = jo.getString("type");
        String _servicedetails = jo.getString("service_details");

        return new EntertainmentFitnessItem(
                _nodeId,
                _entSubCategoryId,
                _yearofestablishment,
                _workers,
                _offers,
                _offerdetails,
                _servicetype,
                _type,
                _servicedetails

        );
    }
}
