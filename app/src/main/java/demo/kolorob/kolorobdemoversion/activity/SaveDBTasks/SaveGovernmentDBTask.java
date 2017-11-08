package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveGovernmentDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, GovNewDBTable, GovernmentNewDBModel> {


    public SaveGovernmentDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new GovNewDBTable(context), new GovernmentNewDBModel(), jsonArrays);
    }


    @Override
    void callNextProcess(){
        if (json.has("Legal")) {
            try {
                new SaveLegalDBTask(context, json).execute(json.getJSONArray("Legal"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}