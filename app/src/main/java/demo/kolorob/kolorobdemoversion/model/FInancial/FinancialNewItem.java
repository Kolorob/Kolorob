package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 6/26/2016.
 */
public class FinancialNewItem {
    String finId;
    String nameen;
    String namebn;

    public FinancialNewItem(String finId, String nameen, String namebn) {
        this.finId = finId;
        this.nameen = nameen;
        this.namebn = namebn;
    }

    public String getFinId() {
        return finId;
    }

    public void setFinId(String finId) {
        this.finId = finId;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    public String getNamebn() {
        return namebn;
    }

    public void setNamebn(String namebn) {
        this.namebn = namebn;
    }

    public static FinancialNewItem parseFinancialMapInfoItem(JSONObject jo) throws JSONException {
        String _finId = jo.getString("id");
        String _nameen = jo.getString("node_name");
        String _namebn = jo.getString("node_bn");

        return new FinancialNewItem(_finId,_nameen,
                _namebn);
    }
}
