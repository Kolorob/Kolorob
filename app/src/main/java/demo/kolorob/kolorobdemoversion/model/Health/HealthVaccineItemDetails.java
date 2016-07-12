package demo.kolorob.kolorobdemoversion.model.Health;

/**
 * Created by Mazharul.Islam1 on 7/12/2016.
 */

import org.json.JSONException;
import org.json.JSONObject;
public class HealthVaccineItemDetails {
    private String nodeId;
    private String vaccinename;
    private String vaccinefee;
    private String vaccineremarks;
    private int foreign_key;

    public HealthVaccineItemDetails(String nodeId, String vaccinename, String vaccinefee, String vaccineremarks, int foreign_key) {
        this.nodeId = nodeId;
        this.vaccinename = vaccinename;
        this.vaccinefee = vaccinefee;
        this.vaccineremarks = vaccineremarks;
        this.foreign_key = foreign_key;
    }

    public static HealthVaccinesItem parseHealthVaccinesItem(JSONObject jo,int foreign_key) throws JSONException {
        String _nodeId=jo.getString("id");
        String _vaccinename= jo.getString("vaccine_name");
        String _vaccinefee=jo.getString("vaccine_fee");
        String _vaccineremarks=jo.getString("vaccine_remark");
        int _refNum= foreign_key;


        return new HealthVaccinesItem(
                _nodeId,
                _vaccinename,
                _vaccinefee,
                _vaccineremarks,
                _refNum

        );




    }
}

