package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveHealthDBTask extends GenericSaveDBTask<JSONArray, Integer, Long, HealthNewDBTableMain, HealthNewDBModelMain> {

    public SaveHealthDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }


    @Override
    protected Long doInBackground(JSONArray... jsonArrays) {

        Log.e(" Data collection : ",  "ongoing " + getClass());

        JSONArray data = jsonArrays[0];

        for (int i = 0; i < data.length(); i++) {

            try {
                if (!data.isNull(i)) {
                    JSONObject jsonObject = data.getJSONObject(i);

                    HealthNewDBModelMain health = new HealthNewDBModelMain().parse(jsonObject);
                    new HealthNewDBTableMain(context).insertItem(health);

                    if (jsonObject.has("health_pharmacy")) {
                        JSONObject pharmacy = jsonObject.getJSONObject("health_pharmacy");
                        new HealthNewDBTablePharma(context).insertItem(new HealthNewDBModelPharmacy().parse(pharmacy, health.getId()));
                    }
                    if (jsonObject.has("health_hospital")) {
                        JSONObject hospital = jsonObject.getJSONObject("health_hospital");
                        new HealthNewDBTableHospital(context).insertItem(new HealthNewDBModelHospital().parse(hospital, health.getId()));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return new Long(-1);
            }
        }


        return new Long(0);
    }


    @Override
    void callNextProcess(){
        if (json.has("Entertainment")) {
            try {
                new SaveEntertainmentDBTask(context, json).execute(json.getJSONArray("Entertainment"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
