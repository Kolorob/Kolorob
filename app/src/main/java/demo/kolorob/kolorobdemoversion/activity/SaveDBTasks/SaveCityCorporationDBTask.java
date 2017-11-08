package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.SplashActivityNew;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public class SaveCityCorporationDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, CityCorporationTable, CityCorporation> {

    public SaveCityCorporationDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }

    @Override
    protected void onPostExecute(Long result) {
        callNextProcess();
    }

    @Override
    public void callNextProcess(){
        if (json.has("ward")) {
            try {
                new SaveWardDBTask(context, json).execute(json.getJSONArray("ward"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new CityCorporationTable(context), new CityCorporation(), jsonArrays);
    }

    @Override

    protected void onPreExecute(){
        super.onPreExecute();
        Log.e(" Data collection : ",  "starting " + getClass());
    }

}