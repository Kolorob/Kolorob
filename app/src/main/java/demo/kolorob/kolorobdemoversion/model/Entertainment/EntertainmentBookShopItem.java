package demo.kolorob.kolorobdemoversion.model.Entertainment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 2/8/2016.
 */
public class EntertainmentBookShopItem {
    private String nodeId;
    private int entSubCategoryId;
    private String borrowCost;
    private String lending;
    private String memcost;
    private String offers;
    private String offerdetails;
    private String memcostffp;
    private String memcostfoc;

    public EntertainmentBookShopItem(String nodeId, int entSubCategoryId, String borrowCost, String lending, String memcost, String offers, String offerdetails, String memcostffp, String memcostfoc) {
        this.nodeId = nodeId;
        this.entSubCategoryId = entSubCategoryId;
        this.borrowCost = borrowCost;
        this.lending = lending;
        this.memcost = memcost;
        this.offers = offers;
        this.offerdetails = offerdetails;
        this.memcostffp = memcostffp;
        this.memcostfoc = memcostfoc;
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

    public String getBorrowCost() {
        return borrowCost;
    }

    public void setBorrowCost(String borrowCost) {
        this.borrowCost = borrowCost;
    }

    public String getLending() {
        return lending;
    }

    public void setLending(String lending) {
        this.lending = lending;
    }

    public String getMemcost() {
        return memcost;
    }

    public void setMemcost(String memcost) {
        this.memcost = memcost;
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

    public String getMemcostffp() {
        return memcostffp;
    }

    public void setMemcostffp(String memcostffp) {
        this.memcostffp = memcostffp;
    }

    public String getMemcostfoc() {
        return memcostfoc;
    }

    public void setMemcostfoc(String memcostfoc) {
        this.memcostfoc = memcostfoc;
    }

    public static EntertainmentBookShopItem parseEntertainmentBookShopItem(JSONObject jo) throws JSONException {
        String _nodeId = jo.getString("node_id");
        int _entSubCategoryId = jo.getInt("ent_sub_category_id");
        String _borrowCost = jo.getString("borrow_cost");
        String _lending = jo.getString("lending_allowed");
        String _memcost = jo.getString("membership_cost");
        String _offers = jo.getString("offers");
        String _offerdetails = jo.getString("offer_details");
        String _memcostffp = jo.getString("membership_cost_ffp");
        String _memcostfoc = jo.getString("membership_cost_foc");


        return new EntertainmentBookShopItem(
                _nodeId,
                _entSubCategoryId,
                _borrowCost,
                _lending,
                _memcost,
                _offers,
                _offerdetails,
                _memcostffp,
                _memcostfoc

        );
    }
}