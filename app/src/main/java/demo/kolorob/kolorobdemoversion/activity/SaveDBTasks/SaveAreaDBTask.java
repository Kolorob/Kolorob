package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.activity.PhoneRegActivity;
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
    public void onPostExecute(Long result) {

        Log.e(" Data collection : ", "done " + getClass());

        if (result == 0.0) {
            callNextProcess();
        }
    }



    @Override
    public void callNextProcess(){

        Intent intent;
        SharedPreferences settings = context.getSharedPreferences("prefs", 0);
        boolean registered = settings.getBoolean("IFREGISTERED",false);

        if (registered)
            intent = new Intent(context, DataLoadingActivity.class);
        else
            intent = new Intent(context, PhoneRegActivity.class);

        context.startActivity(intent);
        //context.stopService(intent);

        ((Activity)context).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        ((Activity)context).finish();
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