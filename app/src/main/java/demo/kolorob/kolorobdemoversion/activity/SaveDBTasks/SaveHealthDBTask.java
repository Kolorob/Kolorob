package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveHealthDBTask extends GenericSaveDBTask<HealthNewDBTableMain, HealthNewDBModelMain> {

    public SaveHealthDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public int saveItem() {

        Log.e(" Data collection : ",  "ongoing " + getClass());


        for (int i = 0; i < json.length(); i++) {

            try {
                if (!json.isNull(i)) {
                    JSONObject jsonObject = json.getJSONObject(i);

                    HealthNewDBModelMain health = new HealthNewDBModelMain().parse(jsonObject);
                    new HealthNewDBTableMain(context).insertItem(health);

                    if (jsonObject.has("health_pharmacy")) {
                        Log.e(" Health : ",  "pharmacy");
                        JSONObject pharmacy = jsonObject.getJSONObject("health_pharmacy");
                        new HealthNewDBTablePharma(context).insertItem(new HealthNewDBModelPharmacy().parse(pharmacy, health.getId()));
                    }
                    if (jsonObject.has("health_hospital")) {
                        Log.e(" Health : ",  "hospital");
                        JSONObject hospital = jsonObject.getJSONObject("health_hospital");
                        new HealthNewDBTableHospital(context).insertItem(new HealthNewDBModelHospital().parse(hospital, health.getId()));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return -1;
            }
        }


        return 1;
    }


}
