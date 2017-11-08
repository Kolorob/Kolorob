package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.model.Area;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public class SaveAreaDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, AreaTable, Area> {

    public SaveAreaDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }


    @Override
    public void callNextProcess(){
        Intent intent = new Intent(context, DataLoadingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new AreaTable(context), new Area(), jsonArrays);
    }


    @Override
    protected void onPreExecute(){
        Log.e(" Data collection : ",  "starting " + getClass());
    }

}