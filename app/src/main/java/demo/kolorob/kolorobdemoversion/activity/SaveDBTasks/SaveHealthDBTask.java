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
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveHealthDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, HealthNewDBTableMain, HealthNewDBModelMain> {
    public SaveHealthDBTask(Context ctx) {
        super(ctx);
    }


    protected Long doInBackground(JSONArray... jsonArrays) {
        return super.doInBackground(new HealthNewDBTableMain(context), new HealthNewDBModelMain(), AppConstants.HEALTH, jsonArrays);
    }
}
