package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.SplashActivityNew;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.WardTable;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.Ward;


/**
 * Created by shamima.yasmin on 10/18/2017.
 */


public class SaveWardDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, WardTable, Ward> {

    public SaveWardDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new WardTable(context), new Ward(), jsonArrays);
    }

    @Override
    protected void onPostExecute(Long result) {
        Log.e(" Data collection : ", "done " + getClass());
        callNextProcess();
    }

    @Override
    public void callNextProcess(){
        if (json.has("areas")) {
            try {
                new SaveAreaDBTask(context, json).execute(json.getJSONArray("areas"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onPreExecute(){
        Log.e(" Data collection : ",  "starting " + getClass());
    }

}