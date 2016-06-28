package demo.kolorob.kolorobdemoversion.model.FInancial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by israt.jahan on 6/26/2016.
 */
public class FinancialRegistrationInfoItem {
    String registeredwith;
    String registerednumber;

    public FinancialRegistrationInfoItem(String registeredwith, String registerednumber) {

        this.registeredwith = registeredwith;
        this.registerednumber = registerednumber;
    }

    public String getRegisteredwith() {
        return registeredwith;
    }

    public void setRegisteredwith(String registeredwith) {
        this.registeredwith = registeredwith;
    }

    public String getRegisterednumber() {
        return registerednumber;
    }

    public void setRegisterednumber(String registerednumber) {
        this.registerednumber = registerednumber;
    }
    public static FinancialRegistrationInfoItem parseFinancialRegistrationInfoItem(JSONObject jo) throws JSONException {
        String _regwith = jo.getString("node_registered_with");

        String _regnum = jo.getString("node_registered_number");

        return new FinancialRegistrationInfoItem(_regwith,
                _regnum);
    }
}